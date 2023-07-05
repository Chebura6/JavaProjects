package is.technologies.Lab2.MyBatis.Models;

import is.technologies.Lab2.Interfaces.Cat;
import is.technologies.Lab2.Interfaces.CatOwner;

import java.sql.Date;

public class MyBatisCat implements Cat {
    private int Id;
    private String Name;
    private Date DateOfBirth;
    private String Breed;
    private String Color;
    private MyBatisCatOwner Owner;

    public MyBatisCat() {
    }

    public MyBatisCat(String name, Date dateOfBirth, String breed, String color, MyBatisCatOwner owner) {
        Name = name;
        DateOfBirth = dateOfBirth;
        Breed = breed;
        Color = color;
        Owner = owner;
    }

    @Override
    public int getId() {
        return Id;
    }

    @Override
    public void setId(int id) {
        Id = id;
    }

    @Override
    public String getName() {
        return Name;
    }

    @Override
    public void setName(String name) {
        Name = name;
    }

    @Override
    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    @Override
    public void setDateOfBirth(Date dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    @Override
    public String getBreed() {
        return Breed;
    }

    @Override
    public void setBreed(String breed) {
        Breed = breed;
    }

    @Override
    public String getColor() {
        return Color;
    }

    @Override
    public void setColor(String color) {
        Color = color;
    }

    @Override
    public CatOwner getOwner() {
        return Owner;
    }

    @Override
    public void setOwner(CatOwner owner) {
        Owner = (MyBatisCatOwner) owner;
    }
}
