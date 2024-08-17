package com.webflux_crud.webflux_crud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Product {
    @Id
    private Integer id;
    private String name;
    private int qty;
    private double price;

}
