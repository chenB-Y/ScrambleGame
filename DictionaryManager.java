package test;

import java.util.HashMap;
import java.util.Map;
public class DictionaryManager {
    private static DictionaryManager instance;
    private Map<String, Dictionary> dictionaries;

    public DictionaryManager() {
        this.dictionaries = new HashMap<>();
    }

    public static DictionaryManager get() {
        if (instance == null) {
            instance = new DictionaryManager();
        }
        return instance;
    }

    public boolean query(String... books) {
        String word = books[books.length - 1].trim();
        for (int i = 0; i < books.length - 1; i++) {
            String book = books[i];
            if (!dictionaries.containsKey(book)) {
                dictionaries.put(book, new Dictionary(book));
            }
            Dictionary dictionary = dictionaries.get(book);
            if (dictionary.query(word)) {
                return true;
            }
            dictionary.updateCache(word);
        }
        return false;
    }

    public boolean challenge(String... books) {
        String word = books[books.length - 1].trim();
        for (int i = 0; i < books.length - 1; i++) {
            String book = books[i];
            if (!dictionaries.containsKey(book)) {
                dictionaries.put(book, new Dictionary(book));
            }
            Dictionary dictionary = dictionaries.get(book);
            if (!dictionary.query(word)) {
                dictionary.updateCache(word);

            }
            else {
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        return dictionaries.size();
    }
}

