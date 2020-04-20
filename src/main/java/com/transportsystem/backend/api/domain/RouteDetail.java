package com.transportsystem.backend.api.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RouteDetail {

    private int route_id;
    private String planet_origin_id;
    private String planet_origin_name;
    private String planet_destination_id;
    private String planet_destination_name;
    private double distance;
    private double traffic_delay;


}
