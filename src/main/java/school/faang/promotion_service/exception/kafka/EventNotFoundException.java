package school.faang.promotion_service.exception.kafka;


import school.faang.promotion_service.exception.LoggableException;

public class EventNotFoundException extends LoggableException {
    public EventNotFoundException(String string) {
        super(string);
    }
}
