package me.xuzh.classes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Xu on 4/18/15.
 */
public class Result implements Comparable<Result> {
    private String queryID = "";
    private String q0 = "Q0";
    private String docID = "";
    private int indexDocID = 0;
    private int rank = 0;
    private float score = 0.0f;
    private String runID = "";

    public static void writeResult(List<Result> resultList, String url) throws IOException {
        if (resultList == null || resultList.size() == 0) {
            return;
        }

        BufferedWriter writer = null;
        File file = new File(url);
        writer = new BufferedWriter(new FileWriter(file));

        for (int i = 0; i < resultList.size(); i++) {
            Result result = resultList.get(i);
            writer.write(result.toString());
        }

        writer.close();
    }

    public String getQueryID() {
        return queryID;
    }

    public void setQueryID(String queryID) {
        this.queryID = queryID;
    }

    public String getQ0() {
        return q0;
    }

    public void setQ0(String q0) {
        this.q0 = q0;
    }

    public String getDocID() {
        return docID;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }

    public int getIndexDocID() {
        return indexDocID;
    }

    public void setIndexDocID(int indexDocID) {
        this.indexDocID = indexDocID;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getRunID() {
        return runID;
    }

    public void setRunID(String runID) {
        this.runID = runID;
    }

    @Override
    public int compareTo(Result o) {
        return Float.compare(o.score, score);
    }

    @Override
    public String toString() {
        return queryID + "\t" + q0 + "\t" + docID + "\t" + rank + "\t" + score + "\t" + runID + "\n";
    }
}
