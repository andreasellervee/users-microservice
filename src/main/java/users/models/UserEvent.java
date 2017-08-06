package users.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import users.enums.UserEventType;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserEvent implements Serializable {

    private Long userId;
    private UserEventType userEventType;

}
