package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import config.UrlConstants;
import service.ItemManagerInterface;

@Controller
@RequestMapping(path=UrlConstants.REMOVE_ITEM_FROM_CART_URL)
public class RemoveItemFromShoppingCartController {

    private ItemManagerInterface itemManager;
    
    @Autowired
    public RemoveItemFromShoppingCartController(ItemManagerInterface itemManager) {
        this.itemManager = itemManager;
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public String removeItemfromShoppingCart(@RequestParam(name="id") int id){
        itemManager.removeItemFromCart(id);
        return "redirect:list.html";
    }
}
