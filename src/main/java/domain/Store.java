package domain;

public class Store implements StoreInterface{
    private int id;
    private int amount;
    private ItemInterface item;
    
    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public int getMaxAmountOfItemSelectedById(int id) {
        return 0;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;        
    }

    @Override
    public int getAmount() {
        return this.amount;
    }

    @Override
    public void setId(int id) {
        this.id = id;        
    }

    @Override
    public int getId() {
        return this.id;
    }

    public ItemInterface getItem() {
        return item;
    }

    public void setItem(ItemInterface item) {
        this.item = item;
    }

}
