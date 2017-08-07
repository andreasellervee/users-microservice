package users.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import users.UsersMicroserviceApp;
import users.models.UserEvent;
import users.services.EventService;

@Service
@Profile("!test")
public class EventServiceImpl implements EventService {

    private static final Logger log = LoggerFactory.getLogger(UsersMicroserviceApp.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    @Async
    public void pushEvent(UserEvent userEvent) {
        log.debug("Pushing new user event to message bus: " + userEvent);
        try {
            jmsTemplate.convertAndSend(userEvent.getUserEventType().getEventTypeKey(), userEvent);
        } catch (Exception e) {
            log.error("Error pushing event to message bus: " + userEvent, e);
        }
    }
}
