package brac.it.sample.service;

import brac.it.sample.common.Utils;
import brac.it.sample.dao.BatteryDao;
import brac.it.sample.dto.BatteryDto;
import brac.it.sample.dto.BatterySaveResponseDto;
import brac.it.sample.dto.GetBatteryStatsDto;
import brac.it.sample.model.Battery;
import brac.it.sample.repository.BatteryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BatteryService {
    @Autowired
    BatteryRepository batteryRepo;

    @Autowired
    BatteryDao batteryDao;

    public List<BatterySaveResponseDto> saveBatteryList(List<BatteryDto> list) {
        try {
            List<BatterySaveResponseDto> responseList = new ArrayList<>();
            List<Battery> batteryEoList = new ArrayList<>();
            List<Battery> batteryEoSavedList = new ArrayList<>();
            for (BatteryDto dto: list) {
                if (Utils.isOk(dto.name()) && dto.wattCapacity() != null) {
                    // Checking if the name already exists in battery table
                    Battery batteryEo = batteryRepo.findByName(dto.name()).orElse(new Battery());
                    batteryEo.setName(dto.name());
                    batteryEo.setPostCode(dto.postcode());
                    batteryEo.setWattCapacity(dto.wattCapacity());
                    batteryEoList.add(batteryEo);
                }
            }
            batteryEoSavedList = batteryRepo.saveAllAndFlush(batteryEoList);
            if (batteryEoSavedList != null) {
                batteryEoSavedList.stream().forEach(battery -> {
                    BatterySaveResponseDto dto = new BatterySaveResponseDto();
                    BeanUtils.copyProperties(battery, dto);
                    responseList.add(dto);
                });
                return responseList;
            }
        } catch (Exception e) {
            return null;
        }
        return new ArrayList<>();
    }

    public GetBatteryStatsDto getBatteryStats(String postcodeRange) {
        try {
            return batteryDao.getBatteryStats(postcodeRange);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return new GetBatteryStatsDto();
    }
}
