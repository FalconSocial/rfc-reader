package falcon.io.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import falcon.io.parse.StringSplitter;
import falcon.io.parse.StringSplitter.Word;
import falcon.io.service.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

/**
 * Created by tibor on 2017-08-07.
 */
@Service
public class DocumentProcessor {

    @Autowired
    private MessageProducer messageProducer;

    public DocumentProcessor(){

    }

    public void setMessageProducer(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    public void processDocument(String document, UUID documentId) {
        ArrayList<ArrayList<Word>> pages = StringSplitter.split(document);
        for(int i=0; i< pages.size(); i++){
            processPage(pages.get(i), i, documentId, pages.size());
        }
    }

    private void processPage(ArrayList<Word> page, int pageIndex, UUID documentId, int numPages) {
        for(int i=0; i< page.size(); i++){
            processWord(page.get(i),pageIndex, i, documentId, numPages, page.size());
        }
    }

    private void processWord(Word word, int pageIndex, int wordIndex, UUID documentId, int numPages, int numWords) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        df.setTimeZone(tz);
        String nowAsISO = df.format(new Date());
        Language dummyLang = new Language("en_US", "dk_DK");

        ObjectMapper om = new ObjectMapper();
        if (word.translatable) {
            WordSplit ws;
            ws = WordSplit.builder().created(nowAsISO).payload(
                    new Payload(documentId, word.word, dummyLang,
                    new Page(wordIndex, pageIndex, numWords, numPages))).build();
            try {
                this.messageProducer.sendForTranslation(om.writeValueAsString(ws));
            } catch (JsonProcessingException e) {
                // TODO log and reraise?
            }
        }

        else {
            LiteralSplit ws;
            ws = LiteralSplit.builder().created(nowAsISO).payload(
                    new Payload(documentId, word.word, dummyLang,
                    new Page(wordIndex, pageIndex, numWords, numPages))).build();
            try {
                this.messageProducer.sendLiteral(om.writeValueAsString(ws));
            } catch (JsonProcessingException e) {
                // TODO log and reraise?
            }
        }


    }


}
