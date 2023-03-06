package com.psl.productapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_category")
    private String category;

    @Column(name = "product_brand")
    private String brand;

    @Column(name = "product_price")
    private double price;

    @Column(name = "product_image", unique = false, nullable = true, length = 16777215)
    private byte[] image = null;

    @Column(name = "product_favourite")
    private boolean favourite;

}
