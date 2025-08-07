package school.faang.promotion_service.kafka;

import com.fasterxml.jackson.databind.JsonNode;

public record EnvelopeMessage(
        String type,
        JsonNode payload
) {
}
