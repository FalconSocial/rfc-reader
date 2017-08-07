package falcon.io.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by tibor on 2017-08-07.
 */
public class RFCTranslateRequest {

    private String url;

    private String lang;

    @JsonCreator
    RFCTranslateRequest(@JsonProperty("url") String url, @JsonProperty("lang") String lang){
        this.url = url;
        this.lang = lang;
    }

    public String getUrl() {
        return url;
    }

    public String getLang() {
        return lang;
    }
}
