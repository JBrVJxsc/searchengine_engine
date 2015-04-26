package me.xuzh.classes;

import java.util.HashSet;

/**
 * Created by Xu on 4/18/15.
 */
public class TokenParser {
    public static HashSet<String> parseTokens(String str) {
        HashSet<String> result = new HashSet<String>();
        if (str == null || str.trim().length() == 0) {
            return result;
        }

        String[] tokens = str.split(" ");
        StopWords stopWords = new StopWords();

        // Remove the stop words in the tokens.
        for (String string : tokens) {
            if (string.trim().length() != 0 && !stopWords.getStopWords().contains(string)) {
                result.add(string);
            }
        }

        return result;
    }
}
