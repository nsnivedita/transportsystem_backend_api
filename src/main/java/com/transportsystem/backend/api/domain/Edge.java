package com.transportsystem.backend.api.domain;

import lombok.Data;

@Data
public class Edge {

    private final long id;
    private final Vertex source;
    private final Vertex destination;
    private final double weight;
}
