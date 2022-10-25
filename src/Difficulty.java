public class Difficulty {
    private int size = 0;
    private int percentage = 0;

    public Difficulty(int size, int percentage) {
        this.size = size;
        this.percentage = percentage;
    }

    public int getSize() {
        return size;
    }

    public int getPercentage() {
        return Math.round((size * size) * (percentage / 100.f));
    }
}
