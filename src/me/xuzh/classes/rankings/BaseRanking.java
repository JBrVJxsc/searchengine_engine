package me.xuzh.classes.rankings;

import me.xuzh.classes.NLP;
import me.xuzh.classes.Query;
import me.xuzh.classes.Result;
import me.xuzh.classes.WordClass;
import me.xuzh.interfaces.IRanking;
import org.apache.lucene.index.*;
import org.apache.lucene.util.BytesRef;

import java.io.IOException;
import java.util.*;

/**
 * Created by Xu on 4/19/15.
 */
public abstract class BaseRanking implements IRanking {

    public abstract String getName();

    public abstract String getUrlName();

    public List<Result> getResults(Query query, IndexReader reader, String field, int numOfResults) throws IOException {
        Fields fields = MultiFields.getFields(reader);
        Terms terms = fields.terms(field);
        TermsEnum iterator = terms.iterator(null);
        HashSet<String> tokens = query.getTokens();

        BytesRef byteRef = null;
        HashMap<Integer, Float> map = new HashMap<Integer, Float>();
        while ((byteRef = iterator.next()) != null) {
            String term = byteRef.utf8ToString();
            if (!tokens.contains(term)) {
                continue;
            }
            WordClass wordClass = NLP.getWordClass(term);

            PostingsEnum postingsEnum = iterator.postings(null, null, PostingsEnum.OFFSETS);
            int docFreq = reader.docFreq(new Term(field, term));
            float idf = (float) (1 + Math.log(reader.numDocs() / (1 + docFreq)));
            int nextDoc;
            while ((nextDoc = postingsEnum.nextDoc()) != PostingsEnum.NO_MORE_DOCS) {
                float score = getScore(postingsEnum.freq(), idf, wordClass);
                if (map.containsKey(nextDoc)) {
                    map.put(nextDoc, map.get(nextDoc) + score);
                } else {
                    map.put(nextDoc, score);
                }
            }
        }

        List<Result> resultList = new ArrayList<Result>();
        for (Map.Entry<Integer, Float> entry : map.entrySet()) {
            Result result = new Result();
            result.setIndexDocID(entry.getKey());
            result.setQueryID(query.getId());
            result.setScore(entry.getValue());
            result.setRunID(getName());
            resultList.add(result);
        }

        Collections.sort(resultList);
        if (resultList.size() == 0) {
            return null;
        }
        resultList = resultList.subList(0, Math.min(resultList.size(), numOfResults));
        for (int i = 0; i < resultList.size(); i++) {
            Result result = resultList.get(i);
            result.setDocID(reader.document(result.getIndexDocID()).getValues("U")[0]);
            result.setRank(i + 1);
        }

        return resultList;
    }

    protected abstract float getScore(int tf, float idf, WordClass wordClass) throws IOException;
}
