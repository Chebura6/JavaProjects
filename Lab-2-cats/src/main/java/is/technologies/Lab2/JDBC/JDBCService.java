package is.technologies.Lab2.JDBC;

import is.technologies.Lab2.Interfaces.Cat;
import is.technologies.Lab2.Interfaces.CatOwner;
import is.technologies.Lab2.Interfaces.DataBaseOperations;
import is.technologies.Lab2.JDBC.Models.JDBCCat;
import is.technologies.Lab2.JDBC.Models.JDBCCatOwner;
//import java.util.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCService implements DataBaseOperations {
    private Connection Connection;
    public JDBCService(ConnectionManager connectionManager) {
        if (connectionManager == null) {
            throw new NullPointerException("Null connection manager object");
        }

        this.Connection = connectionManager.getConnection();
    }

    @Override
    public Cat saveCat(Cat cat) throws SQLException {
        String catName = cat.getName();
        String catBreed = cat.getBreed();
        Date catDateOfBirth = cat.getDateOfBirth();
        String catColor = cat.getColor();
        int catOwnerId = cat.getOwner().getId();
        String query = "INSERT INTO Cat(CatName, Dateofbirth, Breed, Color, OwnerID) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = Connection.prepareStatement(query);
        preparedStatement.setString(1, catName);
        preparedStatement.setDate(2, catDateOfBirth);
        preparedStatement.setString(3, catBreed);
        preparedStatement.setString(4, catColor);
        preparedStatement.setInt(5, catOwnerId);

        preparedStatement.executeUpdate();
        return cat;
    }

    @Override
    public CatOwner saveCatOwner(CatOwner catOwner) throws SQLException {
        String ownerName = catOwner.getName();
        Date dateOfBirth = catOwner.getDateOfBirth();
        String query = "INSERT INTO CatOwner(Name, Dateofbirth) VALUES (?, ?)";
        PreparedStatement preparedStatement = Connection.prepareStatement(query);
        preparedStatement.setString(1, ownerName);
        preparedStatement.setDate(2, dateOfBirth);
        preparedStatement.executeUpdate();
        String selectForID = "SELECT ownerid FROM Catowner WHERE name = \'" + ownerName + "\'" + " AND dateofbirth = \'" + dateOfBirth.toString() + "\'";
        Statement select = Connection.createStatement();
        ResultSet set = select.executeQuery(selectForID);
        while (set.next()) {
            catOwner.setId(set.getInt(1));
        }
        return catOwner;
    }

    @Override
    public void deleteCatById(int id) throws SQLException {
        String query = "DELETE FROM Cat WHERE catid = \'" + id + "\'";
        Statement statement = Connection.createStatement();
        statement.executeUpdate(query);
    }

    @Override
    public void deleteCatOwnerById(int id) throws SQLException {
        String update = "UPDATE Cat SET ownerid = NULL WHERE ownerid = " + id;
        Statement statement1 = Connection.createStatement();
        statement1.executeUpdate(update);
        String query = "DELETE FROM CatOwner WHERE ownerid = \'" + id + "\'";
        Statement statement2 = Connection.createStatement();
        statement2.executeUpdate(query);
    }

    @Override
    public void deleteCat(Cat cat) throws SQLException {
        deleteCatById(cat.getId());
    }

    @Override
    public void deleteCatOwner(CatOwner catOwner) throws SQLException {
        deleteCatOwnerById(catOwner.getId());
    }

    @Override
    public void deleteAllCats() throws SQLException {
        String deleteQuery = "DELETE FROM Cat";
        Statement statement = Connection.createStatement();
        statement.executeUpdate(deleteQuery);
    }

    @Override
    public void deleteAllCatOwners() throws SQLException {
        String update = "UPDATE Cat SET ownerid = NULL";
        Statement statement1 = Connection.createStatement();
        statement1.executeUpdate(update);
        String deleteQuery = "DELETE FROM CatOwner";
        Statement statement = Connection.createStatement();
        statement.executeUpdate(deleteQuery);
    }

    @Override
    public Cat updateCat(Cat cat, int id) throws SQLException {
        String query = "UPDATE Cat SET "
                + "catname = " + cat.getName()
                + " dateofbirth = " + cat.getDateOfBirth()
                + " breed " + cat.getBreed()
                + " color = " + cat.getColor()
                + " ownerid = " + cat.getOwner().getId()
                + "WHERE catid = " + id;
        Statement statement = Connection.createStatement();
        statement.executeUpdate(query);
        cat.setId(id);
        return cat;
    }

    @Override
    public CatOwner updateCatOwner(CatOwner catOwner, int id) throws SQLException {
        String query = "UPDATE CatOwner SET "
                + "catname = " + catOwner.getName()
                + " dateofbirth = " + catOwner.getDateOfBirth()
                + "WHERE catid = " + id;
        Statement statement = Connection.createStatement();
        statement.executeUpdate(query);
        catOwner.setId(id);
        return catOwner;
    }


    @Override
    public Cat getCatById(int id) throws SQLException {
        String query = "SELECT * FROM Cat WHERE catid = " + id;
        Statement statement = Connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        JDBCCatOwner owner = null;
        Cat cat = null;

        return makeCats(resultSet).get(0);
    }

    @Override
    public CatOwner getCatOwnerById(int id) throws SQLException {
        String query = "SELECT * FROM CatOwner WHERE ownerid = " + id;
        Statement statement = Connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        JDBCCatOwner owner = null;
        while (resultSet.next()) {
            owner = new JDBCCatOwner(resultSet.getString(2), resultSet.getDate(3));
            owner.setId(resultSet.getInt(1));
        }

        return owner;
    }

    @Override
    public List<Cat> getAllCats() throws SQLException {
        String query = "SELECT * FROM Cat";
        Statement statement = Connection.createStatement();
        ResultSet result = statement.executeQuery(query);


        return makeCats(result);
    }

    @Override
    public List<CatOwner> getAllCatOwners() throws SQLException {
        String query = "SELECT * FROM CatOwner";
        Statement statement = Connection.createStatement();
        ResultSet result = statement.executeQuery(query);
        List<CatOwner> catOwners = new ArrayList<CatOwner>();
        while (result.next()) {
            JDBCCatOwner catOwner = new JDBCCatOwner(result.getString(2), result.getDate(3));
            catOwner.setId(result.getInt(1));
            catOwners.add(catOwner);
        }

        return catOwners;
    }


    @Override
    public List<Cat> getAllByCatOwnerId(int id) throws SQLException {
        String query = "SELECT * FROM Cat WHERE ownerid = " + id;
        Statement statement = Connection.createStatement();
        ResultSet result = statement.executeQuery(query);

        return makeCats(result);
    }

    private List<Cat> makeCats(ResultSet resultSet) throws SQLException {
        JDBCCatOwner owner = null;
        Cat cat = null;
        List<Cat> cats = new ArrayList<Cat>();
        while (resultSet.next()) {
            int catOwnerID = resultSet.getInt(6);
            String getOwnerQuery = "SELECT * FROM CatOwner WHERE ownerid = " + catOwnerID;
            Statement ownerStatement = Connection.createStatement();
            ResultSet set = ownerStatement.executeQuery(getOwnerQuery);
            while (set.next()) {
                owner = new JDBCCatOwner(set.getString(2), set.getDate(3));
                owner.setId(set.getInt(1));
            }
            cat = new JDBCCat(resultSet.getString(2), resultSet.getDate(3), resultSet.getString(4), resultSet.getString(5), owner);
            cat.setId(resultSet.getInt(1));
            cats.add(cat);
        }

        return cats;
    }
}


