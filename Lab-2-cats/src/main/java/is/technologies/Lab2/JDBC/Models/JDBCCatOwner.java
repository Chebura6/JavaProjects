package is.technologies.Lab2.JDBC.Models;

import is.technologies.Lab2.Interfaces.CatOwner;

import java.sql.Date;

public class JDBCCatOwner implements CatOwner {
    private int Id;
    private String Name;
    private Date DateOfBirth;

    public JDBCCatOwner(String name, Date dateOfBirth) {
        Name = name;
        DateOfBirth = dateOfBirth;
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
}
