public class Gemi {
    private int boyut;
    private int [] konumX;
    private int [] konumY;

    public Gemi(int boyut, int konumX, int konumY) {
        this.boyut = boyut;
        this.konumX = new int[boyut];
        this.konumY = new int[boyut];
    }

}
