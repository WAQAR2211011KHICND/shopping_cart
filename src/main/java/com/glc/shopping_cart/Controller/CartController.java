package com.glc.shopping_cart.Controller;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glc.shopping_cart.Entity.Cart;
import com.glc.shopping_cart.Repository.CartRepository;

@RestController
@RequestMapping("/shopping_cart")
@CrossOrigin
public class CartController {
    
    @Autowired
    private CartRepository cartRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Cart>> getAllCart(){
        return ResponseEntity.ok().body(cartRepository.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addCart(@RequestBody Cart cart){
        return ResponseEntity.ok().body(cartRepository.save(cart));
    }

    @DeleteMapping("/delete_all")
    public ResponseEntity<Void> deleteAllCart(){
        cartRepository.deleteAll();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCart(@PathVariable Long id){
        Optional<Cart> cartOptional = cartRepository.findById(id);
        if(cartOptional.isPresent()){
            cartRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();

        }


    }
    
}
