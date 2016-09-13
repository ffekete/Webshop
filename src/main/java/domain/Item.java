package domain;

public class Item implements ItemInterface{
    private int id;
    
    private double price;
    
    private String name;
    
    private String description;
    
    private boolean available;
    
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

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return this.available;
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

}
