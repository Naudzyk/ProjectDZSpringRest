package ru.zhenya.rest.RestApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zhenya.rest.RestApi.models.Sensor;
import ru.zhenya.rest.RestApi.repository.SensorRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;
    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }
    public Optional<Sensor> findByName(String name){
        return sensorRepository.findByName(name);
    }
    public void register(Sensor sensor){
        sensorRepository.save(sensor);
    }
}
