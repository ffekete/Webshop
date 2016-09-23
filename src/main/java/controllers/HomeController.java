package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import config.UrlConstants;
import config.view.LogicalViewNames;

@Controller
@RequestMapping(path=UrlConstants.HOME_URL)
public class HomeController {
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getHomepage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(LogicalViewNames.HOME_VIEW_LOGICAL_NAME);
        return modelAndView;
    }
}
