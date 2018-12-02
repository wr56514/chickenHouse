package org.marcinski.chickenHouse.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String price;


    @ManyToOne
    @JoinColumn(name = "day_id")
    private Day day;
}
