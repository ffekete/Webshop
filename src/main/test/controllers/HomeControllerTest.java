package controllers;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import config.UrlConstants;
import controllers.HomeController;

public class HomeControllerTest{

    private MockMvc mvc;

    @BeforeClass
    void setup() {
        HomeController homeController = new HomeController();
        mvc = MockMvcBuilders.standaloneSetup(homeController).build();
    }
    
    @Test
    void testPerformGetHomepageShouldReturnIndexPage() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get(UrlConstants.HOME_URL)).andExpect(MockMvcResultMatchers.view().name("index"));
    }
}
