package domain;

import java.util.Iterator;
import java.util.List;

import domain.repository.ItemDAOInterface;

public class ShoppingCart implements ShoppingCartInterface{

    private ItemDAOInterface itemRepository;
    
    private List<ItemInterface> content;
    
    public ShoppingCart(List<ItemInterface> content, ItemDAOInterface itemRepository){
        this.content = content;
        this.itemRepository = itemRepository;
    }
    
    public void add(ItemInterface item) {
        content.add(item);
    }

    public int getItemsCount() {
        return this.content.size();
    }

    @Override
    public List<ItemInterface> getAllItems() {
        return content;
    }

    @Override
    public void addItemById(int id) {
        ItemInterface item = itemRepository.findElementById(id);

        if(item != null){
            content.add(item);
        }
        else
        {
            throw new NullPointerException("Error, item id " + id +" cannot be found in repository!");
        }
    }
    
    public void clear(){
        content.clear();
    }

    public ItemInterface selectItemById(int id){
        Iterator<ItemInterface> iterator = content.iterator();
        while(iterator.hasNext()){
            ItemInterface item = iterator.next();
            if(item.getId() == id){
                return item;
            }
        }
        return null;
    }
    
    @Override
    public void removeElementById(int id) {
        ItemInterface itemToRemove = selectItemById(id);
        
        if(itemToRemove == null){
            throw new NullPointerException("Item cannot be removed with id " + id + ", because it does not exist in the shopping cart!");
        }
        
        content.remove(itemToRemove);
    }

}
