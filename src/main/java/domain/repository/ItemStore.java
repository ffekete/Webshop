package domain.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import config.queries.QueryConstants;
import domain.ItemInterface;
import domain.JoinedItem;
import domain.JoinedItemInterface;
import domain.StoreInterface;

public class ItemStore implements ItemRepositoryInterface{

    private SessionFactory factory;
    
    public ItemStore(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }
    
    public List<JoinedItemInterface> listItems() {
        Session session = factory.openSession();
        Transaction tx = null;
        
        List<JoinedItemInterface> items = new ArrayList<JoinedItemInterface>();
        
        try{
           tx = session.beginTransaction();
           @SuppressWarnings("rawtypes")
           Iterator iteratorForPairOfItemAndStore = session.createQuery(QueryConstants.QERY_ALL_ITEMS_FROM_REPOSITORY).getResultList().iterator();
           
           JoinedItemInterface joinedResult = null;
           while(iteratorForPairOfItemAndStore.hasNext()){
               Object[] actualPairOfItemAndStore = (Object[])iteratorForPairOfItemAndStore.next();
               joinedResult = new JoinedItem();
               joinedResult.setItem((ItemInterface)actualPairOfItemAndStore[0]);
               joinedResult.setStore((StoreInterface)actualPairOfItemAndStore[1]);
               items.add(joinedResult);
           }
           
           tx.commit();
        }catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }finally {
           session.close(); 
        }        
 
        return items;
    }

    @Override
    public ItemInterface findElementById(int id) {
        ItemInterface result = null;
        
        Session session = factory.openSession();
        Transaction tx = null;
        
        try{
           tx = session.beginTransaction();
           result = (ItemInterface) session.createQuery(QueryConstants.QERY_ITEM_BY_ID_FROM_REPOSITORY + id).getSingleResult();
           tx.commit();
        }catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }finally {
           session.close(); 
        } 
        
        return result;
    }
}
