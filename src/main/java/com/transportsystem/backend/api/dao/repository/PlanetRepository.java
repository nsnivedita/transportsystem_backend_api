package com.transportsystem.backend.api.dao.repository;
import com.transportsystem.backend.api.dao.entity.Planet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends CrudRepository<Planet,Integer> {
    @Query(value = "select planet_name from Planet where planet_node = ?1")
    String findPlanetNameByPlanetId(String planet_node);
}
