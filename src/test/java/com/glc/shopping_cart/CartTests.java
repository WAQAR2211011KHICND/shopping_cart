package com.glc.shopping_cart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.glc.shopping_cart.Entity.Cart;
import com.glc.shopping_cart.Repository.CartRepository;

public class CartTests {

    @Mock
    private CartRepository cartRepository;

    private static Cart cut;

    @BeforeAll
    public static void setUp() {
        cut = new Cart();
    }

    @Test
    public void testsId() {
        Long id = 10L;
        cut.setId(id);
        assertNotNull(cut.getId());
        assertEquals(id, cut.getId().longValue());
    }

    @Test
    public void testsName() {
        String name = "Ruby Slippers";
        cut.setName(name);
        assertEquals(name, cut.getName());
    }

    @Test
    public void testImage() {
        String image = "https://raw.githubusercontent.com/jeff-lent/Alisnobba/main/Capstone/ActualRubyRubySlippers.jpg";
        cut.setImage(image);
        assertEquals(image, cut.getImage());
    }

    @Test
    public void testsQuantity() {
        Long quantity = 5L;
        cut.setQuantity(quantity);
        assertNotNull(cut.getQuantity());
        assertEquals(quantity, cut.getQuantity().longValue());
    }

    @Test
    public void testsPrice() {
        Long price = 123456789L;
        cut.setPrice(price);
        assertNotNull(cut.getPrice());
        assertEquals(price, cut.getPrice().longValue());
    }

    @Test
    public void testsTotalPrice() {
        Long totalPrice = 12345678949L;
        cut.setTotalPrice(totalPrice);
        assertNotNull(cut.getTotalPrice());
        assertEquals(totalPrice, cut.getTotalPrice().longValue());
    }


    @Test
    public void testNullId(){
        Long id = null;
        cut.setId(id);
        assertNull(cut.getId());
    }


    @Test
    public void testsAllArgConstructor(){
        Long id = 10L;
        String name = "Ruby Slippers";
        String image = "https://raw.githubusercontent.com/jeff-lent/Alisnobba/main/Capstone/ActualRubyRubySlippers.jpg";
        Long quantity = 5L;
        Long price = 123456789L;
        Long totalPrice = 12345678949L;

        cut = new Cart(id, image, name, quantity, totalPrice, price);
        assertEquals(id, cut.getId());
		assertEquals(name, cut.getName());
		assertEquals(image, cut.getImage());
		assertEquals(quantity, cut.getQuantity());
		assertEquals(totalPrice, cut.getTotalPrice());
		assertEquals(price, cut.getPrice());

    }


}
