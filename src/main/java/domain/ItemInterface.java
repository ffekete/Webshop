package domain;

public interface ItemInterface {
    
    void setPrice(double price);
    double getPrice();
    
    void setName(String name);
    String getName();
    
    void setAvailable(boolean available);
    boolean isAvailable();
    
    void setDescription(String description);
    String getDescription();
    
    int getId();
    void setId(int id);
}