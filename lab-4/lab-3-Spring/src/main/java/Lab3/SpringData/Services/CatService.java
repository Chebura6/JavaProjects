package Lab3.SpringData.Services;

import Lab3.SpringData.Interfaces.CatRepository;
import Lab3.SpringData.Entities.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatService {
    @Autowired
    private CatRepository catRepository;

    public CatService() {}

    public List<Cat> getAllCats() {
        return catRepository.findAll();
    }
    public Cat saveCat(Cat cat) {
        return catRepository.save(cat);
    }
    public void updateCat(Integer id, Cat cat) {
        catRepository.update(id, cat.getName(), cat.getDateOfBirth(), cat.getBreed(), cat.getColor(), cat.getTailLenght());
    }
    public void deleteCatById(Integer id) {
        catRepository.deleteById(id);
    }
    public Cat findCatById(Integer id) {
        return catRepository.findById(id).get();
    }
    public List<Cat> getCatsByOwnerId(Long ownerId) { return catRepository.getCatsByOwnerId(ownerId); }
}
