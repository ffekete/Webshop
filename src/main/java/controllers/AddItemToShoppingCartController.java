package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import config.UrlConstants;
import config.requestparameters.RequestParameters;
import config.view.LogicalViewNames;
import service.ItemManagerInterface;

@Controller
@RequestMapping(path=UrlConstants.ADD_ITEM_TO_CART_URL)
public class AddItemToShoppingCartController {
    
    private ItemManagerInterface itemManager; 
    
    @Autowired
    public AddItemToShoppingCartController(ItemManagerInterface itemManager){
        this.itemManager = itemManager;
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public String additemToShoppingCart(@RequestParam(name=RequestParameters.ID, required=true) int id, @RequestParam(name=RequestParameters.QUANTITY, required=false) Integer quantity){
        itemManager.addItemToCart(id, quantity);

        return LogicalViewNames.REDIRECT_TO_LIST_VIEW_LOGICAL_NAME;
    }
}
