package com.transportsystem.backend.api.service;

import com.transportsystem.backend.api.dao.entity.Planet;
import com.transportsystem.backend.api.dao.entity.Route;
import com.transportsystem.backend.api.dao.entity.Traffic;
import com.transportsystem.backend.api.dao.repository.PlanetRepository;
import com.transportsystem.backend.api.dao.repository.RouteRepository;
import com.transportsystem.backend.api.dao.repository.TrafficRepository;
import com.transportsystem.backend.api.domain.*;
import com.transportsystem.backend.api.utility.DijkstraAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransportServiceImpl implements TransportService{

    private PlanetRepository planetRepository;
    private RouteRepository routeRepository;
    private TrafficRepository trafficRepository;

    @Autowired
    public TransportServiceImpl(final PlanetRepository planetRepository, final RouteRepository routeRepository, final TrafficRepository trafficRepository)
    {
        this.planetRepository=planetRepository;
        this.routeRepository=routeRepository;
        this.trafficRepository=trafficRepository;
    }

    @Override
    public List<Planet> listAllPlanet() {
        List<Planet> planets = new ArrayList<>();
        planetRepository.findAll().forEach(planets::add);
        return planets;
    }

    @Override
    public List<Route> listAllRoute() {
        List<Route> routes = new ArrayList<>();
        routeRepository.findAll().forEach(routes::add);
        return routes;
    }

    @Override
    public List<Traffic> listAllTraffic() {
        List<Traffic> traffics = new ArrayList<>();
        trafficRepository.findAll().forEach(traffics::add);
        return traffics;
    }

    @Override
    public String getPlanetName(String pid)
    {
        return planetRepository.findPlanetNameByPlanetId(pid);
    }

    @Override
    public List<RouteDetail> routeDetails()
    {
        List<RouteDetail> routeDetails = new ArrayList<>();
        List<Route> routes = new ArrayList<>();
        routeRepository.findAll().forEach(routes::add);

        for (Route route : routes)
        {
            RouteDetail routeDetail = new RouteDetail();
            routeDetail.setRoute_id(route.getRoute_id());
            routeDetail.setPlanet_origin_id(route.getPlanet_origin());
            routeDetail.setPlanet_origin_name(getPlanetName(route.getPlanet_origin()));
            routeDetail.setPlanet_destination_id(route.getPlanet_destination());
            routeDetail.setPlanet_destination_name(getPlanetName(route.getPlanet_destination()));
            routeDetail.setDistance(route.getDistance());
           Traffic traffic = trafficRepository.findByRouteid(route.getRoute_id());
            routeDetail.setTraffic_delay(traffic.getTraffic_delay());
            routeDetails.add(routeDetail);
        }
        return routeDetails;
    }



    @Override
    public Direction shortestPath(Path path) {
        LinkedList<Vertex> vertex = new LinkedList<Vertex>();
        List<Vertex> vertices = new ArrayList<>();
        List<Planet> planets = listAllPlanet();
        for (Planet p : planets) {
            Vertex v = new Vertex(p.getPlanet_node(), p.getPlanet_name());
            vertices.add(v);
        }

        List<Edge> edges = new ArrayList<>();
        List<RouteDetail> routes = routeDetails();
        for (RouteDetail r : routes) {
            Vertex source = null;
            Vertex destination = null;
            for (Vertex v : vertices) {
                if (v.getId().equals(r.getPlanet_origin_id())) {
                    source = v;
                }
                if (v.getId().equals(r.getPlanet_destination_id())) {
                    destination = v;
                }
            }
            Edge e = new Edge(r.getRoute_id(), source, destination, r.getDistance());
            edges.add(e);
        }
        Graph graph = new Graph(vertices, edges);
        DijkstraAlgorithm calculator = new DijkstraAlgorithm(graph);
        for (Vertex v : vertices) {
            if (v.getName().equals(path.getSource())) {
                calculator.execute(v);
                break;
            }
        }
        for (Vertex v : vertices) {
            if (v.getName().equals(path.getDestination())) {
                vertex = calculator.getPath(v);
                break;
            }
        }
        Direction direction = new Direction();
        direction.setSource(path.getSource());
        direction.setDestination(path.getDestination());
        direction.setPath(vertex.toString());
        ListIterator list_Iter = vertex.listIterator(0);

        List<RouteDetail> routeDetails = routeDetails();
        String prv = null;
        String nxt = null;
        double distance = 0.0;
        double traffic = 0.0;
        while (list_Iter.hasNext()) {

            if (prv == null) {
                prv = list_Iter.next().toString();
                continue;
            }
            nxt = list_Iter.next().toString();
            if (prv != null && nxt != null) {
                for (RouteDetail route : routeDetails) {
                    if (route.getPlanet_origin_name().equals(prv) && route.getPlanet_destination_name().equals(nxt)) {
                        distance = distance + route.getDistance();
                        traffic = traffic + route.getTraffic_delay();
                        break;
                    }
                }
                prv = nxt;
            }
        }
        direction.setDistance(distance);
        direction.setDuration(traffic);
        return direction;
    }



    @Override
    public void savePlanetData(List<Planet> planetList) {
        for(Planet planet : planetList) { planetRepository.save(planet); }
    }

    @Override
    public void saveRoutsData(List<Route> routes) {
        for(Route route : routes) { routeRepository.save(route); }
    }

    @Override
    public void saveTrafficData(List<Traffic> traffics) {
        for(Traffic traffic : traffics) { trafficRepository.save(traffic); }
    }

}
