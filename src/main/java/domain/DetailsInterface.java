package domain;


public interface DetailsInterface {
    void setPrice(double price);
    double getPrice();
    
    void setName(String name);
    String getName();
    
    void setDescription(String description);
    String getDescription();
    
    void setAmount(int amount);
    int getAmount();
}