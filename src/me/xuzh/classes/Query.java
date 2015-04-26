package me.xuzh.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Xu on 4/18/15.
 */
public class Query {
    private String id = "";
    private String title = "";
    private String description = "";

    public static List<Query> getQueries(String url) {
        // Check file.
        File file = new File(url);
        if (!file.exists()) {
            System.out.println("Could not find: " + url + ".");
            System.exit(-1);
        }

        // Read the file.
        System.out.println("Reading the file...");
        // Use a list to save all the query objects.
        List<me.xuzh.classes.Query> queryList = new ArrayList<Query>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            me.xuzh.classes.Query query = null;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("<num>")) {
                    // When we meet "<num>", we start with a new Doc object.
                    query = new me.xuzh.classes.Query();
                    query.setId(line.split(" ")[2]);
                } else if (line.startsWith("<title> ")) {
                    query.setTitle(line.replace("<title> ", ""));
                } else if (line.startsWith("<desc>")) {
                    query.setDescription(br.readLine());
                    // When we meet "<desc>", that means we have collected all information a query object needs.
                    // So just add this object to the document list.
                    queryList.add(query);
                }
            }
        } catch (Exception e) {
            System.out.println("Reading caught an exception:");
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        System.out.println(queryList.size() + " queries has been found.");
        return queryList;
    }

    public HashSet<String> getTokens() {
//        return TokenParser.parseTokens(new Stemmer().stem(title));
        return TokenParser.parseTokens(new Stemmer().stem(description));
//        return TokenParser.parseTokens(new Stemmer().stem(title + " " + description));
    }

    @Override
    public String toString() {
        return "Query{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
