package App.Controllers;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GenerateController{
    

    @RequestMapping(value ="/generate/{id}", method = RequestMethod.POST, produces = "application/text")
    public String newType(@PathVariable int id){
        boolean result = false;
       
      return result+"";
    }
   

}