package falcon.io.service;

import falcon.io.resource.TranslatedRfcDTO;
import falcon.io.service.dto.DocumentAssembled;

import javax.transaction.Transactional;

/**
 * Created by tibor on 2017-08-08.
 */
public interface TranslationService {
    @Transactional
    String translate(String url, String srcLang, String tgtLang);

    @Transactional
    TranslatedRfcDTO getTranslation(String id);

    @Transactional
    void saveTranslation(DocumentAssembled document);
}
