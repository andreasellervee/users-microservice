package users.enums;

import lombok.Getter;

@Getter
public enum UserEventType {

    USER_ADDED("user.event.added"),
    USER_MODIFIED("user.event.modified"),
    USER_DELETED("user.event.deleted");

    private String eventTypeKey;

    UserEventType(String eventTypeKey) {
        this.eventTypeKey = eventTypeKey;
    }
}
