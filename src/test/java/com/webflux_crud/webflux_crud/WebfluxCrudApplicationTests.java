package com.webflux_crud.webflux_crud;

import com.webflux_crud.webflux_crud.controller.ProductController;
import com.webflux_crud.webflux_crud.dto.ProductDto;
import com.webflux_crud.webflux_crud.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(ProductController.class)
class WebfluxCrudApplicationTests {


	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private ProductService service;

	@Test
	@DisplayName("create user")
	void addProductTest(){
		Mono<ProductDto> productDtoMono = Mono.just(new ProductDto(6, "SemRush", 5, 231.5));
		when(service.saveProduct(productDtoMono)).thenReturn(productDtoMono);

		webTestClient.post().uri("/products/")
				.body(Mono.just(productDtoMono), ProductDto.class)
				.exchange()
				.expectStatus().isOk()
		    	.expectBody(ProductDto.class);
	}


	@Test
	@DisplayName("all products request")
	void getProductsTest(){
		Flux<ProductDto> productDtoFlux = Flux.just(new ProductDto(6, "SemRush", 5, 231.5),
				new ProductDto(7, "rock", 7, 535.4));
		when(service.getProducts()).thenReturn(productDtoFlux);

		Flux<ProductDto> responseBody = webTestClient.get().uri("/products")
				.exchange()
				.expectStatus().isOk()
				.returnResult(ProductDto.class)
				.getResponseBody();

		StepVerifier.create(responseBody)
				.expectSubscription()
				.expectNext(new ProductDto(6, "SemRush", 5, 231.5))
				.expectNext(new ProductDto(7, "rock", 7, 535.4))
				.verifyComplete();
	}


	@Test
	@DisplayName("product request by id")
	void getProductTest(){
		Mono<ProductDto> productDtoMono = Mono.just(new ProductDto(6, "SemRush", 5, 231.5));
		when(service.getProduct(any())).thenReturn(productDtoMono);

		Mono<ProductDto> responseBody = webTestClient.get().uri("/products/6")
				.exchange()
				.expectStatus().isOk()
				.returnResult(ProductDto.class)
				.getResponseBody().next();

		StepVerifier.create(responseBody)
				.expectNextMatches(p -> p.getName().equals("SemRush"))
				.verifyComplete();

	}


	@Test
	@DisplayName("product updated")
	void updateProductTest(){
		Mono<ProductDto> productDtoMono = Mono.just(new ProductDto(6, "SemRush", 5, 231.5));
		when(service.updateProduct(productDtoMono, 6)).thenReturn(productDtoMono);

		webTestClient.put().uri("/products/update/6")
				.body(Mono.just(productDtoMono), ProductDto.class)
				.exchange()
				.expectStatus().isOk();//200
	}


	@Test
	@DisplayName("product deleted")
	void deleteProductTest(){

		given(service.deleteProduct(any())).willReturn(Mono.empty());

		webTestClient.delete().uri("/products/delete/6")
				.exchange()
				.expectStatus().isOk();//200
	}
}
