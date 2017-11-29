/*
 * Modified by Dayou Du on Nov.4, 2017
 * VADER is a sub-package in Python library NLTK.
 * Here we use a java version of VADER, which enabled us to perform a MapReduce on Twitter data.
 * 
 * ********************************************CREDITS*******************************************
 * This file is modified based on Nuno A. C. Henriques's project [nunoachenriques.net]
 * 
 * This project is the java version of Hutto's Python project VADER
 * @see <a href="http://comp.social.gatech.edu/papers/icwsm14.vader.hutto.pdf">VADER:
 * A Parsimonious Rule-based Model for Sentiment Analysis of Social Media Text</a>
 */
package myUDF.sentiment.vader;

class Constant {

    // Private constructor to avoid instantiation.
    private Constant() {
        // Void!
    }

    // TODO check SentimentAnalysis for missing constants!

    static final float NORMALIZE_SCORE_ALPHA_DEFAULT = 15.0f;
    static final float ALL_CAPS_BOOSTER_SCORE = 0.733f;
    static final float N_SCALAR = -0.74f;
    static final float EXCLAMATION_BOOST = 0.292f;
    static final float QUESTION_BOOST_COUNT_3 = 0.18f;
    static final float QUESTION_BOOST = 0.96f;
}
