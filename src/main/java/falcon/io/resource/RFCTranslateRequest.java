package falcon.io.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by tibor on 2017-08-07.
 */
public class RFCTranslateRequest {

    private String url;

    private String tgtLang;

    private String srcLang;

    @JsonCreator
    RFCTranslateRequest(@JsonProperty("url") String url, @JsonProperty("tgtLang") String tgtLang, @JsonProperty("srcLang") String srcLang) {
        this.url = url;
        this.tgtLang = tgtLang;
        this.srcLang = srcLang;
    }

    public String getUrl() {
        return url;
    }

    public String getTgtLang() {
        return tgtLang;
    }

    public String getSrcLang() {
        return srcLang;
    }
}
