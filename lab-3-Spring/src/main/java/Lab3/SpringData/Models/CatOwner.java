package Lab3.SpringData.Models;//package is.technologies.Lab3.SpringData.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Data
@Getter
@Setter
@Table(schema = "public",name="catowner")
public class CatOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ownerid", nullable = false)
    private int id;
    @Column(name = "Name")
    private String name;
    @Column(name = "dateofbirth")
    private Date dateOfBirth;

    public CatOwner(String _name, Date _dateOfBirth) {
        name = _name;
        dateOfBirth = _dateOfBirth;
    }

    public CatOwner() {}

    public int getId() {
        return id;
    }

    public void setId(int _id) {
        id = _id;
    }
}
