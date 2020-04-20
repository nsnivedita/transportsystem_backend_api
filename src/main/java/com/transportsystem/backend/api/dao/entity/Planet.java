package com.transportsystem.backend.api.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String planet_node;
    private String planet_name;

}
