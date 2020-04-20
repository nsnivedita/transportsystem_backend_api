package com.transportsystem.backend.api.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Direction {

   /* private String planet_origin_id;
    private String planet_origin_name;
    private String planet_destination_id;
    private String planet_destination_name;
    private List<Vertex> path;*/



    protected String source;

    protected String destination;

    protected String path;
    protected double distance;
    protected double duration;
}
