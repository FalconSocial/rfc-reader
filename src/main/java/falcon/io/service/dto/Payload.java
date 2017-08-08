package falcon.io.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Payload {
    private UUID documentId;
    private String word;
    private Language language;
    private Page page;
}