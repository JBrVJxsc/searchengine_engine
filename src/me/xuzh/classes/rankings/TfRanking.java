package me.xuzh.classes.rankings;

import me.xuzh.classes.WordClass;

import java.io.IOException;

/**
 * Created by Xu on 4/18/15.
 */
public class TfRanking extends BaseRanking {

    @Override
    public String getName() {
        return "Ranking-tf";
    }

    @Override
    public String getUrlName() {
        return "result_tf.txt";
    }

    @Override
    protected float getScore(int tf, float idf, WordClass wordClass) throws IOException {
        float score = (float) Math.sqrt(tf);
        return score;
    }
}
