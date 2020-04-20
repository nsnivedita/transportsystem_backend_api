package com.transportsystem.backend.api.dao.repository;

import com.transportsystem.backend.api.dao.entity.Traffic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrafficRepository extends CrudRepository<Traffic, Integer> {

    Traffic findByRouteid(int s);
}
