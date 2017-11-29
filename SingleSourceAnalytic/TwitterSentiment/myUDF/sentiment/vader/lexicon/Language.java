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
package myUDF.sentiment.vader.lexicon;

import java.util.List;
import java.util.Map;

/**
 * A simple tokenizer of plain text.
 *
 */
public interface Language {

    /**
     * Gets the predefined punctuation list for texts in this language.
     *
     * @return A punctuation list.
     */
    List<String> getPunctuation();

    /**
     * Gets the predefined negative words list in this language.
     *
     * @return A negative words list.
     */
    List<String> getNegativeWords();

    /**
     * Gets the predefined booster (increment or decrement) dictionary.
     *
     * @return A map with key-value pairs of words and increment or decrement
     * valence.
     */
    Map<String, Float> getBoosterDictionary();

    /**
     * Gets the predefined idiomatic expressions valence map.
     *
     * @return A map with key-value pairs of idiomatic expressions and valence.
     */
    Map<String, Float> getSentimentLadenIdioms();

    /**
     * Gets predefined single words valence.
     *
     * @return A map with key-value pairs of words and valence.
     */
    Map<String, Float> getWordValenceDictionary();

    /**
     * Is NOT upper if is a URL of type "http://" or "HTTP://", a number as a
     * string, has one character in lower case. Is upper otherwise.
     *
     * @param token Text sample.
     * @return False if is not upper, true otherwise.
     */
    boolean isUpper(String token);
}
