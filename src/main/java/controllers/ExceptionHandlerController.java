package controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import config.view.LogicalViewNames;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(Exception.class)
    public ModelAndView errorView(Exception ex, final HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("previousUrl", request.getHeader("referer"));
        mav.setViewName(LogicalViewNames.ERROR_VIEW_LOGICAL_NAME);
        return mav;
    }
    
}
