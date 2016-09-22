package service;

public interface ItemManagerInterface {

    void removeItemFromCart(int id);

    void addItemToCart(Integer id, Integer quantity);

}