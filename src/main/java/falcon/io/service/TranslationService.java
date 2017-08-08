package falcon.io.service;

import falcon.io.repository.RfcRepository;
import falcon.io.repository.TranslatedRFC;
import falcon.io.resource.TranslatedRfcDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by tibor on 2017-08-07.
 */
@Service
public class TranslationService {

    @Autowired
    private RfcRepository rfcRepository;

    @Autowired
    private RFCProvider rfcProvider;

    @Transactional
    public String translate(String url, String lang){
        String document = rfcProvider.getRFCAsText(url);
        return rfcRepository.save(new TranslatedRFC(UUID.randomUUID().toString())).getId();
    }

    @Transactional
    public TranslatedRfcDTO getTranslation(String id){
        Optional<TranslatedRFC> translatedRFC = rfcRepository.findById(id);
        return translatedRFC.map(t -> t.asDTO()).orElseThrow(()->new IllegalArgumentException(String.format("Unknown document id: %s", id)));
    }

}
