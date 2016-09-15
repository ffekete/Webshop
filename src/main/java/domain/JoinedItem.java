package domain;

public class JoinedItem implements JoinedItemInterface {

    private ItemInterface item;
    
    private StoreInterface store;

    @Override
    public void setItem(ItemInterface item) {
        this.item = item;        
    }

    @Override
    public ItemInterface getItem() {
        return this.item;
    }

    @Override
    public void setStore(StoreInterface store) {
        this.store = store;        
    }

    @Override
    public StoreInterface getStore() {
        return this.store;
    }
}
