package com.psl.productapi.controller;

import com.psl.productapi.model.Product;
import com.psl.productapi.service.ProductService;
import com.psl.productapi.util.ImageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin()
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public Product saveProduct(@RequestPart("product") Product product, @RequestPart("image") MultipartFile file) {
        Product prod = null;
        try{
            prod = productService.saveProduct(Product.builder().name(product.getName()).brand(product.getBrand()).category(product.getCategory()).price(product.getPrice()).image(ImageUtility.compressImage(file.getBytes())).favourite(product.isFavourite()).build());
        }catch (IOException e){
            e.printStackTrace();
        }

        return prod;
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long productId){
        return ResponseEntity.ok().body(productService.getProduct(productId));
    }

    @GetMapping("/fetch/image/{id}")
    public ResponseEntity<byte[]> getImageById(@PathVariable("id") Long productId){
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(productService.getProduct(productId).getImage());
    }

    @GetMapping("/fetch")
    public ResponseEntity<List<Product>> getAllProduct(){
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestPart("product") Product product, @RequestPart("image") MultipartFile file){
        Product prod = null;
        try{
            if(product.getImage()==null) {
                prod = productService.updateProduct(Product.builder().name(product.getName()).brand(product.getBrand()).category(product.getCategory()).price(product.getPrice()).image(ImageUtility.compressImage(file.getBytes())).favourite(product.isFavourite()).build(), id);
            }else{
                prod = productService.updateProduct(Product.builder().name(product.getName()).brand(product.getBrand()).category(product.getCategory()).price(product.getPrice()).image(ImageUtility.compressImage(product.getImage())).favourite(product.isFavourite()).build(), id);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(prod);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProductById(@PathVariable("id") Long id){
        try {
            productService.deleteProduct(id);
            return "Product with id-"+id+" has deleted";
        }catch (Exception e){
            return "Unable to delete Product";
        }
    }

    @DeleteMapping("/delete")
    public String deleteAllProduct(){
        try {
            productService.deleteAllProducts();
            return "All products are deleted";
        }catch (Exception e){
            return "Unable to delete Products";
        }
    }
}

