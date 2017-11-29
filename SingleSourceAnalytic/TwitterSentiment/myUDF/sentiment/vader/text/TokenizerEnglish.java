/*
 * Modified by Dayou Du on Nov.4, 2017
 * VADER is a sub-package in Python library NLTK.
 * Here we use a java version of VADER, which enabled us to perform a MapReduce on Twitter data.
 * 
 * ***************************************   CREDITS   ****************************************
 * This file is modified based on Nuno A. C. Henriques's project [nunoachenriques.net]
 * 
 * This project is the java version of Hutto's Python project VADER
 * @see <a href="http://comp.social.gatech.edu/papers/icwsm14.vader.hutto.pdf">VADER:
 * A Parsimonious Rule-based Model for Sentiment Analysis of Social Media Text</a>
 */
package myUDF.sentiment.vader.text;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class TokenizerEnglish
        implements Tokenizer {

    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\p{Space}");
    /**
     * The defined characters as punctuation except for contractions
     */
    
    private static final Pattern PUNCTUATION_EXCLUDE_CONTRACTION_PATTERN = Pattern.compile("[\\p{Punct}&&[^.']]|(?<=(^|\\s|\\p{Punct}))[.']|[.'](?=($|\\s|\\p{Punct}))");

    /**
     * Default constructor.
     */
    public TokenizerEnglish() {
        // Void.
    }

    @Override
    public List<String> split(String s, Pattern p) {
        return new LinkedList<>(Arrays.asList(p.split(s)));
    }

    @Override
    public List<String> cleanAndSplit(String s, Pattern p, Pattern c, String r) {
        return new LinkedList<>(Arrays.asList(p.split(c.matcher(s).replaceAll(r))));
    }

    @Override
    public List<String> splitWhitespace(String s) {
        return split(s, WHITESPACE_PATTERN);
    }

    @Override
    public List<String> cleanPunctuationAndSplitWhitespace(String s, String r) {
        return cleanAndSplit(s, WHITESPACE_PATTERN, PUNCTUATION_EXCLUDE_CONTRACTION_PATTERN, r);
    }

    @Override
    public void removeTokensBySize(List<String> l, int min, int max) {
        Iterator<String> i = l.iterator();
        while (i.hasNext()) {
            String t = i.next();
            if (t.length() < min || t.length() > max) {
                i.remove();
            }
        }
    }
}
