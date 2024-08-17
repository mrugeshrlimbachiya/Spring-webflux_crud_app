package com.webflux_crud.webflux_crud.repository;

import com.webflux_crud.webflux_crud.entity.Product;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.stereotype.Repository;

@EnableR2dbcRepositories
public interface ProductRepository extends R2dbcRepository<Product, Integer> {
}
