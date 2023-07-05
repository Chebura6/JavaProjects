package Lab3.SpringData.Services;

import Lab3.SpringData.Interfaces.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpringService {
    @Autowired
    private CatRepository CatRepository;

    public SpringService(CatRepository CatRepository) {
        this.CatRepository = CatRepository;
    }

//    @Transactional
//    public void saveCat(Cat cat) {
//        CatRepository.saveAndFlush(cat);
//    }

    public void setCatRepository(CatRepository catRepository) {
        this.CatRepository = catRepository;
    }
}
