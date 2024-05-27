
class Oyuncu {
    private String isim;
    private OyunTahtasi tahta;
    public Oyuncu(int tahtaBoyutu) {
        this.tahta = new OyunTahtasi(tahtaBoyutu);
    }
    public String getIsim() {
        return isim;
    }
    public void setIsim(String isim) {
        this.isim = isim;
    }
    public OyunTahtasi getTahta() {
        return tahta;
    }

}
