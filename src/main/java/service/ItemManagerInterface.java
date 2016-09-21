package service;

public interface ItemManagerInterface {

    void removeItemFromCart(int id);

    boolean addItemToCart(Integer id, Integer quantity);

}