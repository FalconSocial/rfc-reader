package falcon.io.parse;

import lombok.Value;

import java.util.UUID;









@Value
public class WordSplit {

    public UUID id;
    public String source = "rfc.reader";
    public String type = "WordSplit";
    public String version = "2.0";
    public String created;
    public UUID aggreateId;
    public Payload payload;

}
