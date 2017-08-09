package falcon.io.service;

import falcon.io.repository.RfcRepository;
import falcon.io.repository.TranslatedRFC;
import falcon.io.resource.TranslatedRfcDTO;
import falcon.io.service.dto.DocumentAssembled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by tibor on 2017-08-07.
 */
@Service
public class TranslationServiceImpl implements TranslationService {

    @Autowired
    private RfcRepository rfcRepository;

    @Autowired
    private RFCProvider rfcProvider;

    @Autowired
    private DocumentProcessor documentProcessor;

    @Override
    @Transactional
    public String translate(String url, String srcLang, String tgtLang) {
        UUID docId = UUID.randomUUID();
        String document = rfcProvider.getRFCAsText(url);
        documentProcessor.processDocument(DocumentProcessRequest.builder()
                .document(document)
                .id(docId)
                .srcLang(srcLang)
                .tgtLang(tgtLang)
                .build());
        return rfcRepository.save(new TranslatedRFC(docId.toString()))
                .getId();
    }

    @Override
    @Transactional
    public TranslatedRfcDTO getTranslation(String id) {
        Optional<TranslatedRFC> translatedRFC = rfcRepository.findById(id);
        return translatedRFC.map(t -> t.asDTO())
                .orElseThrow(() -> new IllegalArgumentException(String.format("Unknown document id: %s", id)));
    }

    @Override
    @Transactional
    public void saveTranslation(DocumentAssembled document) {
        TranslatedRFC translatedRFC = rfcRepository.findById(document.getAggreateId()
                .toString())
                .orElseThrow(() -> new IllegalArgumentException(String.format("Document not found: %s", document.getAggreateId())));
        translatedRFC.setDocument(document.getPayload()
                .getDocument());
    }

}
