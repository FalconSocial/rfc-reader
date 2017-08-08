package falcon.io.service;

import falcon.io.parse.StringSplitter;
import falcon.io.parse.StringSplitter.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public void processDocument(String document, UUID documentId){
        ArrayList<ArrayList<Word>> pages = StringSplitter.split(document);
        for(int i=0; i< pages.size(); i++){
            processPage(pages.get(i), i, documentId);
        }
    }

    private void processPage(ArrayList<Word> page, int pageIndex, UUID documentId){
        for(int i=0; i< page.size(); i++){
            processWord(page.get(i),pageIndex, i, documentId);
        }
    }

    private void processWord(Word word, int pageIndex, int wordIndex, UUID documentId){

    }


}
