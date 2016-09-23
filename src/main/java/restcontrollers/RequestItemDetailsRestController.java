package restcontrollers;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import config.UrlConstants;
import domain.DetailsInterface;
import domain.JoinedItemInterface;
import domain.repository.ItemDAOInterface;
import transformer.JoinedItemToDetailsTransformer;

@RestController
@RequestMapping(path=UrlConstants.REQUEST_DETAILS_REST_URL)
public class RequestItemDetailsRestController {
   
    private ItemDAOInterface itemDAO;
    
    public RequestItemDetailsRestController(ItemDAOInterface itemDAO) {
        this.itemDAO = itemDAO;
    }
    
    @RequestMapping(method=RequestMethod.GET)
    @ResponseBody
    public String getResult(@RequestParam(name="id") Integer id) throws JsonGenerationException, JsonMappingException, IOException{
        
        JoinedItemInterface item = itemDAO.findJoinedItemById(id);
        DetailsInterface details = JoinedItemToDetailsTransformer.transform(item);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(details);
    }
}
