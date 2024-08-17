package com.webflux_crud.webflux_crud.service;


import com.webflux_crud.webflux_crud.dto.ProductDto;
import com.webflux_crud.webflux_crud.repository.ProductRepository;
import com.webflux_crud.webflux_crud.utils.AppUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class ProductService {

    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Flux<ProductDto> getProducts(){
        return repository.findAll().map(AppUtils::entityToDto);
    }

    public Mono<ProductDto> getProduct(Integer id){
        return repository.findById(id).map(AppUtils::entityToDto);
    }


    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono){
        System.out.println("service method called ...");
        return productDtoMono.map(AppUtils::dtoToEntity)
                .flatMap(repository::save)
                .map(AppUtils::entityToDto);
    }

    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono, Integer id){
       return repository.findById(id)
                .flatMap(p->productDtoMono.map(AppUtils::dtoToEntity)
                .doOnNext(e->e.setId(id)))
                .flatMap(repository::save)
                .map(AppUtils::entityToDto);
    }

    public Mono<Void> deleteProduct(Integer id){
        return repository.deleteById(id);

    }
}
