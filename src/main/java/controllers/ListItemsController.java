package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import config.ModelNameConstants;
import config.UrlConstants;
import domain.ItemInterface;
import domain.ShoppingCartInterface;
import domain.repository.ItemRepository;

@Controller
@RequestMapping(path = UrlConstants.LIST_ITEMS_IN_WEBSHOP_URL)
public class ListItemsController {

    private ItemRepository itemStore;
    private ShoppingCartInterface shoppingCart;
    
    @Autowired
    public ListItemsController(ItemRepository itemStore, ShoppingCartInterface shoppingCart){
        this.itemStore = itemStore;
        this.shoppingCart = shoppingCart;
    }
    
    @ModelAttribute(name=ModelNameConstants.ITEM_STORE_MODEL_NAME)
    public List<ItemInterface> getItemsFromStore(){
        return itemStore.listItems();
    }
    
    @ModelAttribute(name=ModelNameConstants.SHOPPING_CART_MODEL_NAME)
    public List<ItemInterface> getShoppingCartContent(){
        return shoppingCart.getAllItems();
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String listItems(){
        return "list";
    }
    
    
}
