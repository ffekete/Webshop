package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import config.UrlConstants;

@Controller
@RequestMapping(path=UrlConstants.HOME_URL)
public class HomeController {
    
    @RequestMapping(method = RequestMethod.GET)
    public String getHomepage(){
        return "index";
    }
}
