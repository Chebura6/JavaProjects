package Lab3.SpringData.Controllers;


import Lab3.SpringData.Entities.CatOwner;
import Lab3.SpringData.Services.CatOwnerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api/catOwner")
@Api(value = "Cat owner resources",description = "CRUD operations")
public class CatOwnerController {
    @Autowired
    private CatOwnerService catOwnerService;
    @GetMapping("/getAll")
    public List<CatOwner> allCatOwners()
    {
        return catOwnerService.getAllCatOwners();
    }

    @PostMapping("/createNew")
    public CatOwner newCatOwner(@RequestBody CatOwner catOwner) {
        return catOwnerService.saveCatOwner(catOwner);
    }

    @GetMapping("/find/{id}")
    public CatOwner findCatOwner(@PathVariable Integer id) {
        return catOwnerService.findCatOwnerById(id);
    }

    @PutMapping("/update/{id}")
    public void updateCatOwner(@PathVariable Integer id, @RequestBody CatOwner catOwner) {
        catOwnerService.updateCatOwner(id, catOwner);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCatOwner(@PathVariable Integer id) throws Exception {
        this.catOwnerService.deleteCatOwnerById(id);
    }
    @GetMapping("/getByName/{name}")
    public Collection<CatOwner> allCatOwnersByName(@PathVariable String name)
    {
        return catOwnerService.getAllByName(name);
    }
}
