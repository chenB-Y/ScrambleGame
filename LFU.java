package test;
import java.util.HashMap;
import java.util.Map;

public class LFU implements CacheReplacementPolicy {
    private Map<String,Integer> lfuMap;

    public LFU(){
        this.lfuMap = new HashMap();
    }

    @Override
    public void add(String word){
        lfuMap.put(word,lfuMap.getOrDefault(word, 0) + 1);
    }
    @Override
    public String remove(){
    String victim = null;
    int minFreq= Integer.MAX_VALUE;
    for (Map.Entry<String,Integer> entry : lfuMap.entrySet())
    {
        if (entry.getValue() < minFreq)
        {
            minFreq = entry.getValue();
            victim = entry.getKey();
        }
    }
    lfuMap.remove(victim);
    return victim;
    }
}