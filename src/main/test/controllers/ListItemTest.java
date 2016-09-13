package controllers;

import java.util.LinkedList;
import java.util.List;

import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import config.ModelNameConstants;
import config.UrlConstants;
import domain.Item;
import domain.ItemInterface;
import domain.repository.ItemRepository;

public class ListItemTest {

    private MockMvc mvc;
    
    private List<ItemInterface> mockedList = new LinkedList<ItemInterface>();
    
    private ItemRepository itemStore;

    void setUpMockedList(){
        ItemInterface book = new Item();
        book.setId(1);
        book.setAvailable(true);
        book.setDescription("Description text");
        book.setName("Book");
        book.setPrice(201.05);
        
        itemStore = Mockito.mock(ItemRepository.class);
        mockedList.add(book);
    }
    
    @BeforeClass
    void setup() {
        setUpMockedList();
        ListItemsController listItemsController = new ListItemsController(itemStore);
        mvc = MockMvcBuilders.standaloneSetup(listItemsController).build();
    }
    
    @Test
    void testShouldReturnListView() throws Exception {
        Mockito.when(itemStore.listItems()).thenReturn(mockedList);
        mvc.perform(MockMvcRequestBuilders.get(UrlConstants.LIST_ITEMS_IN_WEBSHOP_URL)).andExpect(MockMvcResultMatchers.view().name("list"));
    }
    
    @Test
    void testResultShouldContainProperModelName() throws Exception{
        Mockito.when(itemStore.listItems()).thenReturn(mockedList);
        
        mvc.perform(MockMvcRequestBuilders.get(UrlConstants.LIST_ITEMS_IN_WEBSHOP_URL)).andExpect(MockMvcResultMatchers.model().attributeExists(ModelNameConstants.ITEM_STORE_MODEL_NAME));
    }
}
