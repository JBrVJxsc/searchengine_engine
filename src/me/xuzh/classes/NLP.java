package me.xuzh.classes;


/**
 * Created by Xu on 4/25/15.
 */

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.List;


public class NLP {

    private static MaxentTagger tagger = new MaxentTagger("english-bidirectional-distsim.tagger");

    public static WordClass getWordClass(String str) {
        TokenizerFactory<CoreLabel> ptbTokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(),
                "untokenizable=noneKeep");
        BufferedReader r = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(str.getBytes())));
        DocumentPreprocessor documentPreprocessor = new DocumentPreprocessor(r);
        documentPreprocessor.setTokenizerFactory(ptbTokenizerFactory);
        for (List<HasWord> sentence : documentPreprocessor) {
            List<TaggedWord> tSentence = tagger.tagSentence(sentence);
            for (TaggedWord t : tSentence) {
                String wordClass = t.tag();
                if (wordClass.equals("NN")) {
                    return WordClass.NN;
                } else if (wordClass.equals("NNS")) {
                    return WordClass.NNS;
                } else if (wordClass.equals("JJ")) {
                    return WordClass.JJ;
                } else if (wordClass.equals("VB")) {
                    return WordClass.VB;
                } else if (wordClass.equals("VBS")) {
                    return WordClass.VBS;
                } else if (wordClass.equals("WP")) {
                    return WordClass.WP;
                } else if (wordClass.equals("IN")) {
                    return WordClass.IN;
                } else if (wordClass.equals("WDT")) {
                    return WordClass.WDT;
                } else if (wordClass.equals("CC")) {
                    return WordClass.CC;
                } else {
                    return WordClass.OTHER;
                }
            }
        }
        return WordClass.OTHER;
    }
}