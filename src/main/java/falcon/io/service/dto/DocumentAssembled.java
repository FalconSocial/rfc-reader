package falcon.io.service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class DocumentAssembled {

    private UUID id;
    private String source;
    protected String type;
    private String version;
    // I guess ideally the created member would be a Date object, but then it seems
    // like we can't override the getter to return an ISO 8601 String?
    private String created;
    private UUID aggreateId;
    private DocumentPayload payload;
}
