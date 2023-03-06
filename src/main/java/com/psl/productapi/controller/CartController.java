package com.psl.productapi.controller;

import com.psl.productapi.model.Cart;
import com.psl.productapi.model.CartItem;
import com.psl.productapi.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin()
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/fetch")
    public List<CartItem> getAllItems(){
        return cartService.getAllItems();
    }

    @GetMapping("/fetch/{id}")
    public CartItem getItemById(@PathVariable("id") Long id){
        return cartService.getCartItem(id);
    }

    @PostMapping("/save/{id}/{quantity}")
    public CartItem saveItem(@PathVariable("id") Long id, @PathVariable("quantity") int count){
        Cart cart = Cart.builder().productId(id).count(count).build();
        return cartService.saveItem(cart);
    }

    @PutMapping("/update/{id}/{quantity}")
    public CartItem updateItem(@PathVariable("id") Long id, @PathVariable("quantity") int count){
        return cartService.updateItem(id,count);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") Long id){
        cartService.deleteItem(id);
    }

    @DeleteMapping("/delete")
    public void deleteAllItem(){
        cartService.deleteAllItem();
    }
}
