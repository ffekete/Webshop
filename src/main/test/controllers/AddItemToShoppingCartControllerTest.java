package controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import config.UrlConstants;
import config.queries.QueryConstants;
import domain.ShoppingCartInterface;
import domain.Store;
import domain.StoreInterface;
import domain.repository.ItemDAO;
import domain.repository.ItemDAOInterface;

public class AddItemToShoppingCartControllerTest{

    private MockMvc mvc;

    @Mock
    ShoppingCartInterface shoppingCart;
    
    ItemDAOInterface itemDAO;
    
    StoreInterface store;
    
    @Mock
    private Session session;
    
    @SuppressWarnings("rawtypes")
    @Mock
    Query query;
    
    @Mock
    SessionFactory factory;
    
    @Mock
    Transaction transaction;
    
    @AfterMethod
    public void validate() {
        Mockito.validateMockitoUsage();
    }
    
    @BeforeMethod
    void resetMocks(){
        Mockito.reset(session);
        Mockito.when(session.beginTransaction()).thenReturn(transaction);
        Mockito.when(session.createQuery(QueryConstants.QERY_STORE_BY_ITEM_ID_FROM_REPOSITORY)).thenReturn(query);
        Mockito.when(session.createQuery(QueryConstants.UPDATE_ITEM_AMOUNT_IN_STORE)).thenReturn(query);
    }
    
    @BeforeTest
    void setup() {
        MockitoAnnotations.initMocks(this);
        
        store = new Store();
        store.setAmount(100);
        store.setId(0);
                
        Mockito.when(factory.openSession()).thenReturn(session);
        Mockito.when(query.setParameter("id", 0)).thenReturn(query);
        Mockito.when(query.setParameter("newAmount", 0)).thenReturn(query);
        Mockito.when(query.getSingleResult()).thenReturn(store);
        Mockito.when(session.beginTransaction()).thenReturn(transaction);
        Mockito.when(session.createQuery(QueryConstants.QERY_STORE_BY_ITEM_ID_FROM_REPOSITORY)).thenReturn(query);
        Mockito.when(session.createQuery(QueryConstants.UPDATE_ITEM_AMOUNT_IN_STORE)).thenReturn(query);
        itemDAO = new ItemDAO(factory);
        
        AddItemToShoppingCartController addItemController = new AddItemToShoppingCartController(shoppingCart, itemDAO);
        mvc = MockMvcBuilders.standaloneSetup(addItemController).build();
    }
    
    
    @Test
    void testShouldRedirectToListView() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get(UrlConstants.ADD_ITEM_TO_CART_URL+"?id=0&quantity=1")).andExpect(MockMvcResultMatchers.redirectedUrl("list.html"));
        Mockito.verify(session, Mockito.times(1)).createQuery(QueryConstants.QERY_STORE_BY_ITEM_ID_FROM_REPOSITORY);
    }
    
    @Test
    void testShouldCallQueryOnce() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get(UrlConstants.ADD_ITEM_TO_CART_URL+"?id=0&quantity=1"));
        Mockito.verify(session, Mockito.times(1)).createQuery(QueryConstants.QERY_STORE_BY_ITEM_ID_FROM_REPOSITORY);
    }
}
