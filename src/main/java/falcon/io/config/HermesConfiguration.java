package falcon.io.config;

import falcon.io.service.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by tibor on 2017-08-07.
 */
@Configuration
public class HermesConfiguration {

    @Value("${hermes.uri}")
    private String hermesUri;

    @Value("${hermes.topic.translation}")
    private String translationTopic;

    @Value("${hermes.topic.literal}")
    private String literalTopic;

    @Value("${hermes.topic.document}")
    private String documentTopic;

    @Value("${server.port}")
    private String localPort;

    @Bean
    public MessageProducer messageProducer() {
        return new MessageProducer(hermesUri, translationTopic, literalTopic);
    }

    @PostConstruct
    public void subscribe() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
                .add(new MappingJackson2HttpMessageConverter());

        Map<String, Object> subscription = new TreeMap<>();
        subscription.put("topicName", documentTopic);
        subscription.put("name", "translated-document-subscription");
        subscription.put("description", "I get the translated documents");
        subscription.put("endpoint", String.format("http://localhost:%s/translated", localPort));
        Map<String, String> owner = new TreeMap<>();
        owner.put("source", "Plaintext");
        owner.put("id", "Backend flock");
        subscription.put("owner", owner);

        ///topics/{topicName}/subscriptions

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(subscription, headers);

        restTemplate.exchange(String.format("%s/topics/{0}/subscriptions", hermesUri), HttpMethod.POST, entity, String.class, documentTopic);
    }

    @PreDestroy
    public void unsubscribe(){
        new RestTemplate().delete(String.format("%s/topics/{0}/subscriptions/translated-document-subscription", hermesUri), documentTopic);
    }


}
