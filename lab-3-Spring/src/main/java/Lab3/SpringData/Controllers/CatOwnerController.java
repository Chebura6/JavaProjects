package Lab3.SpringData.Controllers;


import Lab3.SpringData.Interfaces.CatOwnerRepository;
import Lab3.SpringData.Models.CatOwner;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/catOwner")
@Api(value = "Cat owner resources",description = "CRUD operations")
public class CatOwnerController {
    @Autowired
    private CatOwnerRepository catOwnerRepository;
    @GetMapping("/catOwners")
    public List<CatOwner> allCarOwners()
    {
        return catOwnerRepository.findAll();
    }

    @PostMapping("/catOwners")
    public CatOwner newCatOwner(@RequestBody CatOwner catOwner) {
        return catOwnerRepository.save(catOwner);
    }

    @GetMapping("/catOwners/{id}")
    public CatOwner findCatOwner(@PathVariable Integer id) {
        return catOwnerRepository.findById(id).get();
    }

    @PutMapping("/catOwners/{id}")
    public void updateCatOwner(@PathVariable Integer id, @RequestBody CatOwner catOwner) {
        catOwnerRepository.update(id, catOwner.getName(), catOwner.getDateOfBirth());
    }

    @DeleteMapping("/catOwners/{id}")
    public void delete(@PathVariable Integer id) throws Exception {
        this.catOwnerRepository.deleteById(id);
    }
    @GetMapping("/catOwners/Name/{name}")
    public Collection<CatOwner> allCatOwnersByName(@PathVariable String name)
    {
        return catOwnerRepository.getAllByName(name);
    }
}
