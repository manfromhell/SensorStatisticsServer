package org.example.repo;

import org.example.domain.SensorData;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SensorDataRepository extends PagingAndSortingRepository<SensorData, Long> {
}
