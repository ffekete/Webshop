package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import config.UrlConstants;
import config.view.LogicalViewNames;
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
    public ModelAndView removeItemfromShoppingCart(@RequestParam(name="id") Integer id){
        ModelAndView modelAndView = new ModelAndView();
        itemManager.removeItemFromCart(id);
        modelAndView.setViewName(LogicalViewNames.REDIRECT_TO_LIST_VIEW_LOGICAL_NAME);
        return modelAndView;
    }
}
