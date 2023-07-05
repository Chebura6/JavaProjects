package is.technologies.Lab2.MyBatis.Mappers;

import is.technologies.Lab2.Interfaces.CatOwner;
import is.technologies.Lab2.MyBatis.Models.MyBatisCatOwner;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CatOwnerMapper {
    @Insert("INSERT INTO CatOwner (NAME, DATEOFBIRTH) VALUES (#{Name}, #{DateOfBirth})")
    void save(CatOwner catowner);

    @Select("SELECT ownerid FROM public.catowner WHERE ownerid = (SELECT MAX(ownerid) FROM public.catowner)")
    @Results(value = {@Result(property = "Id", column = "ownerid")})
    int getLastId(CatOwner catowner);

    @Update("UPDATE public.cat SET ownerid = NULL WHERE ownerid = #{id}")
    void deleteOldOwners(int id);

    @Update("UPDATE public.cat SET ownerid = NULL")
    void nullAllCats();

    @Delete("DELETE FROM public.catowner WHERE ownerid = #{id}")
    void deleteById(int id);

    @Delete("DELETE FROM public.catowner WHERE public.catowner.name = #{Name} AND public.catowner.dateofbirth = #{DateOfBirth}")
    void deleteByEntity(CatOwner catOwner);

    @Delete("DELETE FROM public.catowner")
    void deleteAll();

    @Update("UPDATE public.catowner SET dateofbirth = #{DateOfBirth}, name = #{Name} WHERE ownerid = #{id}")
    void update(CatOwner cat, int id);

    @Select("SELECT * FROM public.catowner WHERE ownerid = #{id}")
    @Results(value = {
            @Result(property = "Id", column = "ownerid"),
            @Result(property = "DateOfBirth", column = "dateofbirth"),
            @Result(property = "Name", column = "name"),
    })
    MyBatisCatOwner getById(int id);

    @Select("select * from public.catowner")
    @ResultType(List.class)
    @Results(value = {
            @Result(property = "Id", column = "ownerid"),
            @Result(property = "DateOfBirth", column = "dateofbirth"),
            @Result(property = "Name", column = "name"),
    })
    List<MyBatisCatOwner> getAll();
}
