package domain;

public class Item implements ItemInterface{
    
    private StoreInterface store;
    
    private int id;
    
    private double price;
    
    private String name;
    
    private String description;
    
    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String description) {
        this.description = description;        
    }

    public String getDescription() {
        return this.description;
    }

    public int getId() {
        return this.id;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public StoreInterface getStore() {
        return store;
    }

    public void setStore(StoreInterface store) {
        this.store = store;
    }

}
