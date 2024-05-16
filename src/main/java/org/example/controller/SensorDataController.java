package org.example.controller;

import org.example.domain.SensorData;
import org.example.repo.SensorDataRepository;
import org.example.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class SensorDataController {

    private boolean enableSound;

    @Autowired
    SensorDataRepository repo;

    @Autowired
    SensorService service;

    @PostMapping("/setSound/{enableSound}")
    public ResponseEntity<Boolean> setSound(@PathVariable(name = "enableSound") boolean enableSound) {
        this.enableSound = enableSound;
        return ResponseEntity.ok(this.enableSound);
    }

    @GetMapping("/getSoundStatus")
    public ResponseEntity<Boolean> getSoundStatus() {
        return ResponseEntity.ok(this.enableSound);
    }

    @PostMapping(path = "/sendData")
    public ResponseEntity<String> sendData(@RequestParam(name = "light") Integer light, @RequestParam(name = "gyro") Integer gyro) {
        SensorData sensorData = repo.save(new SensorData(light, gyro));
        return ResponseEntity.ok("id = " + sensorData.getId() + ", light = " + sensorData.getLight()
                + ", gyro = " + sensorData.getGyro());
    }

    @GetMapping(path = "/getData")
    public ResponseEntity<Page<SensorData>> getSensorData(Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                pageable.getSort().and(Sort.by(Sort.Order.desc("id"))));
        Page<SensorData> page = service.getSensorData(pageRequest);
        return ResponseEntity.ok(page);
    }

    @GetMapping(path = "/table")
    public String getTable(Pageable pageable, Model model) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                pageable.getSort().and(Sort.by(Sort.Order.desc("id"))));
        Page<SensorData> page = service.getSensorData(pageRequest);
        model.addAttribute("sensorData", page);
        int totalPages = page.getTotalPages();
        model.addAttribute("totalPages", totalPages);
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("created", page.stream().map(SensorData::getCreated).collect(Collectors.toList()));
        model.addAttribute("light", page.stream().map(SensorData::getLight).collect(Collectors.toList()));
        model.addAttribute("gyro", page.stream().map(SensorData::getGyro).collect(Collectors.toList()));
        model.addAttribute("age", page.stream().map(SensorData::getId).collect(Collectors.toList()));
        return "table";
    }
}
