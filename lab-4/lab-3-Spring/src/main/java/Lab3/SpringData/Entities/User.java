package Lab3.SpringData.Entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "pwd")
    private String password;
    @OneToOne
    @JoinColumn(name = "ownerid")
    private CatOwner catOwner;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleid")
    private Role role;

}
