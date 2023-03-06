package com.psl.productapi.service;

import com.psl.productapi.model.Cart;
import com.psl.productapi.model.CartItem;
import com.psl.productapi.model.Product;
import com.psl.productapi.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private ProductService service;

    @Autowired
    private CartRepository repository;

    @Override
    public List<CartItem> getAllItems() {
        List<CartItem> items = new ArrayList<>();
        List<Cart> carts= repository.findAll();
        for(Cart cart:carts){
            Product product= service.getProduct(cart.getProductId());
            items.add(CartItem.builder().id(cart.getProductId()).name(product.getName()).category(product.getCategory()).brand(product.getBrand()).image(product.getImage()).price(product.getPrice()).favourite(product.isFavourite()).count(cart.getCount()).build());
        }
        return items;
    }

    @Override
    public CartItem getCartItem(Long id) {
        Cart cart = repository.getReferenceById(id);
        Product product = service.getProduct(id);
        return CartItem.builder().id(cart.getProductId()).name(product.getName()).category(product.getCategory()).brand(product.getBrand()).image(product.getImage()).price(product.getPrice()).favourite(product.isFavourite()).count(cart.getCount()).build();
    }

    @Override
    public CartItem updateItem(Long id, int count) {
        Cart cart= repository.save(Cart.builder().productId(id).count(count).build());
        Product product = service.getProduct(cart.getProductId());
        return CartItem.builder().id(product.getId()).name(product.getName()).category(product.getCategory()).brand(product.getBrand()).image(product.getImage()).price(product.getPrice()).favourite(product.isFavourite()).count(cart.getCount()).build();
    }

    @Override
    public CartItem saveItem(Cart cart) {
        Cart cart1 = repository.save(cart);
        Product product = service.getProduct(cart.getProductId());
        return CartItem.builder().id(cart.getProductId()).name(product.getName()).category(product.getCategory()).brand(product.getBrand()).image(product.getImage()).price(product.getPrice()).favourite(product.isFavourite()).count(cart1.getCount()).build();
    }

    @Override
    public void deleteItem(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAllItem() {
        repository.deleteAll();
    }
}
