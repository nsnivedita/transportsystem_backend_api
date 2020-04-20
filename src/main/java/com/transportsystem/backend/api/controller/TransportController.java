package com.transportsystem.backend.api.controller;

import com.transportsystem.backend.api.dao.entity.Planet;
import com.transportsystem.backend.api.dao.entity.Route;
import com.transportsystem.backend.api.dao.entity.Traffic;
import com.transportsystem.backend.api.domain.Direction;
import com.transportsystem.backend.api.domain.Path;
import com.transportsystem.backend.api.domain.RouteDetail;
import com.transportsystem.backend.api.service.TransportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api",
        produces = {
                APPLICATION_JSON_VALUE
        })
@Tag(name = "transport system", description = "The Transport System Backend API")
public class TransportController {

    private TransportService transportService;

    @Autowired
    public TransportController(final TransportService transportService)
    {
        this.transportService = transportService;
    }

    @Operation(summary = "Planets Name", description = "Find List of all planets which is present in database ", tags = { "planets" })
    @GetMapping (value = "/planets",  produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Planet>getPlanets()
    {
        return transportService.listAllPlanet();
    }

    @Operation(summary = "Distance between two planets", description = "Find List of all route which is present in database", tags = { "routes" })
    @GetMapping (value = "/routes",  produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Route>getRoutes()
    {
        return transportService.listAllRoute();
    }

    @Operation(summary = "Traffic Delay ", description = "Find List of all traffic delay which is present in database ", tags = { "traffics" })
    @GetMapping (value = "/traffics",  produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Traffic>getTraffic()
    {
        return transportService.listAllTraffic();
    }

    @Operation(summary = "Route Detail", description = "Find List of all route  detail from rote and traffic table. ", tags = { "routedetails" })
    @GetMapping (value = "/routdetails",  produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<RouteDetail>getRoutDetails()
    {
        return transportService.routeDetails();
    }

    @Operation(description = "Find Route ")
    @PostMapping (value = "/findroute",  produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Direction getDirection(@Valid @RequestBody Path path)
    {
        return transportService.shortestPath(path);

    }
}
