package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import config.UrlConstants;
import config.view.LogicalViewNames;

@Controller
@RequestMapping(path=UrlConstants.DETAILS_URL)
public class DetailsController {
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getDatailsOfItem(@RequestParam Integer id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(LogicalViewNames.DETAILS_VIEW_LOGICAL_NAME);
        return modelAndView;
    }
}
