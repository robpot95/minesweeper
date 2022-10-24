public enum Direction {
    NORTH(new Position(-1, 0)),
    SOUTH(new Position(1, 0)),
    EAST(new Position(0, 1)),
    WEST(new Position(0, -1)),
    NORTHEAST(new Position(-1, 1)),
    NORTHWEST(new Position(-1, -1)),
    SOUTHEAST(new Position(1, 1)),
    SOUTHWEST(new Position(1, -1));
    
    public Position position;

    private Direction(Position position) {
        this.position = position;
    }
}
