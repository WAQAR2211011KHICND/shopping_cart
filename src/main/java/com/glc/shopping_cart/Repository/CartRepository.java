package com.glc.shopping_cart.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glc.shopping_cart.Entity.Cart;

public interface CartRepository extends JpaRepository<Cart,Long>  {
    
}
