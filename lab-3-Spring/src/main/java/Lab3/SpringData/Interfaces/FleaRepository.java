package Lab3.SpringData.Interfaces;


import Lab3.SpringData.Models.Flea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Repository
public interface FleaRepository extends JpaRepository<Flea, Integer> {

    public List<Flea> getAllByCatId(Integer catId);
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Flea SET name = :name ," +
            "catId = :catid where id = :id")
    void update(@Param("id") Integer id_, @Param("name") String name_,
                @Param("catid") Integer catid_);
}
