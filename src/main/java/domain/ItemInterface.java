package domain;


public interface ItemInterface {
    void setPrice(double price);
    double getPrice();
    
    void setName(String name);
    String getName();
    
    void setDescription(String description);
    String getDescription();
    
    int getId();
    void setId(int id);
    
    StoreInterface getStore();
    void setStore(StoreInterface store);
}