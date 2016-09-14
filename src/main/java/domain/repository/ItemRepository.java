package domain.repository;

import java.util.List;

import domain.ItemInterface;

public interface ItemRepository {
    
    public List<ItemInterface> listItems();
    
    public ItemInterface findElementById(int id);
}
