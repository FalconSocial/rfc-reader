package falcon.io.parse;

import lombok.Value;

import java.util.UUID;

@Value
public class Payload {

    public UUID documentId;
    public String word;
    public Language language;
    public Page page;
}