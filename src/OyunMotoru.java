import java.util.Scanner;

public class OyunMotoru {
    private Oyuncu oyuncu1;
    private Oyuncu oyuncu2;
    private int tahtaBoyutu;

    public OyunMotoru() {

        tahtaBoyutu = 10;
        this.oyuncu1 = new Oyuncu(tahtaBoyutu);
        this.oyuncu2 = new Oyuncu(tahtaBoyutu);
    }

    public void baslat() {
        System.out.println("Oyun başladı!");
        Scanner scanner = new Scanner(System.in);
        System.out.println("İlk oyuncunun ismini giriniz:");
        String oyuncu1Isim = scanner.next();
        oyuncu1.setIsim(oyuncu1Isim);
        System.out.println("İkinci oyuncunun ismini giriniz:");
        String oyuncu2Isim = scanner.next();
        oyuncu2.setIsim(oyuncu2Isim);
        System.out.println(oyuncu1Isim + "'in tahtası:");
        oyuncu1.getTahta().tahtayiGoster();
        System.out.println(oyuncu2Isim + "'in tahtası:");
        oyuncu2.getTahta().tahtayiGoster();
        gemileriYerlestir(oyuncu1);
        gemileriYerlestir(oyuncu2);
        oyunBaslasin();
    }

    private void gemileriYerlestir(Oyuncu oyuncu) {
        System.out.println(oyuncu.getIsim() + " gemileri yerleştiriyor...");
        OyunTahtasi tahta = oyuncu.getTahta();
        for (int gemiBoyutu = 4; gemiBoyutu > 0; gemiBoyutu--) {
            System.out.println("Şu anda " + gemiBoyutu + " boyutunda bir gemi yerleştiriyorsunuz.");
            tahta.gemiYerlestir(gemiBoyutu);
            tahta.tahtayiGoster();
        }
    }

    private void oyunBaslasin() {
        while (true) {
            atisYap(oyuncu1, oyuncu2);
            if (oyuncu2.getTahta().oyunBittiMi()) {
                System.out.println("TEBRİKLER"+oyuncu1.getIsim() + " KAZANDIN! :)");
                break;
            }
            atisYap(oyuncu2, oyuncu1);
            if (oyuncu1.getTahta().oyunBittiMi()) {
                System.out.println("TEBRİKLER"+oyuncu2.getIsim() + " KAZANDIN! :)");
                break;
            }
        }
    }
    private boolean atisYap(Oyuncu atesEden, Oyuncu hedef) {
        System.out.println("Atış yapacak oyuncu -->"+atesEden.getIsim()+"(" + hedef.getIsim() + " 'in tahtası)");
        System.out.println("Atış yapmak için hedef koordinatı giriniz (örn. A1):");

        Scanner scanner = new Scanner(System.in);
        String hedefKoordinat = scanner.next().toUpperCase();
        char hedefHarf = hedefKoordinat.charAt(0);
        int hedefRakam = Integer.parseInt(hedefKoordinat.substring(1));

        int hedefSutun= hedefRakam - 1;
        int hedefSatir = hedefHarf - 'A';

        boolean isabetOldu = hedef.getTahta().isabetliAtis(hedefSatir, hedefSutun);
        return isabetOldu;
    }




}
