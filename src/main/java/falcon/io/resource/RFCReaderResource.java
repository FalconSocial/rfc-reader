package falcon.io.resource;

import falcon.io.service.TranslationService;
import falcon.io.service.dto.DocumentAssembled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by tibor on 2017-08-07.
 */
@Controller
public class RFCReaderResource {

    @Autowired
    private TranslationService translationService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> postRfcUrl(@RequestBody RFCTranslateRequest request, UriComponentsBuilder uriBuilder) {

        UriComponents uriComponents = uriBuilder.path("/{id}")
                .buildAndExpand(translationService.translate(request.getUrl(),request.getSrcLang(), request.getTgtLang()));

        return ResponseEntity.created(uriComponents.toUri())
                .build();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<TranslatedRfcDTO> getTranslatedRfc(@PathVariable("id") String id) {
        return ResponseEntity.ok(translationService.getTranslation(id));
    }

    @RequestMapping(value = "/translated", method = RequestMethod.POST)
    public ResponseEntity<Void> documentTopicHook(@RequestBody DocumentAssembled document, UriComponentsBuilder uriBuilder) {
        translationService.saveTranslation(document);
        UriComponents uriComponents = uriBuilder.path("/{id}")
                .buildAndExpand(document.getAggreateId());

        return ResponseEntity.created(uriComponents.toUri())
                .build();

    }
}
