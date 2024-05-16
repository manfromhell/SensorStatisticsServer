package org.example.service;

import org.example.domain.SensorData;
import org.example.repo.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class SensorService {

    @Autowired
    SensorDataRepository repo;

    public Page<SensorData> getSensorData(Pageable pageable) {
        return repo.findAll(pageable);
    }
}
