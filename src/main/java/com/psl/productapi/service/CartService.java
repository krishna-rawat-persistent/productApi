package com.psl.productapi.service;

import com.psl.productapi.model.Cart;
import com.psl.productapi.model.CartItem;

import java.util.List;

public interface CartService {
    List<CartItem> getAllItems();
    CartItem getCartItem(Long id);
    CartItem updateItem(Long id, int count);
    CartItem saveItem(Cart cart);
    void deleteItem(Long id);
    void deleteAllItem();
}
