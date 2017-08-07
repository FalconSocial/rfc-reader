package falcon.io.parse;

import lombok.Value;

@Value
public class Page {
    public int position;
    public int number;
    public int numberOfWords;
    public int numberOfPages;
}