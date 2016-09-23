package domain;

public class Details implements DetailsInterface{
    private double price;
    
    private String name;
    
    private String description;
    
    private int amount;
    
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

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public int getAmount() {
        return this.amount;
    }
}
