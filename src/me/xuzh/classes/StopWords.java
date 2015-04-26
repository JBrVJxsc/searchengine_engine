package me.xuzh.classes;

import java.util.HashSet;

/**
 * Created by Xu on 4/18/15.
 */
public class StopWords {
    private HashSet<String> stopWords = null;

    public StopWords() {
        stopWords = new HashSet<String>();
        stopWords.add("but");
        stopWords.add("be");
        stopWords.add("with");
        stopWords.add("such");
        stopWords.add("then");
        stopWords.add("for");
        stopWords.add("no");
        stopWords.add("will");
        stopWords.add("not");
        stopWords.add("are");
        stopWords.add("and");
        stopWords.add("their");
        stopWords.add("if");
        stopWords.add("this");
        stopWords.add("on");
        stopWords.add("into");
        stopWords.add("a");
        stopWords.add("or");
        stopWords.add("there");
        stopWords.add("in");
        stopWords.add("that");
        stopWords.add("they");
        stopWords.add("was");
        stopWords.add("is");
        stopWords.add("it");
        stopWords.add("an");
        stopWords.add("the");
        stopWords.add("as");
        stopWords.add("at");
        stopWords.add("these");
        stopWords.add("by");
        stopWords.add("to");
        stopWords.add("of");
    }

    public HashSet<String> getStopWords() {
        return stopWords;
    }
}
