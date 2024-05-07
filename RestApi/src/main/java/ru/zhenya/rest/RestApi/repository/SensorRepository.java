package ru.zhenya.rest.RestApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zhenya.rest.RestApi.models.Sensor;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor,Integer>{
    Optional<Sensor> findByName(String name);
}
