package domain;

public interface StoreInterface {
    boolean isAvailable();
    int getMaxAmountOfItemSelectedById(int id);
    
    void setAmount(int amount);
    int getAmount();
    
    void setId(int id);
    int getId();
    
    public ItemInterface getItem();
    public void setItem(ItemInterface item);
}
