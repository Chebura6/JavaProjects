package is.technologies.Lab2.MyBatis;

import is.technologies.Lab2.Interfaces.Cat;
import is.technologies.Lab2.Interfaces.CatOwner;
import is.technologies.Lab2.Interfaces.DataBaseOperations;
import is.technologies.Lab2.MyBatis.Models.MyBatisCatOwner;
import is.technologies.Lab2.MyBatis.Models.MyBatisConnectionManager;
import is.technologies.Lab2.MyBatis.Mappers.CatMapper;
import is.technologies.Lab2.MyBatis.Mappers.CatOwnerMapper;
import is.technologies.Lab2.MyBatis.Models.MyBatisCat;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyBatisService implements DataBaseOperations {
    MyBatisConnectionManager MyBatisConnectionManager;
    public MyBatisService(MyBatisConnectionManager myBatisConnectionManager) {
        if (myBatisConnectionManager == null) throw new NullPointerException();
        MyBatisConnectionManager = myBatisConnectionManager;
    }
    @Override
    public Cat saveCat(Cat cat) throws SQLException {
        try (SqlSession session = MyBatisConnectionManager.getSession()) {
            CatMapper catMapper = session.getMapper(CatMapper.class);
            catMapper.save(cat);
            cat.setId(catMapper.getLastId(cat));
            session.commit();
        }
        catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public CatOwner saveCatOwner(CatOwner catOwner) throws SQLException {
        try (SqlSession session = MyBatisConnectionManager.getSession()) {
            CatOwnerMapper catMapper = session.getMapper(CatOwnerMapper.class);
            catMapper.save(catOwner);
            catOwner.setId(catMapper.getLastId(catOwner));
            session.commit();
        }
        catch (IOException e) {
            System.out.println(e);
        }
        return catOwner;
    }

    @Override
    public void deleteCatById(int id) throws SQLException {
        try (SqlSession session = MyBatisConnectionManager.getSession()) {
            CatMapper catMapper = session.getMapper(CatMapper.class);
            catMapper.deleteById(id);
            session.commit();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void deleteCatOwnerById(int id) throws SQLException {
        try (SqlSession session = MyBatisConnectionManager.getSession()) {
            CatOwnerMapper catMapper = session.getMapper(CatOwnerMapper.class);
            catMapper.deleteOldOwners(id);
            catMapper.deleteById(id);
            session.commit();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void deleteCat(Cat entity) throws SQLException {
        try (SqlSession session = MyBatisConnectionManager.getSession()) {
            CatMapper catMapper = session.getMapper(CatMapper.class);
            catMapper.deleteByEntity(entity);
            session.commit();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void deleteCatOwner(CatOwner entity) throws SQLException {
        try (SqlSession session = MyBatisConnectionManager.getSession()) {
            CatOwnerMapper catMapper = session.getMapper(CatOwnerMapper.class);
            catMapper.deleteOldOwners(entity.getId());
            catMapper.deleteByEntity(entity);
            session.commit();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void deleteAllCats() throws SQLException {
        try (SqlSession session = MyBatisConnectionManager.getSession()) {
            CatMapper catMapper = session.getMapper(CatMapper.class);
            catMapper.deleteAllCats();
            session.commit();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void deleteAllCatOwners() throws SQLException {
        try (SqlSession session = MyBatisConnectionManager.getSession()) {
            CatOwnerMapper catOwnerMapper = session.getMapper(CatOwnerMapper.class);
            catOwnerMapper.nullAllCats();
            catOwnerMapper.deleteAll();
            session.commit();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public Cat updateCat(Cat cat, int id) throws SQLException {
        try (SqlSession session = MyBatisConnectionManager.getSession()) {
            CatMapper catMapper = session.getMapper(CatMapper.class);
            catMapper.update(cat, id);
            session.commit();
        }
        catch (IOException e) {
            System.out.println(e);
        }
        return cat;
    }

    @Override
    public CatOwner updateCatOwner(CatOwner catOwner, int id) throws SQLException {
        try (SqlSession session = MyBatisConnectionManager.getSession()) {
            CatOwnerMapper catOwnerMapper = session.getMapper(CatOwnerMapper.class);
            catOwnerMapper.update(catOwner, id);
            session.commit();
        }
        catch (IOException e) {
            System.out.println(e);
        }

        return catOwner;
    }

    @Override
    public Cat getCatById(int id) throws SQLException {
        MyBatisCat cat = null;
        try (SqlSession session = MyBatisConnectionManager.getSession()) {
            CatMapper catMapper = session.getMapper(CatMapper.class);
            cat = catMapper.getById(id);
            session.commit();
        }
        catch (IOException e) {
            System.out.println(e);
        }
        return cat;
    }

    @Override
    public CatOwner getCatOwnerById(int id) throws SQLException {
        MyBatisCatOwner owner = null;
        try (SqlSession session = MyBatisConnectionManager.getSession()) {
            CatOwnerMapper catOwnerMapper = session.getMapper(CatOwnerMapper.class);
            owner = catOwnerMapper.getById(id);
            session.commit();
        }
        catch (IOException e) {
            System.out.println(e);
        }

        return owner;
    }

    @Override
    public List<Cat> getAllCats() throws SQLException {
        List<MyBatisCat> cats = null;
        try (SqlSession session = MyBatisConnectionManager.getSession()) {
            CatMapper catMapper = session.getMapper(CatMapper.class);
            cats = catMapper.getAll();
            session.commit();
        }
        catch (IOException e) {
            System.out.println(e);
        }

        return catsConverter(cats);
    }

    @Override
    public List<CatOwner> getAllCatOwners() throws SQLException {
        List<MyBatisCatOwner> owners = null;
        try (SqlSession session = MyBatisConnectionManager.getSession()) {
            CatOwnerMapper catOwnerMapper = session.getMapper(CatOwnerMapper.class);
            owners = catOwnerMapper.getAll();
            session.commit();
        }
        catch (IOException e) {
            System.out.println(e);
        }

        return ownersConverter(owners);
    }

    @Override
    public List<Cat> getAllByCatOwnerId(int id) throws SQLException {
        List<MyBatisCat> cats = null;
        try (SqlSession session = MyBatisConnectionManager.getSession()) {
            CatMapper catMapper = session.getMapper(CatMapper.class);
            cats = catMapper.getByOwnerId(id);
            CatOwner owner = getCatOwnerById(id);
            for (Cat cat : cats) {
                cat.setOwner(owner);
            }
            session.commit();
        }
        catch (IOException e) {
            System.out.println(e);
        }

        return catsConverter(cats);
    }

    private List<Cat> catsConverter(List<MyBatisCat> batisCats) {
        List<Cat> result = new ArrayList<Cat>();
        for (Cat cat: batisCats) {
            result.add(cat);
        }
        return result;
    }

    private List<CatOwner> ownersConverter(List<MyBatisCatOwner> batisOwners) {
        List<CatOwner> result = new ArrayList<CatOwner>();
        for (CatOwner owner: batisOwners) {
            result.add(owner);
        }
        return result;
    }
}
