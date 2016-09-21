package domain.repository;

import java.util.List;

import domain.ItemInterface;
import domain.JoinedItemInterface;
import domain.StoreInterface;

public interface ItemDAOInterface {
    
    public List<JoinedItemInterface> listItems();
    
    public ItemInterface findElementById(int id);
    
    public void decreaseItemAmountInStore(StoreInterface storeEntry, int amount);
    
    public StoreInterface findStoreEntryForItemId(int itemId);
}
