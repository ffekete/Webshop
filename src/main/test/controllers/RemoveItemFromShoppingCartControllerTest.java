package controllers;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import config.UrlConstants;
import domain.ShoppingCartInterface;

public class RemoveItemFromShoppingCartControllerTest{

    private MockMvc mvc;

    @Mock
    ShoppingCartInterface shoppingCart;
    
    @BeforeClass
    void setup() {
        MockitoAnnotations.initMocks(this);
        
        RemoveItemFromShoppingCartController removeItemController = new RemoveItemFromShoppingCartController(shoppingCart);
        mvc = MockMvcBuilders.standaloneSetup(removeItemController).build();
    }
    
    @Test
    void testShouldRedirectToListView() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get(UrlConstants.REMOVE_ITEM_FROM_CART_URL+"?id=0")).andExpect(MockMvcResultMatchers.redirectedUrl("list.html"));
    }
}
