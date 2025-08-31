package school.faang.promotion_service.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Value("${spring.kafka.topics.account-transactions-commands.name}")
    private String accountTransactionsCommandsTopic;
    @Value("${spring.kafka.topics.account-transactions-events.name}")
    private String accountTransactionsEventsTopic;
    @Value("${spring.kafka.topics.promotion-users-events.name}")
    private String promotionUsersEventsTopic;
    @Value("${spring.kafka.topics.promotion-tariffs-events.name}")
    private String promotionTariffsEventsTopic;
    @Value("${spring.kafka.topics.search-users-impression.name}")
    private String searchUsersImpressionTopic;

    @Bean
    public NewTopic accountTransactionCommands() {
        return TopicBuilder.name(accountTransactionsCommandsTopic).build();
    }

    @Bean
    public NewTopic accountTransactionEvents() {
        return TopicBuilder.name(accountTransactionsEventsTopic).build();
    }

    @Bean
    public NewTopic promotionUserEvents() {
        return TopicBuilder.name(promotionUsersEventsTopic).build();
    }

    @Bean
    public NewTopic promotionTariffEvents() {
        return TopicBuilder.name(promotionTariffsEventsTopic).build();
    }

    @Bean
    public NewTopic searchUserImpressionEvents() {
        return TopicBuilder.name(searchUsersImpressionTopic).build();
    }
}
