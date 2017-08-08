package falcon.io.resource;

/**
 * Created by tibor on 2017-08-07.
 */
public class TranslatedRfcDTO {

    private String document;

    private String id;

    private String state;

    public TranslatedRfcDTO(String document, String id, String state) {
        this.document = document;
        this.id = id;
        this.state = state;
    }

    public String getDocument() {
        return document;
    }

    public String getId() {
        return id;
    }

    public String getState() {
        return state;
    }
}
