package school.faang.promotion_service.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic accountTransactionCommands() {
        return TopicBuilder.name("account.transaction.commands").build();
    }

    @Bean
    public NewTopic accountTransactionEvents() {
        return TopicBuilder.name("account.transaction.events").build();
    }

    @Bean
    public NewTopic promotionUserEvents() {
        return TopicBuilder.name("promotion.user.events").build();
    }

    @Bean
    public NewTopic searchUserImpressionEvents() {
        return TopicBuilder.name("search.user.impression.events").build();
    }

}
