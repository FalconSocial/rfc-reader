package falcon.io.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.AsyncRestTemplate;
import pl.allegro.tech.hermes.client.HermesClient;
import pl.allegro.tech.hermes.client.HermesClientBuilder;
import pl.allegro.tech.hermes.client.restTemplate.RestTemplateHermesSender;

import java.net.URI;

/**
 * Created by tibor on 2017-08-07.
 */
@Service
public class MessageProducer {

    private final HermesClient client;

    private final String translationTopic;

    private final String literalTopic;

    public MessageProducer(String hermesUri, String translationTopic, String literalTopic) {
        this.client = HermesClientBuilder.hermesClient(new RestTemplateHermesSender(new AsyncRestTemplate()))
                .withURI(URI.create(hermesUri))
                .build();
        this.translationTopic = translationTopic;
        this.literalTopic = literalTopic;
    }

    public void sendForTranslation(String messageJson){
        this.client.publishJSON(translationTopic, messageJson);
    }

    public void sendLiteral(String messageJson){
        this.client.publishJSON(literalTopic, messageJson);
    }

}
