package com.transportsystem.backend.api.dao.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Traffic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int routeid;
    private String planet_origin;
    private String planet_destination;
    private double traffic_delay;

}
