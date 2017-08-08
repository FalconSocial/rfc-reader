package falcon.io.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;


@Data
@AllArgsConstructor
public class WordSplit {
    private UUID id;
    private String source;
    private String type;
    private String version;
    // I guess ideally the created member would be a Date object, but then it seems
    // like we can't override the getter to return an ISO 8601 String?
    private String created;
    private UUID aggreateId;
    private Payload payload;

    @Builder
    protected WordSplit(String created, Payload payload) {
        this.id = UUID.randomUUID();
        this.source = "rfc.reader";
        this.type = "WordSplit";
        this.version = "2.0";
        this.created = created;
        this.payload = payload;
    }
}

