package test;

import java.util.Objects;
import java.util.Random;

public class Tile {
    public final char letter;
    public final int score;
    public char getLetter() {
        return letter;
    }
    public int getScore() {
        return score;
    }
    private Tile(char letter, int score) {
        this.letter = letter;
        this.score = score;
    }
    @Override
    public int hashCode() {
        return Objects.hash(letter, score);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tile other = (Tile) obj;
        return letter == other.letter && score == other.score;
    }

    public static class Bag{
        public static int sizeOfTiles = 98; // static var contains the amount of Tiles
        private static Bag TheBag = null; // create empty bag
        public Bag() { }
        private int[] numOfLet = {9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1}; // starting array that contains number of any letter
        private final int[] OriginalnumOfLet = {9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1}; // original array that contains number of any letter
        Tile A = new Tile('A',1); //All of the letters with their score
        Tile B = new Tile('B',3);
        Tile C = new Tile('C',3);
        Tile D = new Tile('D',2);
        Tile E = new Tile('E',1);
        Tile F = new Tile('F',4);
        Tile G = new Tile('G',2);
        Tile H = new Tile('H',4);
        Tile I = new Tile('I',1);
        Tile J = new Tile('J',8);
        Tile K = new Tile('K',5);
        Tile L = new Tile('L',1);
        Tile M = new Tile('M',3);
        Tile N = new Tile('N',1);
        Tile O = new Tile('O',1);
        Tile P = new Tile('P',3);
        Tile Q = new Tile('Q',10);
        Tile R = new Tile('R',1);
        Tile S = new Tile('S',1);
        Tile T = new Tile('T',1);
        Tile U = new Tile('U',1);
        Tile V = new Tile('V',4);
        Tile W = new Tile('W',4);
        Tile X = new Tile('X',8);
        Tile Y = new Tile('Y',4);
        Tile Z = new Tile('Z',10);
        private Tile[] arrOfLet = {A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z}; //array of Tailes from A to Z.
        public Tile getRand() //return one random Tile
        {
            for(int i = 0;i<numOfLet.length;i++)// check if all the array is empty
            {
                if (numOfLet[i] != 0)
                    break;
                if (arrOfLet[i] == Z)
                {
                    return null;
                }
            }
            int rand=0;
            boolean flag = true;
            while (flag) // choose one random Tile , continue until find index which is not empty
            {
                rand = new Random().nextInt(arrOfLet.length);
                if (numOfLet[rand] != 0)
                {
                    numOfLet[rand]--;
                    flag = false;
                    sizeOfTiles--;
                }
            }
            return arrOfLet[rand];
        }
        public Tile getTile(char lett) // check if the there is letter of "lett" in the bag
        {
            if(lett >91 || lett <65)
                return null;
            int indexOfLet = lett-65; // pun in the index of the letter to the var
            if (numOfLet[indexOfLet] > 0)// there is letter.
            {
                numOfLet[indexOfLet]-- ;
                sizeOfTiles--;
                return arrOfLet[indexOfLet];
            }
            else
            {
                //System.out.println("there is no letter : " + lett + " in tht Bag");
                return null;
            }
        }
        public void put(Tile lett)// adds letter "lett" to the bag
        {
            int indexOfLet = lett.letter -65; // pun in the index of the letter to the var
            if (numOfLet[indexOfLet] < OriginalnumOfLet[indexOfLet]) {
                numOfLet[indexOfLet]++;
                sizeOfTiles++;
            }
        }
        public int size()// return the size of Tiles in the bag
        {
            return sizeOfTiles;
        }
        public int[] getQuantities() // create new array and copy from array- numOfLet.
        {
            int[] cpArrNum = new int [26];
            System.arraycopy(numOfLet, 0 ,cpArrNum ,0,26); // copy the array.
            return cpArrNum;
        }
        public static Bag getBag() // singelton - create single bag.
        {
            if (TheBag == null)
                TheBag = new Bag();
            return TheBag;
        }
    }
}

