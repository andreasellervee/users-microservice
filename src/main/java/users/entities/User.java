package users.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import users.models.UserCreation;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true, nullable = false)
    private String nickname;

    private int passwordHash;

    @Column(unique = true, nullable = false)
    private String email;

    private String country;

    public User(UserCreation userCreation) {
        this.firstName = userCreation.getFirstName();
        this.lastName = userCreation.getLastName();
        this.nickname = userCreation.getNickname();
        this.passwordHash = userCreation.getPassword().hashCode();
        this.email = userCreation.getEmail();
        this.country = userCreation.getCountry();
    }
}
