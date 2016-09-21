package domain.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import config.queries.QueryConstants;
import domain.ItemInterface;
import domain.JoinedItem;
import domain.JoinedItemInterface;
import domain.StoreInterface;

public class ItemDAO implements ItemDAOInterface{

    private SessionFactory factory;
    
    public ItemDAO(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }
    
    public List<JoinedItemInterface> listItems() {
        Session session = factory.openSession();
        Transaction tx = null;
        
        List<JoinedItemInterface> items = new ArrayList<JoinedItemInterface>();
        
        try{
           tx = session.beginTransaction();
           Iterator<?> iteratorForPairOfItemAndStore = session.createQuery(QueryConstants.QERY_ALL_ITEMS_FROM_REPOSITORY).getResultList().iterator();
           
           JoinedItemInterface joinedResult = null;
           while(iteratorForPairOfItemAndStore.hasNext()){
               Object[] actualPairOfItemAndStore = (Object[])iteratorForPairOfItemAndStore.next();
               joinedResult = new JoinedItem();
               joinedResult.setItem((ItemInterface)actualPairOfItemAndStore[0]);
               joinedResult.setStore((StoreInterface)actualPairOfItemAndStore[1]);
               items.add(joinedResult);
           }
           
           tx.commit();
        }catch (HibernateException|ClassCastException|NullPointerException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }
        finally {
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

    @Override
    public void decreaseItemAmountInStore(StoreInterface storeEntry, int amount) {
        if(storeEntry.getAmount() < amount){
            StringBuilder sb = new StringBuilder();
            sb.append("Requested amount is greater than actual amount in store for item ");
            throw new IllegalArgumentException(sb.toString());
        }
        
        Session session = factory.openSession();
        Transaction tx = null;
        
        try{
           tx = session.beginTransaction();
           Query<?> query = session.createQuery(QueryConstants.UPDATE_ITEM_AMOUNT_IN_STORE);
           query.setParameter("id", storeEntry.getId());
           query.setParameter("newAmount", storeEntry.getAmount() - amount);
           query.executeUpdate();
           tx.commit();
        }catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }finally {
           session.close(); 
        }
    }

    @Override
    public StoreInterface findStoreEntryForItemId(int itemId) {
        StoreInterface result = null;
        
        Session session = factory.openSession();
        Transaction tx = null;
        
        try{
           tx = session.beginTransaction();
           Query<?> query = session.createQuery(QueryConstants.QERY_STORE_BY_ITEM_ID_FROM_REPOSITORY);
           query.setParameter("id", itemId);
           result = (StoreInterface) query.getSingleResult();
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
