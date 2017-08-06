package users.services;

import users.models.UserEvent;

public interface EventService {

    void pushEvent(UserEvent userEvent);
}
