package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class IOSearcher {
    public static boolean search(String word, String... fileNames) {
        for (String filename : fileNames) {
            Scanner myScaner = null;
            try {
                myScaner = new Scanner( new BufferedReader(new FileReader(filename)));
                while(myScaner.hasNext())
                {
                    if (word.equals(myScaner.next())) {
                        return true;
                    }
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException("File"+filename +"does not exsits");
            }
            finally {
                if (myScaner !=null)
                myScaner.close();
            }
        }
        return false;
    }
}
