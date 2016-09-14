package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import config.UrlConstants;
import domain.ShoppingCartInterface;

@Controller
@RequestMapping(path=UrlConstants.ADD_ITEM_TO_CART_URL)
public class AddItemToShoppingCartController {

    private ShoppingCartInterface shoppingCart;
    
    @Autowired
    public AddItemToShoppingCartController(ShoppingCartInterface shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public String additemToShoppingCart(@RequestParam(name="id") int id){
        shoppingCart.addItemById(id);
        
        return "redirect:list.html";
    }
}
