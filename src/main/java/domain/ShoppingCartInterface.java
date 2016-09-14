package domain;

import java.util.List;

public interface ShoppingCartInterface {
    void add(ItemInterface item);
    int getItemsCount();
    List<ItemInterface> getAllItems();
    void addItemById(int id);
    void clear();
    void removeElementById(int id);
}
