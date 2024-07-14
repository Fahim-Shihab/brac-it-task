package brac.it.sample.dao;

import brac.it.sample.dto.BatteryStatDto;
import brac.it.sample.dto.GetBatteryStatsDto;
import brac.it.sample.model.Battery;
import brac.it.sample.repository.BatteryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Repository
@Transactional
public class BatteryDao {

    @PersistenceContext
    EntityManager em;

    @Autowired
    BatteryRepository batteryRepo;

    public GetBatteryStatsDto getBatteryStats(String range) {
        if (range != null && range.contains("-")) {
            String ranges[] = range.split("-");
            if (ranges != null && ranges.length == 2) {
                Integer rangeMin = 0;
                Integer rangeMax = 0;
                try {
                    rangeMin = Integer.parseInt(ranges[0]);
                    rangeMax = Integer.parseInt(ranges[1]);
                } catch (Exception e) {
                    System.out.println("Bad Request");
                    return new GetBatteryStatsDto();
                }

                String s = "select b from Battery b where 1=1 and cast(b.postCode as integer) between "+rangeMin+" and "+rangeMax;
                Query q = em.createQuery(s, Battery.class);
                List<Battery> batteryList = q.getResultList();
                if (batteryList != null) {

                    GetBatteryStatsDto responseObj = new GetBatteryStatsDto();
                    List<String> batteryNames = new ArrayList<>();
                    Integer totalWattCapacity = 0;
                    Double averageWattCapacity = 0.0;

                    for (Battery battery : batteryList) {
                        batteryNames.add(battery.getName());
                        totalWattCapacity += battery.getWattCapacity();
                    }

                    averageWattCapacity = Double.valueOf(totalWattCapacity / batteryList.size());

                    responseObj.setBatteryNames(batteryNames);
                    responseObj.setStatistics(new BatteryStatDto(totalWattCapacity, averageWattCapacity));

                    return responseObj;
                }
            }
        }
        return new GetBatteryStatsDto();
    }
}
