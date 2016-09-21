package domain.repository;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import config.queries.QueryConstants;
import domain.Item;
import domain.ItemInterface;
import domain.JoinedItem;
import domain.JoinedItemInterface;
import domain.Store;
import domain.StoreInterface;

public class ItemStoreTest{

    @Mock
    SessionFactory factory;
    
    @Mock
    Session session;
    
    @Mock
    Transaction transaction;
    
    @Mock
    Query<Object[]> query;
    
    private List<Object[]> mockedList = new LinkedList<Object[]>();

    ItemDAO itemDAO;
    
    ItemInterface book;
    StoreInterface store;
    
    @BeforeClass
    void setup(){
        MockitoAnnotations.initMocks(this);
        
        itemDAO = new ItemDAO(factory);
        
        book = new Item();
        store = new Store();
        
        Object[] joinedItem = new Object[]{book, store};
        
        mockedList.add(joinedItem);
    }
    
    @BeforeMethod
    public void resetStoreAndBook(){
        
        book.setId(1);
        book.setDescription("Description text");
        book.setName("Book");
        book.setPrice(201.05);
        
        
        store.setId(1);
        store.setAmount(5);
    }
    
    @Test(expectedExceptions={IllegalArgumentException.class}, expectedExceptionsMessageRegExp="Requested amount is greater than actual amount in store for item.")
    void testShouldThrowExceptionRequestedAmountIsGreaterThanInStore(){
        JoinedItemInterface item = new JoinedItem();
        item.setItem(book);
        item.setStore(store);
        
        itemDAO.decreaseItemAmountInStore(store, 6);
    }

    @Test
    void testShouldDecreasaeItemNumberInStore(){
        Mockito.when(session.beginTransaction()).thenReturn(transaction);
        Mockito.when(factory.openSession()).thenReturn(session);
        Mockito.when(session.createQuery(QueryConstants.UPDATE_ITEM_AMOUNT_IN_STORE)).thenReturn(query);

        Mockito.when(query.setParameter(Mockito.anyString(), Mockito.anyInt())).thenReturn(query);
        
        JoinedItemInterface item = new JoinedItem();
        item.setItem(book);
        item.setStore(store);
        
        itemDAO.decreaseItemAmountInStore(store, 4);
        
        Mockito.verify(query, Mockito.times(1)).executeUpdate();
    }
    
    @Test
    void testShouldReturnBook(){
        
        Mockito.when(session.beginTransaction()).thenReturn(transaction);
        Mockito.when(factory.openSession()).thenReturn(session);

        Mockito.when(query.getResultList()).thenReturn(mockedList);
        Mockito.when(session.createQuery(QueryConstants.QERY_ALL_ITEMS_FROM_REPOSITORY)).thenReturn(query);
        
        List<JoinedItemInterface> result = itemDAO.listItems();
        
        Assert.assertEquals(result.size(), 1);
        Assert.assertEquals(result.get(0).getItem(), book);
    }
}
