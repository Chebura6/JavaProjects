package Lab3.SpringData.Controllers;


import Lab3.SpringData.Interfaces.CatRepository;
import Lab3.SpringData.Models.Cat;
import Lab3.SpringData.Models.CatOwner;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api/cat")
@Api(value = "Cat owner resources",description = "CRUD operations")
public class CatController {

    @Autowired
    private CatRepository catRepository;

    @GetMapping("/cats")
    public Collection<Cat> allCats() {
        return catRepository.findAll();
    }

    @PutMapping("/cats")
    public Cat newCat(@RequestBody Cat cat) {
        return catRepository.save(cat);
    }

    @PutMapping("/cats/{id}")
    public void updateCat(@PathVariable Integer id, @RequestBody Cat cat) {
        catRepository.update(id, cat.getName(), cat.getDateOfBirth(), cat.getBreed(), cat.getColor(), cat.getTailLenght());
    }

    @DeleteMapping("/cats/{id}")
    public void deleteCat(@PathVariable Integer id) {
        catRepository.deleteById(id);
    }

    @GetMapping("/cats/{id}")
    public Cat findCat(@PathVariable Integer id) {
        return catRepository.findById(id).get();
    }

//    @GetMapping("/cats/owner/{id}")
//    public List<Cat> findCatsByOwnerId(Integer ownerId) {
//        return catRepository.getAllByCatOwnerId(ownerId);
//    }


}
