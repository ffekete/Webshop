package controllers;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import config.UrlConstants;
import domain.ShoppingCartInterface;
import domain.Store;
import domain.StoreInterface;
import domain.repository.ItemDAOInterface;
import service.ItemManager;

public class RemoveItemFromShoppingCartControllerTest{

    private MockMvc mvc;

    @Mock
    ShoppingCartInterface shoppingCart;
    
    @Mock
    ItemDAOInterface itemDAO;
    
    StoreInterface store = new Store();
    
    @AfterMethod
    public void validate() {
        Mockito.validateMockitoUsage();
    }
    
    @BeforeTest
    void setup() {
        MockitoAnnotations.initMocks(this);
        
        Mockito.when(itemDAO.findStoreEntryForItemId(0)).thenReturn(store);
        
        ItemManager itemManager = new ItemManager(itemDAO, shoppingCart);
        
        RemoveItemFromShoppingCartController removeItemController = new RemoveItemFromShoppingCartController(itemManager);
        mvc = MockMvcBuilders.standaloneSetup(removeItemController).build();
    }
    
    @Test
    void testShouldRedirectToListView() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get(UrlConstants.REMOVE_ITEM_FROM_CART_URL+"?id=0")).andExpect(MockMvcResultMatchers.redirectedUrl("list.html"));
        
        Mockito.verify(itemDAO, Mockito.times(1)).decreaseItemAmountInStore(store, -1);
    }
}
