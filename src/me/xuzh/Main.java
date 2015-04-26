package me.xuzh;

import me.xuzh.classes.NLP;
import me.xuzh.classes.Result;
import me.xuzh.classes.WordClass;
import me.xuzh.classes.rankings.BooleanRanking;
import me.xuzh.classes.rankings.MyRanking;
import me.xuzh.classes.rankings.TfIdfRanking;
import me.xuzh.classes.rankings.TfRanking;
import me.xuzh.interfaces.IRanking;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Main {

    public static void main(String[] args) throws Exception {
        // Check parameter.
        if (args.length < 1) {
            System.out.println("No file name provided.");
            System.exit(-1);
        }

        // Check file.
        File file = new File(args[0]);
        if (!file.exists()) {
            System.out.println("Could not find: " + args[0] + ".");
            System.exit(-1);
        }

        // Get the query list.
        List<me.xuzh.classes.Query> queryList = me.xuzh.classes.Query.getQueries(args[0]);

        String index = "index";
        String field = "T";
        int numOfResults = 50;

        IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(index)));

        // Do the queries.
        // Init the NLP Library.
        WordClass wordClass = NLP.getWordClass("");

        List<IRanking> iRankingList = new ArrayList<IRanking>();
        iRankingList.add(new BooleanRanking());
        iRankingList.add(new TfRanking());
        iRankingList.add(new TfIdfRanking());
        iRankingList.add(new MyRanking());

        long sum = 0;
        int testTime = 1;
        for (int i = 0; i < testTime; i++) {
            for (IRanking iRanking : iRankingList) {
                System.out.println("Current ranking: " + iRanking.getName());

                Date start = new Date();
                List<Result> resultList = new ArrayList<Result>();
                for (me.xuzh.classes.Query query : queryList) {
                    List<Result> results = iRanking.getResults(query, reader, field, numOfResults);
                    if (results == null || results.size() == 0) {
                        continue;
                    }
                    resultList.addAll(results);
                }
                Date end = new Date();
                Result.writeResult(resultList, iRanking.getUrlName());
                System.out.println(iRanking.getName() + " finished. Found " + resultList.size() + " results in " + (end.getTime() - start.getTime()) + " milliseconds.");
                sum += end.getTime() - start.getTime();
            }
        }
        System.out.println("Avg: " + sum / testTime + " milliseconds.");
        reader.close();
    }
}