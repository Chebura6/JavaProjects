package is.technologies.Lab2.Hibernate.Models;

import is.technologies.Lab2.Interfaces.Cat;
import is.technologies.Lab2.Interfaces.CatOwner;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "cat", catalog = "public")
public class HibernateCat implements Cat {
    public HibernateCat() {
    }

    public HibernateCat(String name, Date dateOfBirth, String breed, String color, HibernateCatOwner owner) {
        Name = name;
        DateOfBirth = dateOfBirth;
        Breed = breed;
        Color = color;
        Owner = owner;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catid", nullable = false)
    private int Id;
    @Column(name = "catname")
    private String Name;
    @Column(name = "dateofbirth")
    private Date DateOfBirth;
    @Column(name = "breed")
    private String Breed;
    @Column(name = "color")
    private String Color;
    @ManyToOne
    @JoinColumn(name = "ownerid")
    private HibernateCatOwner Owner;

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
        Owner = (HibernateCatOwner)owner;
    }
    public int getOwnerId() {
        return Owner.getId();
    }
}
