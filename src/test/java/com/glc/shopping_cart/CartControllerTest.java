package com.glc.shopping_cart;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glc.shopping_cart.Entity.Cart;
import com.glc.shopping_cart.Repository.CartRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest
public class CartControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CartRepository cardRepository;

    private static Cart cut;

    @BeforeAll
    public static void setUp() {
        cut = new Cart();
    }

    @Test
    public void getAllCarts() throws Exception {
        Long id = 10L;
        String name = "Ruby Slippers";
        String image = "https://raw.githubusercontent.com/jeff-lent/Alisnobba/main/Capstone/ActualRubyRubySlippers.jpg";
        Long quantity = 5L;
        Long price = 123456789L;
        Long totalPrice = 12345678949L;

        cut = new Cart(id, image, name, quantity, totalPrice, price);
        Cart cut2 =  new Cart(id+1L, image, name, quantity+1L, totalPrice+1L, price+1L);
        
        List<Cart> carts = List.of(cut,cut2);
        when(cardRepository.findAll()).thenReturn(carts);
        ResultActions response = mockMvc.perform(get("/shopping_cart/all"));
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(carts.size())));
    }


    @Test
    public void addToCart() throws Exception {
        Long id = 10L;
        String name = "Ruby Slippers";
        String image = "https://raw.githubusercontent.com/jeff-lent/Alisnobba/main/Capstone/ActualRubyRubySlippers.jpg";
        Long quantity = 5L;
        Long price = 123456789L;
        Long totalPrice = 12345678949L;

        cut = new Cart(id, image, name, quantity, totalPrice, price);
        
        when(cardRepository.save(cut)).thenReturn(cut);
        ResultActions response = mockMvc.perform(
            post("/shopping_cart/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cut)) );

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(Integer.parseInt(cut.getId().toString()) )))
                .andExpect(jsonPath("$.name",is( cut.getName() )))
                .andExpect(jsonPath("$.image",is( cut.getImage() )))
                .andExpect(jsonPath("$.quantity",is( Integer.parseInt(cut.getQuantity().toString()) )))
                .andExpect(jsonPath("$.price",is( Integer.parseInt(cut.getPrice().toString()) )));
                
    }
    

    @Test
    public void deteleCardData() throws Exception {
        Long id = 10L;
        Mockito.doNothing().when(cardRepository).deleteById(id);  
        mockMvc.perform(
            delete("/shopping_cart/"+id) )
            .andExpect(status().isOk());

    }

    @Test
    public void deteleCardDataByNullId() throws Exception {
        Long id = null;
        when(cardRepository.findById(id)).thenReturn( Optional.empty());
        Mockito.doNothing().when(cardRepository).deleteById(id); 
        mockMvc.perform(
            delete("/shopping_cart/"+id) )
            .andExpect(status().is(400));
    }

    @Test
    public void deleteAllCard() throws Exception{
        Mockito.doNothing().when(cardRepository).deleteAll(); 
        mockMvc.perform(
            delete("/shopping_cart/delete_all") )
            .andExpect(status().is(200));

    }
}
