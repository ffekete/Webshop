package controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockTestCase;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import config.UrlConstants;
import domain.Item;
import domain.ItemInterface;
import domain.JoinedItem;
import domain.JoinedItemInterface;
import domain.ShoppingCartInterface;
import domain.Store;
import domain.StoreInterface;
import domain.repository.ItemDAOInterface;

@PrepareForTest(ResourceBundleMessageSource.class)
public class ListItemTest extends PowerMockTestCase{

    private MockMvc mvc;
    
    private List<JoinedItemInterface> mockedList = new LinkedList<JoinedItemInterface>();
    
    @Mock
    private ShoppingCartInterface mockedShoppingCart;
    
    @Mock
    private ItemDAOInterface itemStore;
    
    void setUpMockedList(){
        ItemInterface book = new Item();
        book.setId(1);
        book.setDescription("Description text");
        book.setName("Book");
        book.setPrice(201.05);
        
        StoreInterface store = new Store();
           
        JoinedItemInterface joinedItem = new JoinedItem();
        joinedItem.setItem(book);
        joinedItem.setStore(store);
        
        mockedList.add(joinedItem);
        
        MockitoAnnotations.initMocks(this);
    }
    
    @BeforeClass
    void setup() {
        setUpMockedList();
        final ResourceBundleMessageSource messageSource = PowerMockito.mock(ResourceBundleMessageSource.class);
        
        PowerMockito.when(messageSource.getMessage(Mockito.anyString(), Mockito.any(Object[].class), Mockito.any(Locale.class))).thenReturn("");
        ListItemsController listItemsController = new ListItemsController(itemStore, mockedShoppingCart, messageSource);
        mvc = MockMvcBuilders.standaloneSetup(listItemsController).build();
    }
    
    @Test
    void testShouldReturnListView() throws Exception {
        Mockito.when(itemStore.listItems()).thenReturn(mockedList);
        mvc.perform(MockMvcRequestBuilders.get(UrlConstants.LIST_ITEMS_IN_WEBSHOP_URL)).andExpect(MockMvcResultMatchers.view().name("list"));
    }
}
