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
import org.testng.annotations.Test;

import config.queries.QueryConstants;
import domain.Item;
import domain.ItemInterface;

public class ItemStoreTest{

    @Mock
    SessionFactory factory;
    
    @Mock
    Session session;
    
    @Mock
    Transaction transaction;
    
    @Mock
    Query<ItemInterface> query;
    
    private List<ItemInterface> mockedList = new LinkedList<ItemInterface>();

    ItemStore itemStore ;
    
    ItemInterface book;
    
    @BeforeClass
    void setup(){
        MockitoAnnotations.initMocks(this);
        
        itemStore = new ItemStore(factory);
        
        book = new Item();
        book.setId(1);
        book.setAvailable(true);
        book.setDescription("Description text");
        book.setName("Book");
        book.setPrice(201.05);
        mockedList.add(book);
    }
    
    @Test
    void testShouldReturnBook(){
        
        Mockito.when(session.beginTransaction()).thenReturn(transaction);
        Mockito.when(factory.openSession()).thenReturn(session);

        Mockito.when(query.getResultList()).thenReturn(mockedList);
        Mockito.when(session.createQuery(QueryConstants.QERY_ALL_ITEMS_FROM_REPOSITORY)).thenReturn(query);
        
        List<ItemInterface> result = itemStore.listItems();
        
        Assert.assertEquals(result.size(), 1);
        Assert.assertEquals(result.get(0), book);
    }
}
