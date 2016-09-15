package domain;

public interface JoinedItemInterface{
    void setStore(StoreInterface store);
    StoreInterface getStore();
    
    void setItem(ItemInterface item);
    ItemInterface getItem();
}
