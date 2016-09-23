package controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import config.UrlConstants;
import config.view.LogicalViewNames;
import domain.ShoppingCartInterface;
import domain.repository.ItemDAOInterface;

@Controller
@RequestMapping(path = UrlConstants.LIST_ITEMS_IN_WEBSHOP_URL)
public class ListItemsController {

    private ItemDAOInterface itemStore;
    private ShoppingCartInterface shoppingCart;
    private ResourceBundleMessageSource messageSource;
    
    @Autowired
    public ListItemsController(ItemDAOInterface itemStore, ShoppingCartInterface shoppingCart, ResourceBundleMessageSource messageSource){
        this.itemStore = itemStore;
        this.shoppingCart = shoppingCart;
        this.messageSource = messageSource;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listItems(){
        ModelAndView modelAndView = new ModelAndView();
        String addItemActionUrl = messageSource.getMessage("listview.add_item_action", null, Locale.getDefault());
        String removeItemFromCartActionUrl = messageSource.getMessage("listview.remove_item_from_cart_action", null, Locale.getDefault());
        modelAndView.addObject("addItemActionUrl", addItemActionUrl);
        modelAndView.addObject("removeItemFromCartActionUrl", removeItemFromCartActionUrl);
        modelAndView.addObject("itemStoreModel", itemStore.listItems());
        modelAndView.addObject("shoppingCartModel", shoppingCart.getAllItems());
        modelAndView.setViewName(LogicalViewNames.LIST_VIEW_LOGICAL_NAME);
        return modelAndView;
    }
    
    
}
