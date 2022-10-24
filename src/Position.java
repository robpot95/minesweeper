import java.util.Objects;

public class Position {
    public final int row;
    public final int column;
    
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        return this.row == ((Position)o).row && this.column == ((Position)o).column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString(){
        return "[" + row + "," + column + "]";
    }
}
