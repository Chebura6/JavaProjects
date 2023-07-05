package Lab3.SpringData.Services;

import Lab3.SpringData.Interfaces.CatOwnerRepository;
import Lab3.SpringData.Entities.CatOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatOwnerService {

    @Autowired
    private CatOwnerRepository catOwnerRepository;

    public CatOwnerService() {}

    public List<CatOwner> getAllCatOwners() {
        return catOwnerRepository.findAll();
    }
    public CatOwner saveCatOwner(CatOwner catOwner) {
        return catOwnerRepository.save(catOwner);
    }
    public void updateCatOwner(Integer id, CatOwner catOwner) {
        catOwnerRepository.update(id, catOwner.getName(), catOwner.getDateOfBirth());
    }
    public void deleteCatOwnerById(Integer id) {
        catOwnerRepository.deleteById(id);
    }
    public CatOwner findCatOwnerById(Integer id) {
        return catOwnerRepository.findById(id).get();
    }
    public List<CatOwner> getAllByName(String name) {
        return catOwnerRepository.getAllByName(name);
    }
}
