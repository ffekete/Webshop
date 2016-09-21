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
@RequestMapping(path=UrlConstants.ADD_ITEM_TO_CART_URL)
public class AddItemToShoppingCartController {

    private ShoppingCartInterface shoppingCart;
    
    private ItemDAOInterface itemDao;
    
    @Autowired
    public AddItemToShoppingCartController(ShoppingCartInterface shoppingCart, ItemDAOInterface itemDao) {
        this.shoppingCart = shoppingCart;
        this.itemDao = itemDao;
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public String additemToShoppingCart(@RequestParam(name="id", required=true) int id, @RequestParam(name="quantity", required=false) Integer quantity){
        StoreInterface storeEntry = null;
        String resultViewName = "redirect:list.html";
        if(quantity!=null){
            storeEntry = this.itemDao.findStoreEntryForItemId(id);
            for(int i = 0; i < quantity; i++){
                shoppingCart.addItemById(id);
            }
            this.itemDao.decreaseItemAmountInStore(storeEntry, quantity);
        }
        else
        {
            resultViewName = "error";
        }
        
        return resultViewName;
    }
}
