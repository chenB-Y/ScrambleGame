package test;

import java.util.HashSet;

public class CacheManager {
    private int size;
    private CacheReplacementPolicy crp;
    private HashSet<String> cache;

    public CacheManager(int _size,CacheReplacementPolicy _crp){
        this.size = _size;
        this.crp = _crp;
        this.cache = new HashSet<>();
    }
    public boolean query(String word){
        if (cache.contains(word))
            return true;
        else
            return false;
    }
    public void add(String word){
        crp.add(word);
        if (cache.size() == size) {
            String rm = crp.remove();
            cache.remove(rm);
        }
        cache.add(word);
    }
}
