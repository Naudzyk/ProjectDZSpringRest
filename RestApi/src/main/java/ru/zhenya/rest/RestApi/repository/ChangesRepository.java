package ru.zhenya.rest.RestApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zhenya.rest.RestApi.models.Changes;

import java.util.Optional;

@Repository
public interface ChangesRepository extends JpaRepository<Changes,Integer> {

}
