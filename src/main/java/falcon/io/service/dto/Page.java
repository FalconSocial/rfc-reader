package falcon.io.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Page {
    private int position;
    private int number;
    private int numberOfWords;
    private int numberOfPages;
}