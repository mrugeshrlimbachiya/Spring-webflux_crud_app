package com.webflux_crud.webflux_crud.controller;


import com.webflux_crud.webflux_crud.dto.ProductDto;
import com.webflux_crud.webflux_crud.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public Flux<ProductDto> getProducts(){
        return service.getProducts();
    }

    @GetMapping("/{id}")
    public Mono<ProductDto> getProduct(@PathVariable Integer id){
        return service.getProduct(id);
    }

    @PostMapping("/")
    public Mono<ProductDto> saveProduct(@RequestBody Mono<ProductDto> productDtoMono){
        System.out.println("controller method called ...");
        return service.saveProduct(productDtoMono);
    }

    @PutMapping("/update/{id}")
    public Mono<ProductDto> updateProduct(@RequestBody Mono<ProductDto> productDtoMono, @PathVariable Integer id){
        return service.updateProduct(productDtoMono, Integer.valueOf(String.valueOf(id)));
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteProduct(@PathVariable Integer id){
        return service.deleteProduct(Integer.valueOf(String.valueOf(id)));
    }
}



