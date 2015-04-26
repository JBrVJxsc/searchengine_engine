package me.xuzh.interfaces;

import me.xuzh.classes.Query;
import me.xuzh.classes.Result;
import org.apache.lucene.index.IndexReader;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Xu on 4/18/15.
 */
public interface IRanking {
    String getName();

    String getUrlName();

    List<Result> getResults(Query query, IndexReader reader, String field, int numOfResults) throws IOException;
}