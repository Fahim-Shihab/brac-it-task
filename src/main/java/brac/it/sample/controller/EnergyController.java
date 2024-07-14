package brac.it.sample.controller;

import brac.it.sample.dto.BatteryDto;
import brac.it.sample.dto.BatterySaveResponseDto;
import brac.it.sample.dto.GetBatteryStatsDto;
import brac.it.sample.service.BatteryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class EnergyController {
    @Autowired
    BatteryService batteryService;

    @PostMapping(path = "/batteries")
    @ResponseBody
    public List<BatterySaveResponseDto> saveBatteryList(@Valid @RequestBody List<BatteryDto> list) {
        return batteryService.saveBatteryList(list);
    }

    @GetMapping(path = "/batteries")
    @ResponseBody
    public GetBatteryStatsDto getBatteryStats(@RequestParam String range) {
        return batteryService.getBatteryStats(range);
    }
}