package is.technologies.Lab2.Hibernate.Models;

import is.technologies.Lab2.Interfaces.CatOwner;
import jakarta.persistence.*;
//import jakarta.persistence.*;

//import javax.persistence.Column;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.OneToMany;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "CatOwner", catalog = "public")
public class HibernateCatOwner implements CatOwner {
    public HibernateCatOwner() {
    }

    public HibernateCatOwner(String name, Date dateOfBirth) {
        Name = name;
        DateOfBirth = dateOfBirth;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OwnerID")
    private int Id;
    @Column(name = "Name")
    private String Name;
    @Column(name = "DateOfBirth")
    private Date DateOfBirth;

    public int getId() {
        return Id;
    }

    @Override
    public void setId(int id)  {
        id = id;
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
