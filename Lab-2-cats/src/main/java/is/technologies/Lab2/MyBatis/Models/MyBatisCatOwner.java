package is.technologies.Lab2.MyBatis.Models;

import is.technologies.Lab2.Interfaces.CatOwner;

import java.sql.Date;

public class MyBatisCatOwner implements CatOwner {
    private int Id;
    private String Name;
    private Date DateOfBirth;

    public MyBatisCatOwner() {
    }

    public MyBatisCatOwner(String name, Date dateOfBirth) {
        Name = name;
        DateOfBirth = dateOfBirth;
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
}
