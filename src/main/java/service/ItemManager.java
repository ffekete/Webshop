package service;

import domain.ShoppingCartInterface;
import domain.StoreInterface;
import domain.repository.ItemDAOInterface;

public class ItemManager implements ItemManagerInterface {
    private ShoppingCartInterface shoppingCart;

    private ItemDAOInterface itemDao;
    
    public ItemManager(ItemDAOInterface itemDao, ShoppingCartInterface shoppingCart) {
        this.shoppingCart = shoppingCart;
        this.itemDao = itemDao;
    }
    
    @Override
    public void removeItemFromCart(int id){
        shoppingCart.removeElementById(id);
        StoreInterface storeEntry = itemDao.findStoreEntryForItemId(id);
        itemDao.decreaseItemAmountInStore(storeEntry, -1);
    }
    
    @Override
    public void addItemToCart(Integer itemId, Integer quantity){
        StoreInterface storeEntry = null;
        
        if(quantity!=null && itemId != null){
            storeEntry = this.itemDao.findStoreEntryForItemId(itemId);
            for(int i = 0; i < quantity; i++){
                shoppingCart.addItemById(itemId);
            }
            this.itemDao.decreaseItemAmountInStore(storeEntry, quantity);
        }
        else
        {
            throw new NullPointerException("Item id or quantity parameter is missing from the query parameters list!");
        }
    }
}
