package is.technologies.Lab2.Interfaces;

import java.sql.SQLException;
import java.util.List;

public interface DataBaseOperations {
    public Cat saveCat(Cat cat) throws SQLException;
    public CatOwner saveCatOwner(CatOwner catOwner) throws SQLException;
    public void deleteCatById(int id) throws SQLException;
    public void deleteCatOwnerById(int id) throws SQLException;
    public void deleteCat(Cat entity) throws SQLException;
    public void deleteCatOwner(CatOwner entity) throws SQLException;
    public void deleteAllCats() throws SQLException;
    public void deleteAllCatOwners() throws SQLException;
    public Cat updateCat(Cat cat, int id) throws SQLException;
    public CatOwner updateCatOwner(CatOwner catOwner, int id) throws SQLException;
    public Cat getCatById(int id) throws SQLException;
    public CatOwner getCatOwnerById(int id) throws SQLException;
    public List<Cat> getAllCats() throws SQLException;
    public List<CatOwner> getAllCatOwners() throws SQLException;
    public List<Cat> getAllByCatOwnerId(int id) throws SQLException;
}
