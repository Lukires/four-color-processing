public enum Colors {
    RED(new int[]{255,0,0}), GREEN(new int[]{0,255,0}), BLUE(new int[]{0,0,255}), ORANGE(new int[]{255, 165, 0});

    private int[] RGB;
    public int usage = 0;
    Colors(int[] RGB) {
        this.RGB=RGB;
    }

    public int[] getRGB() {
        return RGB;
    }
}
