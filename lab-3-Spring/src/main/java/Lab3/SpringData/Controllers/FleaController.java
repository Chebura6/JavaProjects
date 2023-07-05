package Lab3.SpringData.Controllers;

import Lab3.SpringData.Interfaces.CatRepository;
import Lab3.SpringData.Interfaces.FleaRepository;
import Lab3.SpringData.Models.Cat;
import Lab3.SpringData.Models.Flea;
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
    private FleaRepository fleaRepository;

    @GetMapping("/fleas")
    public Collection<Flea> allFleas() {
        return fleaRepository.findAll();
    }

    @PutMapping("/fleas")
    public Flea newFlea(@RequestBody Flea flea) {
        return fleaRepository.save(flea);
    }

    @PutMapping("/fleas/{id}")
    public void updateFlea(@PathVariable Integer id, @RequestBody Flea flea) {
        fleaRepository.update(id, flea.getName(), flea.getCatId());
    }

    @DeleteMapping("/fleas/{id}")
    public void deleteFlea(@PathVariable Integer id) {
        fleaRepository.deleteById(id);
    }

    @GetMapping("/fleas/{id}")
    public Flea findFlea(@PathVariable Integer id) {
        return fleaRepository.findById(id).get();
    }

    public List<Flea> findFleasByCatId(Integer catId) {
        return fleaRepository.getAllByCatId(catId);
    }

}
