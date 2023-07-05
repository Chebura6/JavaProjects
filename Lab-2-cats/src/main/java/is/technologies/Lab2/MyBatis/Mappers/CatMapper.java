package is.technologies.Lab2.MyBatis.Mappers;

import is.technologies.Lab2.Interfaces.Cat;
import is.technologies.Lab2.MyBatis.Models.MyBatisCat;
import org.apache.ibatis.annotations.*;

import java.util.*;

public interface CatMapper {
    @Insert("INSERT INTO Cat (CATNAME, DATEOFBIRTH, BREED, COLOR, OWNERID) VALUES (#{Name}, #{DateOfBirth}, #{Breed}, #{Color}, #{Owner.Id})")
    void save(Cat cat);

    @Select("SELECT catid FROM public.cat WHERE catid = (SELECT MAX(catid) FROM public.cat)")
    @Results(value = {@Result(property = "id", column = "catid")})
    int getLastId(Cat cat);

    @Delete("Delete from public.cat where catid = #{id}")
    void deleteById(int id);

    @Delete("DELETE FROM public.cat WHERE public.cat.catname = #{Name} AND public.cat.dateofbirth = #{DateOfBirth} " +
            "AND public.cat.breed = #{Breed} " +
            "AND public.cat.color = #{Color} " +
            "AND public.cat.ownerid = #{Owner.Id}")
    void deleteByEntity(Cat cat);

    @Delete("DELETE FROM public.cat")
    void deleteAllCats();

    @Update("UPDATE public.cat SET public.cat.dateofbirth = #{DateOfBirth}, breed = #{Breed}, catname = #{Name}, color = #{Color}, ownerid = #{Owner.Id} where catid = #{id}")
    void update(Cat cat, int id);

    @Select("SELECT * FROM public.cat WHERE catid = #{id}")
    @Results(value = {
            @Result(property = "Id", column = "catid"),
            @Result(property = "DateOfBirth", column = "datirtheofb"),
            @Result(property = "Breed", column = "breed"),
            @Result(property = "Name", column = "catname"),
            @Result(property = "Color", column = "color"),
    })
    MyBatisCat getById(int id);

    @Select("SELECT * FROM public.cat")
    @ResultType(List.class)
    @Results(value = {
            @Result(property = "Id", column = "catid"),
            @Result(property = "DateOfBirth", column = "datirtheofb"),
            @Result(property = "Breed", column = "breed"),
            @Result(property = "Name", column = "catname"),
            @Result(property = "Color", column = "color"),
    })
    List<MyBatisCat> getAll();

    @Select("SELECT * FROM public.cat WHERE ownerid = #{id}")
    @ResultType(List.class)
    @Results(value = {
            @Result(property = "Id", column = "catid"),
            @Result(property = "DateOfBirth", column = "datirtheofb"),
            @Result(property = "Breed", column = "breed"),
            @Result(property = "Name", column = "catname"),
            @Result(property = "Color", column = "color"),
    })
    List<MyBatisCat> getByOwnerId(int id);
}