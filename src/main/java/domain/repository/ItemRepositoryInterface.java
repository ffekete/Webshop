package domain.repository;

import java.util.List;

import domain.ItemInterface;
import domain.JoinedItemInterface;

public interface ItemRepositoryInterface {
    
    public List<JoinedItemInterface> listItems();
    
    public ItemInterface findElementById(int id);
}
