package Lab3.SpringData.Entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Data
@Getter
@Setter
@Entity
@Table(name = "role", schema = "public")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleid")
    private Long id;
    @Column(name = "rolename")
    public String name;
}
