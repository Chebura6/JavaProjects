package is.technologies.Lab2.JDBC.Models;

import is.technologies.Lab2.Interfaces.Cat;
import is.technologies.Lab2.Interfaces.CatOwner;

import java.sql.Date;
//import java.time.LocalDate;


public class JDBCCat implements Cat {
    private int Id;
    private String Name;
    private Date DateOfBirth;
    private String Breed;
    private String Color;
    private JDBCCatOwner Owner;

    public JDBCCat(String name, Date dateOfBirth, String breed, String color, JDBCCatOwner owner) {
        Name = name;
        DateOfBirth = dateOfBirth;
        Breed = breed;
        Color = color;
        Owner = owner;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getBreed() {
        return Breed;
    }

    public void setBreed(String breed) {
        Breed = breed;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public JDBCCatOwner getOwner() {
        return Owner;
    }

    public void setOwner(CatOwner owner) {
        Owner = (JDBCCatOwner)owner;
    }
}
