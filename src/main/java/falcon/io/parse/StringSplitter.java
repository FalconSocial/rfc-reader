package falcon.io.parse;

import java.util.ArrayList;
import java.util.StringTokenizer;


class Word {

    public String word;
    public boolean translatable;

    public Word(String word, boolean translatable) {
        this.word = word;
        this.translatable = translatable;
    }

    public String toString() {
        return "\"" + word + "\"" + " - " + new Boolean(translatable).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word1 = (Word) o;

        if (translatable != word1.translatable) return false;
        return word != null ? word.equals(word1.word) : word1.word == null;
    }

    @Override
    public int hashCode() {
        int result = word != null ? word.hashCode() : 0;
        result = 31 * result + (translatable ? 1 : 0);
        return result;
    }
}


public class StringSplitter {

    public static ArrayList<ArrayList<Word>> split(String document) {

        //String[] lines = document.split("\\r?\\n");
        String lineDelims = "\r\n";
        StringTokenizer lineTokenizer = new StringTokenizer(document, lineDelims,
                true);
        ArrayList<ArrayList<Word>> parsedDocument = new ArrayList<>();

        ArrayList<Word> currentPage = new ArrayList<>();
        String delims = " \t\n\r\f,.:;?![]'";

        while(lineTokenizer.hasMoreTokens()) {
            String line = lineTokenizer.nextToken();
            // Check if this is a page break.
            // If not, split up into words.
            if (isPageBreak(line)) {
                parsedDocument.add(currentPage);
                currentPage = new ArrayList<>();
            }
            StringTokenizer st = new StringTokenizer(line,
                    delims, true);
            while (st.hasMoreTokens()) {
                String wordOrToken = st.nextToken();
                boolean isTranslatable = !delims.contains(wordOrToken);
                currentPage.add(new Word(wordOrToken, isTranslatable));
            }
        }
        parsedDocument.add(currentPage);

        return parsedDocument;
    }

    public static boolean isPageBreak(String line) {
        // TODO: improve this regex.
        if (line.contains("[Page ")) {
            System.out.println("Matched line: " + line);
            return true;
        }
        return false;
    }
}