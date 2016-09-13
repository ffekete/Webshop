package domain.repository;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import config.queries.QueryConstants;
import domain.ItemInterface;

public class ItemStore implements ItemRepository{

    private SessionFactory factory;
    
    public ItemStore(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }
    
    @SuppressWarnings("unchecked")
    public List<ItemInterface> listItems() {
        Session session = factory.openSession();
        Transaction tx = null;
        
        List<ItemInterface> items = null;
        
        try{
           tx = session.beginTransaction();
           items = session.createQuery(QueryConstants.QERY_ALL_ITEMS_FROM_REPOSITORY).getResultList();
           tx.commit();
        }catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }finally {
           session.close(); 
        }        
        
        return items;
    }
}
