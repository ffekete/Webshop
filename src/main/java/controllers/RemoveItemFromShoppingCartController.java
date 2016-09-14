package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import config.UrlConstants;
import domain.ShoppingCartInterface;

@Controller
@RequestMapping(path=UrlConstants.REMOVE_ITEM_FROM_CART_URL)
public class RemoveItemFromShoppingCartController {

    private ShoppingCartInterface shoppingCart;
    
    @Autowired
    public RemoveItemFromShoppingCartController(ShoppingCartInterface shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public String removeItemfromShoppingCart(@RequestParam(name="id") int id){
        shoppingCart.removeElementById(id);
        
        return "redirect:list.html";
    }
}
