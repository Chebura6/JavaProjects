package Lab3.SpringData.Entities;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@Table(schema = "public", name="flea")
public class Flea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fleaid", nullable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "catid")
    private Integer catId;

    public Flea(String _name, Integer _catId) {
        name = _name;
        catId = _catId;
    }

    public Flea() {
    }
}
