package falcon.io.parse;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import falcon.io.service.dto.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringSplitterTest {

    @Test
    public void splitEmpty() {
        ArrayList<ArrayList<Word>> expected = new ArrayList<>();
        expected.add(new ArrayList<>());
        ArrayList<ArrayList<Word>> actual = StringSplitter.split("");
        assertEquals(expected, actual);
    }

    @Test
    public void splitLines() {
        String document = "this is a line\rthis is another line\n";
        ArrayList<Word> firstLine = new ArrayList<>(
                Arrays.asList(new Word("this", true),
                        new Word(" ", false), new Word("is", true),
                        new Word(" ", false), new Word("a", true),
                        new Word(" ", false), new Word("line", true),
                        new Word("\r", false),
                        new Word("this", true),
                        new Word(" ", false), new Word("is", true),
                        new Word(" ", false), new Word("another", true),
                        new Word(" ", false), new Word("line", true),
                        new Word("\n", false))
        );
        ArrayList<ArrayList<Word>> expected = new ArrayList<>();
        expected.add(firstLine);

        ArrayList<ArrayList<Word>> actual = StringSplitter.split(document);
        assertEquals(expected, actual);
    }

    @Test
    public void splitPages() {
        String document = "this, is. a; line\r";
        document += "Rekhter, et al           Best Current Practice                  [Page 1]";
        ArrayList<Word> firstPage = new ArrayList<>(
                Arrays.asList(new Word("this", true), new Word(",", false),
                        new Word(" ", false), new Word("is", true),
                        new Word(".", false), new Word(" ", false),
                        new Word("a", true), new Word(";", false),
                        new Word(" ", false), new Word("line", true),
                        new Word("\r", false))
        );
        ArrayList<Word> secondPage = new ArrayList<>(
                Arrays.asList(new Word("Rekhter", true), new Word(",", false),
                        new Word(" ", false), new Word("et", true),
                        new Word(" ", false), new Word("al", true),
                        new Word(" ", false), new Word(" ", false),
                        new Word(" ", false), new Word(" ", false),
                        new Word(" ", false), new Word(" ", false),
                        new Word(" ", false), new Word(" ", false),
                        new Word(" ", false),
                        new Word(" ", false), new Word(" ", false),
                        new Word("Best", true), new Word(" ", false),
                        new Word("Current", true), new Word(" ", false),
                        new Word("Practice", true), new Word(" ", false),
                        new Word(" ", false), new Word(" ", false),
                        new Word(" ", false), new Word(" ", false),
                        new Word(" ", false), new Word(" ", false),
                        new Word(" ", false), new Word(" ", false),
                        new Word(" ", false),
                        new Word(" ", false), new Word(" ", false),
                        new Word(" ", false), new Word(" ", false),
                        new Word(" ", false), new Word(" ", false),
                        new Word(" ", false), new Word(" ", false),
                        new Word("[", false), new Word("Page", true),
                        new Word(" ", false), new Word("1", true),
                        new Word("]", false))
        );

        ArrayList<ArrayList<Word>> actual = StringSplitter.split(document);
        ArrayList<ArrayList<Word>> expected = new ArrayList<>();
        expected.add(firstPage);
        expected.add(secondPage);
        assertEquals(expected, actual);
    }

    @Test
    public void instantiateWordSplit() throws IOException {
        WordSplit w = WordSplit.builder().created("now").payload(
                new Payload(UUID.randomUUID(), "blah",
                        new Language("en_US", "dk_DK"),
                        new Page(1, 1, 2, 3))).build();
        LiteralSplit l = LiteralSplit.builder().created("now").payload(
                new Payload(UUID.randomUUID(), "blah",
                        new Language("en_US", "dk_DK"),
                        new Page(1, 1, 2, 3))).build();
        ObjectMapper m = new ObjectMapper();
        String wordJSON = m.writeValueAsString(w);
        String literalJSON = m.writeValueAsString(l);
        WordSplit w2 = m.readValue(wordJSON, WordSplit.class);
        LiteralSplit s2 = m.readValue(literalJSON, LiteralSplit.class);
    }

    @Test
    public void testMultipleRFCs() {
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));
    }
}