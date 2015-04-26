package me.xuzh.classes.rankings;

import me.xuzh.classes.WordClass;

import java.io.IOException;

/**
 * Created by Xu on 4/18/15.
 */
public class MyRanking extends BaseRanking {
    @Override
    public String getName() {
        return "Ranking-My";
    }

    @Override
    public String getUrlName() {
        return "result_my_ranking.txt";
    }

    @Override
    protected float getScore(int tf, float idf, WordClass wordClass) throws IOException {
        float score = (float) Math.sqrt(tf) * idf * getWeight(wordClass);
        return score;
    }

    private float getWeight(WordClass wordClass) {
        // NN, // world
        // NNS, // words
        // JJ, // basic, computational
        // VB, // lose, understand
        // VBS, // takes
        // WP, // who
        // IN, // in, on, from
        // WDT, // that, this
        // CC, // and
        // OTHER
        if (wordClass == WordClass.NN) {
            return 2.0f;
        } else if (wordClass == WordClass.NNS) {
            return 2.12f;
        } else if (wordClass == WordClass.JJ) {
            return 1.9f;
        } else if (wordClass == WordClass.VB) {
            return 1.95f;
        } else if (wordClass == WordClass.VBS) {
            return 1.96f;
        } else if (wordClass == WordClass.WP) {
            return 1.5f;
        } else if (wordClass == WordClass.IN) {
            return 1.2f;
        } else if (wordClass == WordClass.WDT) {
            return 1.2f;
        } else if (wordClass == WordClass.CC) {
            return 1.1f;
        } else {
            return 1;
        }
    }
}