package com.psl.productapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {

    private Long id;
    private String name;
    private String category;
    private String brand;
    private double price;
    private byte[] image;
    private boolean favourite;
    private int count;
}
