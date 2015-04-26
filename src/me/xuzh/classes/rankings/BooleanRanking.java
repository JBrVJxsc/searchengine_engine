package me.xuzh.classes.rankings;

import me.xuzh.classes.WordClass;

import java.io.IOException;

/**
 * Created by Xu on 4/18/15.
 */
public class BooleanRanking extends BaseRanking {
    @Override
    public String getName() {
        return "Ranking-Boolean";
    }

    @Override
    public String getUrlName() {
        return "result_boolean.txt";
    }

    @Override
    protected float getScore(int tf, float idf, WordClass wordClass) throws IOException {
        return 1;
    }
}