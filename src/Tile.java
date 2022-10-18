public class Tile {

    public boolean revealedTile;  // This square has a bomb
    public boolean flaggedTile;  // Marks this square with a flag
    public boolean mineHereTile;  // Bomb is placed on this square
    public boolean emptyTile; // None

    public int y;       // Y coordinate of this square
    public int x;       // X coordinate of this square


    public Tile() {
        revealedTile = false;
        flaggedTile = false;
        mineHereTile = false;
        emptyTile = false;
        y = 0;
        x = 0;
    }


    public boolean isEmptyTile() {
        return emptyTile;
    }

    public void setEmptyTile(boolean mineAdjacent) {
        this.emptyTile = mineAdjacent;
    }

    public Tile(boolean mineAdjacent) {
        this.emptyTile = mineAdjacent;
    }

    public boolean isRevealedTile() {
        return revealedTile;
    }

    public void setRevealedTile(boolean revealedTile) {
        this.revealedTile = revealedTile;
    }

    public boolean isFlaggedTile() {
        return flaggedTile;
    }

    public void setFlaggedTile(boolean flaggedTile) {
        this.flaggedTile = flaggedTile;
    }

    public boolean isMineHereTile() {
        return mineHereTile;
    }

    public void setMineHereTile(boolean mineHereTile) {
        this.mineHereTile = mineHereTile;
    }

    public int clear(){             // method to reveal the cell, returns 0 if there is a mine or number of mines nearby
        this.revealedTile = true;
        if(mineHereTile){
        return 0;
        }else
        {
            return emptyTile;
        }

        }
        }
