package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Dictionary {
    private CacheManager exsistWord;
    private CacheManager nonExsistWord;
    private BloomFilter filter;
    private String[] allFiles;

    public Dictionary(String... fileNames) {
        this.exsistWord = new CacheManager(400, new LRU());
        this.nonExsistWord = new CacheManager(100, new LFU());
        this.filter = new BloomFilter(256, "SHA1", "MD5");
        this.allFiles = fileNames;
        for (String filename : fileNames) {
            Scanner myScaner = null;
            try {
                myScaner = new Scanner(new BufferedReader(new FileReader(filename)));
                while (myScaner.hasNext()) {
                    filter.add(myScaner.next());
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException("File" + filename + "does not exsits");
            } finally {
                if (myScaner != null)
                    myScaner.close();
            }
        }

    }

    public boolean query(String word) {
        if (exsistWord.query(word))
            return true;
        if (nonExsistWord.query(word))
            return false;
        if (filter.contains(word)) {
            exsistWord.add(word);
            return true;
        } else {
            nonExsistWord.add(word);
            return false;
        }
    }

    public boolean challenge(String word) {
        try {
            if (IOSearcher.search(word, allFiles)) {
                exsistWord.add(word);
                return true;
            } else {
                nonExsistWord.add(word);
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException("File does not exsits");
        }

    }
    public void updateCache(String word) {
        word = word.toLowerCase().trim();
        boolean exists = filter.contains(word);
        if (exists) {
            exsistWord.add(word);
        } else {
            nonExsistWord.add(word);
        }
    }
}
