package falcon.io.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;


@Data
@AllArgsConstructor
public class WordSplit {
    private UUID id;
    private String source = "rfc.reader";
    private String type = "WordSplit";
    private String version = "2.0";
    // I guess ideally the created member would be a Date object, but then it seems
    // like we can't override the getter to return an ISO 8601 String?
    private String created;
    private UUID aggreateId;
    private Payload payload;
}
