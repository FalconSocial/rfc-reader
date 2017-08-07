package falcon.io.repository;

import falcon.io.resource.TranslatedRfcDTO;

import javax.persistence.*;

/**
 * Created by tibor on 2017-08-07.
 */
@Entity
@Table(name = "TRANSLATED_RFC")
public class TranslatedRFC {

    public static enum State {
        TRANSLATED,
        PROCESSING
    }

    @Id
    private String id;

    @Column(name = "state", length = 10)
    private State state;

    @Lob
    @Column
    private String document;


    public TranslatedRFC(){

    }

    public TranslatedRFC(String id){
        this.id = id;
        this.state = State.PROCESSING;
    }

    public TranslatedRfcDTO asDTO(){
        return new TranslatedRfcDTO(document, id, state.name());
    }

    public String getDocument() {
        return document;
    }

    public String getId() {
        return id;
    }
}
