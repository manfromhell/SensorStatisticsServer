package org.example.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Sensor_Data")
public class SensorData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime created;
    private Integer light;
    private Integer gyro;

    public SensorData() {
    }

    public SensorData(Integer light, Integer gyro) {
        this.created = LocalDateTime.now();
        this.light = light;
        this.gyro = gyro;
    }

    public SensorData(Long id, LocalDateTime created, Integer light, Integer gyro) {
        this.id = id;
        this.created = created;
        this.light = light;
        this.gyro = gyro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Integer getLight() {
        return light;
    }

    public void setLight(Integer light) {
        this.light = light;
    }

    public Integer getGyro() {
        return gyro;
    }

    public void setGyro(Integer gyro) {
        this.gyro = gyro;
    }
}
