package Lab3.SpringData.Services;


import Lab3.SpringData.Interfaces.FleaRepository;
import Lab3.SpringData.Entities.Flea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FleaService {
    @Autowired
    private FleaRepository fleaRepository;

    public FleaService() {}

    public List<Flea> getAllFleas() {
        return fleaRepository.findAll();
    }
    public Flea saveFlea(Flea flea) {
        return fleaRepository.save(flea);
    }
    public void updateFlea(Integer id, Flea flea) {
        fleaRepository.update(id, flea.getName(), flea.getCatId());
    }
    public void deleteFleaById(Integer id) {
        fleaRepository.deleteById(id);
    }
    public Flea findFleaById(Integer id) {
        return fleaRepository.findById(id).get();
    }
    public List<Flea> getAllFleasByCatId(Integer catId) {
        return fleaRepository.getAllByCatId(catId);
    }

}
