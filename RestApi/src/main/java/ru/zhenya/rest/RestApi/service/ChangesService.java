package ru.zhenya.rest.RestApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zhenya.rest.RestApi.models.Changes;
import ru.zhenya.rest.RestApi.repository.ChangesRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChangesService {
    private final ChangesRepository changesRepository;
    private final SensorService sensorService;
    @Autowired
    public ChangesService(ChangesRepository changesRepository, SensorService sensorService) {
        this.changesRepository = changesRepository;
        this.sensorService = sensorService;
    }

    public List<Changes> findAll(){
        return changesRepository.findAll();
    }

    public void addChanges(Changes changes){
        enrichChanges(changes);
        changesRepository.save(changes);

    }

    public void enrichChanges(Changes changes){
        changes.setSensor(sensorService.findByName(changes.getSensor().getName()).get());

        changes.setData_time(LocalDateTime.now());
    }
}
