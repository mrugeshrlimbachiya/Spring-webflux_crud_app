package com.webflux_crud.webflux_crud.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Integer id;

//    @Valid

//    @NotNull(message = "name is mandatory")
//    @NotBlank(message = "name is mandatory")
//    @Size(min = 2, max = 12, message = "name should be between 1 and 12") //enter qty between 1 and 12
    private String name;

//    @NotNull(message = "qty is mandatory")
//    @NotBlank(message = "qty is mandatory")
//    @Size(min = 1, max = 24, message = "qty should be between 1 and 24") //enter qty between 1 and 24
    private int qty;

//    @NotNull(message = "price is mandatory")
//    @NotBlank(message = "price is mandatory")
    private double price;

}
