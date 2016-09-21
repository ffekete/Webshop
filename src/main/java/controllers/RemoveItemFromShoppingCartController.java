package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import config.UrlConstants;
import domain.ShoppingCartInterface;
import domain.StoreInterface;
import domain.repository.ItemDAOInterface;

@Controller
@RequestMapping(path=UrlConstants.REMOVE_ITEM_FROM_CART_URL)
public class RemoveItemFromShoppingCartController {

    private ShoppingCartInterface shoppingCart;
    
    private ItemDAOInterface itemDAO;
    
    @Autowired
    public RemoveItemFromShoppingCartController(ShoppingCartInterface shoppingCart, ItemDAOInterface itemDAO) {
        this.shoppingCart = shoppingCart;
        this.itemDAO = itemDAO;
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public String removeItemfromShoppingCart(@RequestParam(name="id") int id){
        shoppingCart.removeElementById(id);
        StoreInterface storeEntry = itemDAO.findStoreEntryForItemId(id);
        itemDAO.decreaseItemAmountInStore(storeEntry, -1);
        return "redirect:list.html";
    }
}
