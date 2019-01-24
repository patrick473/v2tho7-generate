package App.Controllers;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import App.Services.GenerateService;


@RestController
public class GenerateController{
    

    
    @RequestMapping(value ="/generate/{databaseid}", method = RequestMethod.POST, produces = "application/text")
    public String newType(@PathVariable int databaseid){
        
        GenerateService gService = new GenerateService();

        String result = gService.generateIntoDatabase(databaseid);
       
      return result;
    }
   

}