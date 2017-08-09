package falcon.io.service;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

/**
 * Created by tibor on 2017-08-08.
 */
@Getter
@Builder
public class DocumentProcessRequest {
    private String document;
    private UUID id;
    private String srcLang;
    private String tgtLang;
}
