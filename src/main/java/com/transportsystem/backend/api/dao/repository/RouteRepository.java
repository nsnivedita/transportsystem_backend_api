package com.transportsystem.backend.api.dao.repository;

import com.transportsystem.backend.api.dao.entity.Route;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends CrudRepository<Route, Integer> {
}
