package com.transportsystem.backend.api.service;

import com.transportsystem.backend.api.dao.entity.Planet;
import com.transportsystem.backend.api.dao.entity.Route;
import com.transportsystem.backend.api.dao.entity.Traffic;
import com.transportsystem.backend.api.domain.Direction;
import com.transportsystem.backend.api.domain.Path;
import com.transportsystem.backend.api.domain.RouteDetail;

import java.util.List;

public interface TransportService {

    List<Planet> listAllPlanet();
    List<Route> listAllRoute();
    List<Traffic> listAllTraffic();
    List<RouteDetail> routeDetails();
    Direction shortestPath(Path path);
    String getPlanetName(String pid);
    void savePlanetData(List<Planet> planets);
    void saveRoutsData(List<Route> routes);
    void saveTrafficData(List<Traffic> traffic);

}
