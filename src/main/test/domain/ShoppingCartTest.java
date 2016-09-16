package domain;

import java.util.LinkedList;
import java.util.List;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domain.repository.ItemDAOInterface;

public class ShoppingCartTest {
    
    List<ItemInterface> shoppingCartContent = new LinkedList<>();
    
    ShoppingCartInterface shoppingCart;
    
    @Mock
    ItemDAOInterface itemRepository;
    
    ItemInterface book = initializeBook();
    
    @BeforeClass
    void setup(){
        MockitoAnnotations.initMocks(this);
        
        shoppingCart = new ShoppingCart(shoppingCartContent, itemRepository);
        
        
        
        Mockito.when(itemRepository.findElementById(1)).thenReturn(book);
    }
    
    ItemInterface initializeBook(){
        ItemInterface book = new Item();
        book.setId(1);
        book.setDescription("Simple book.");
        book.setName("Book");
        book.setPrice(100.045);
        
        return book;
    }
    
    @AfterMethod
    void tearDown(){
        shoppingCart.clear();
    }

    @Test(expectedExceptions=NullPointerException.class)
    void testShouldNotFindAnythingToRemoveFromShoppingCart(){
        shoppingCart.add(book);
        shoppingCart.removeElementById(0);
    }
    
    @Test
    void testShouldRemoveItemFromShoppingCart(){
        shoppingCart.add(book);
        shoppingCart.removeElementById(1);
        Assert.assertEquals(shoppingCart.getItemsCount(), 0);
    }
    
    @Test
    void testShouldAddItemToShoppingCart(){
        shoppingCart.add(book);
        
        Assert.assertEquals(shoppingCart.getItemsCount(), 1);
    }
    
    @Test
    void testShouldFindBookInRepository(){
        shoppingCart.addItemById(1);
        
        Assert.assertEquals(shoppingCart.getItemsCount(), 1);
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    void testShouldFindNoneInTheRepository(){
        shoppingCart.addItemById(2);
    }
}
