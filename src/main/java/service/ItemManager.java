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
    
    /* (non-Javadoc)
     * @see service.ItemManagerInterface#removeItemFromCart(int)
     */
    @Override
    public void removeItemFromCart(int id){
        shoppingCart.removeElementById(id);
        StoreInterface storeEntry = itemDao.findStoreEntryForItemId(id);
        itemDao.decreaseItemAmountInStore(storeEntry, -1);
    }
    
    /* (non-Javadoc)
     * @see service.ItemManagerInterface#addItemToCart(java.lang.Integer, java.lang.Integer)
     */
    @Override
    public boolean addItemToCart(Integer id, Integer quantity){
        StoreInterface storeEntry = null;
        
        if(quantity!=null && id != null){
            storeEntry = this.itemDao.findStoreEntryForItemId(id);
            for(int i = 0; i < quantity; i++){
                shoppingCart.addItemById(id);
            }
            this.itemDao.decreaseItemAmountInStore(storeEntry, quantity);
            return true;
        }
        return false;
    }
}
