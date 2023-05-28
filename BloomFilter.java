package test;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Map;

public class BloomFilter {
    private int size;
    private List<MessageDigest> hashFunctions;
    private BitSet bits;

    public BloomFilter(int _size, String... algs){
        this.size = _size;
        this.bits = new BitSet(size);
        this.hashFunctions = new ArrayList();
        for (String alg : algs )
        {
            try {
                hashFunctions.add(MessageDigest.getInstance(alg));
            }
            catch (NoSuchAlgorithmException e) {
                throw new IllegalArgumentException("Invalid hash function: " + alg);
            }
        }
    }
    public void add(String word) {
        for (MessageDigest hashFunc : hashFunctions) {
            byte[] bts = hashFunc.digest(word.getBytes());
            int index = (Math.abs(new BigInteger(bts).intValue())) % size;
            bits.set(index);
        }
    }
    public boolean contains(String word){
        for(MessageDigest hashFunc : hashFunctions)
        {
            byte[] bts = hashFunc.digest(word.getBytes());
            int index = (Math.abs(new BigInteger(bts).intValue())) % size;
            if (!(bits.get(index)))
                return false;
        }
        return true;
    }
    @Override
    public String toString(){
        StringBuilder nwstr = new StringBuilder();
        for (int i = 0 ; i<bits.length();i++)
        {
            nwstr = nwstr.append(bits.get(i) ? "1" : "0");
        }
        return nwstr.toString();
    }
}
