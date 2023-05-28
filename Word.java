package test;

import java.util.Arrays;

public class Word {
    private Tile[] tiles;
    private int row;
    private int col;
    private boolean vertical ;
    public Word(Tile[] tiles, int row, int col, boolean vertical) {
        this.tiles = tiles;
        this.col = col;
        this.row = row;
        this.vertical = vertical;
    }
    public Word() {
    }
    public Tile[] getTiles() {
        return tiles;
    }

    public int getCol() {
        return col;
    }
    public int getRow() {
        return row;
    }

    public boolean getVertical() {
        return vertical;
    }
    public int getLength() {
        return getTiles().length;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Word other = (Word) obj;
        return col == other.col && row == other.row && Arrays.equals(tiles, other.tiles) && vertical == other.vertical;
    }
}