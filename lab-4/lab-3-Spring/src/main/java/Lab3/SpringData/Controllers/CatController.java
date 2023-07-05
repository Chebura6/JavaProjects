package Lab3.SpringData.Controllers;


import Lab3.SpringData.Entities.Cat;
import Lab3.SpringData.Entities.User;
import Lab3.SpringData.Interfaces.UserRepository;
import Lab3.SpringData.Services.CatService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collection;

@RestController
@RequestMapping("api/cat")
@Api(value = "Cat owner resources",description = "CRUD operations")
public class CatController {

    @Autowired
    private CatService catService;
    private HttpServletRequest request;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public void setRequest(HttpServletRequest request1)
    {
        this.request = request1;
    }

    @GetMapping("/getAll")
    public Collection<Cat> allCats() {
        if (request.isUserInRole("ADMIN")) {
            return catService.getAllCats();
        }

        User user = userRepository.findByUsername(request.getUserPrincipal().getName());
        System.out.println("hello");
        var result = catService.getCatsByOwnerId(user.getCatOwner().getId());
        return result;

    }

    @PutMapping("/createNew")
    public Cat newCat(@RequestBody Cat cat) {
        return catService.saveCat(cat);
    }

    @PutMapping("/update/{id}")
    public void updateCat(@PathVariable Integer id, @RequestBody Cat cat) {
        catService.updateCat(id, cat);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCat(@PathVariable Integer id) {
        catService.deleteCatById(id);
    }

    @GetMapping("/find/{id}")
    public Cat findCat(@PathVariable Integer id) {
        return catService.findCatById(id);
    }

//    @GetMapping("/cats/owner/{id}")
//    public List<Cat> findCatsByOwnerId(Integer ownerId) {
//        return catRepository.getAllByCatOwnerId(ownerId);
//    }


}
