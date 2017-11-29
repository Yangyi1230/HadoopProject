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

import java.util.List;
import java.util.regex.Pattern;

public interface Tokenizer {

    /**
     * Classic text split in tokens based on a {@link Pattern}.
     *
     * @param s The text to be split (tokenized).
     * @param p The compiled {@link Pattern} to use on text split.
     * @return The tokens list after text split (tokenization).
     */
    List<String> split(String s, Pattern p);

    /**
     * Clean text based on a {@link Pattern} and then a classic
     * text split in tokens also based on a {@link Pattern}.
     *
     * @param s The text to be tokenized.
     * @param p The compiled {@link Pattern} to use on text split.
     * @param c The compiled {@link Pattern} to match and remove from text.
     * @param r The text ({@code string}) to replace (e.g., "").
     * @return The tokens list after text clean and split.
     */
    List<String> cleanAndSplit(String s, Pattern p, Pattern c, String r);

    /**
     * Classic white space (e.g., {@code Pattern.compile("\\p{Space}")}) text
     * split in tokens.
     *
     * @param s Text to be split.
     * @return The tokens list after text white space split (tokenization).
     */
    List<String> splitWhitespace(String s);

    /**
     * First, punctuation (e.g., {@code Pattern.compile("\\p{Punct}")}) is
     * removed from text and then a classic white space
     * (e.g., {@code Pattern.compile("\\p{Space}")}) text split in tokens.
     *
     * @param s Text to be cleaned and split.
     * @param r The text ({@code string}) to replace (e.g., "").
     * @return The tokens list after text clean and white space split.
     */
    List<String> cleanPunctuationAndSplitWhitespace(String s, String r);

    /**
     * Removes tokens (items in the list) that do not comply with the
     * required {@code min} and {@code max} length.
     *
     * @param l List of text ({@code String}) tokens.
     * @param min Minimum length.
     * @param max Maximum length.
     */
    void removeTokensBySize(List<String> l, int min, int max);
}
