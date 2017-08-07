package users.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import users.UsersMicroserviceApp;
import users.models.UserEvent;
import users.services.EventService;

@Service
@Profile("test")
public class DummyEventServiceImpl implements EventService {

    private static final Logger log = LoggerFactory.getLogger(UsersMicroserviceApp.class);

    @Override
    public void pushEvent(UserEvent userEvent) {
        log.info("[DUMMY] Pushing new user event to message bus: " + userEvent);
    }
}
