package Lab3.SpringData.Interfaces;

import Lab3.SpringData.Models.CatOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public interface CatOwnerRepository extends JpaRepository<CatOwner, Integer> {
    ArrayList<CatOwner> getAllByName(String name);
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE CatOwner SET name = :name , dateOfBirth = :dateOfBirth where id = :id")
    void update(@Param("id") Integer id, @Param("name") String name, @Param("dateOfBirth") Date dateOfBirth);

}
