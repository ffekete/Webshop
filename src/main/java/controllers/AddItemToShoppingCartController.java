package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import config.UrlConstants;
import service.ItemManager;

@Controller
@RequestMapping(path=UrlConstants.ADD_ITEM_TO_CART_URL)
public class AddItemToShoppingCartController {

    @Autowired
    private ItemManager itemManager; 
    
    @RequestMapping(method=RequestMethod.GET)
    public String additemToShoppingCart(@RequestParam(name="id", required=true) int id, @RequestParam(name="quantity", required=false) Integer quantity){
        String resultViewName = "redirect:list.html";
        boolean result = itemManager.addItemToCart(id, quantity);
        
        if(result == false)
        {
            resultViewName = "error";
        }
        
        return resultViewName;
    }
}
