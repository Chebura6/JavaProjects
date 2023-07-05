package Lab3.SpringData.Models;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Getter
@Setter
@Table(name = "cat")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catid", nullable = false)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "dateofbirth")
    private Date dateOfBirth;
    @Column(name = "breed")
    private String breed;
    @Column(name = "color")
    private String color;
    @Column(name = "taillenght")
    private Integer tailLenght;
    @ManyToOne
    @JoinColumn(name = "ownerid", referencedColumnName = "ownerid")
    private CatOwner owner;

    public Cat(String _name, Date _dateOfBirth, String _breed, String _color, CatOwner _owner, Integer _tailLenght) {
        name = name;
        dateOfBirth = _dateOfBirth;
        breed = _breed;
        color = _color;
        owner = _owner;
        tailLenght = _tailLenght;
    }

    public Cat() { }


}
