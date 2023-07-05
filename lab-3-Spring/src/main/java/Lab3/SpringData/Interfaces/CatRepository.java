package Lab3.SpringData.Interfaces;//package is.technologies.Lab3.SpringData.Interfaces;

import Lab3.SpringData.Models.Cat;
import Lab3.SpringData.Models.CatOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat,Integer > {

//    public List<Cat> getAllByCatOwnerId(Integer id);
    public List<Cat> getAllByBreed(String breed);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Cat SET name = :name , dateOfBirth = :dateOfBirth, " +
            "breed = :breed, color = :color," +
            "tailLenght = :taillenght where id = :id")
    void update(@Param("id") Integer id_, @Param("name") String name_,
                @Param("dateOfBirth") Date dateOfBirth_, @Param("breed") String breed_,
                @Param("color") String color_, @Param("taillenght") Integer taillenght_);

}
