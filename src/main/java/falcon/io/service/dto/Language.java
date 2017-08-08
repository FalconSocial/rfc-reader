package falcon.io.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Language {
    private String input;
    private String output;
}