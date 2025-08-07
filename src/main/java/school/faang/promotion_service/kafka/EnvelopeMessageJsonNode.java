package school.faang.promotion_service.kafka;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface EnvelopeMessageJsonNode {
    @JsonIgnore
    String getType();

    long getId();
}
