package falcon.io.service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DocumentPayload {

    private String documentId;
    private String document;
    private Language language;
}
