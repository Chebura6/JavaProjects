package Lab3.SpringData.Controllers;

import Lab3.SpringData.Entities.Flea;
import Lab3.SpringData.Services.FleaService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api/flea")
@Api(value = "Cat owner resources",description = "CRUD operations")
public class FleaController {

    @Autowired
    private FleaService fleaService;

    @GetMapping("/getAll")
    public Collection<Flea> allFleas() {
        return fleaService.getAllFleas();
    }

    @PutMapping("/createNew")
    public Flea newFlea(@RequestBody Flea flea) {
        return fleaService.saveFlea(flea);
    }

    @PutMapping("/update/{id}")
    public void updateFlea(@PathVariable Integer id, @RequestBody Flea flea) {
        fleaService.updateFlea(id, flea);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFlea(@PathVariable Integer id) {
        fleaService.findFleaById(id);
    }

    @GetMapping("/find/{id}")
    public Flea findFlea(@PathVariable Integer id) {
        return fleaService.findFleaById(id);
    }

    @GetMapping("/findFleasByCatId/{id}")
    public List<Flea> findFleasByCatId(@PathVariable Integer catId) {
        return fleaService.getAllFleasByCatId(catId);
    }

}
