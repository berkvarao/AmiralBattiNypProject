import java.util.Scanner;

public class OyunTahtasi {
    private char[][] tahta;
    private boolean[][] atisYapildi;
    public OyunTahtasi(char[][] tahta, boolean[][] atisYapildi) {
        this.tahta = tahta;
        this.atisYapildi = atisYapildi;
    }



    public void tahtayiGoster() {
        System.out.print("  ");
        for (int i = 0; i < tahta.length; i++) {
            System.out.print( (1 + i) + " ");
        }
        System.out.println();
        for (int i = 0; i < tahta.length; i++) {
            System.out.print((char)('A' + i) + " ");
            for (int j = 0; j < tahta[i].length; j++) {
                System.out.print(tahta[i][j] + " ");
            }
            System.out.println();
        }
    }
    public OyunTahtasi(int boyut) {
        this.tahta = new char[boyut][boyut];
        initializeTahta();
    }
    private void initializeTahta() {
        for (int i = 0; i < tahta.length; i++) {
            for (int j = 0; j < tahta[i].length; j++) {
                tahta[i][j] = '-';
            }
        }
    }

    public  void gemiYerlestir(int gemiBoyutu) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Başlangıç koordinatlarını girin (örn. A1):");
            String baslangicKoordinat = scanner.next().toUpperCase();
            char baslangicHarf = baslangicKoordinat.charAt(0);
            int baslangicRakam = Integer.parseInt(baslangicKoordinat.substring(1));
            if (baslangicHarf < 'A' || baslangicHarf >= (char) ('A' + tahta.length) || baslangicRakam < 1 || baslangicRakam > tahta.length) {
                System.out.println("Geçersiz koordinat girdiniz. Lütfen tekrar koordinat giriniz.");
                continue;
            }
            int baslangicSutun = baslangicRakam - 1;
            int baslangicSatir = baslangicHarf - 'A';
            System.out.println("Yönü belirleyin (yatay için --> Y, dikey için --> D):");
            char yon = scanner.next().toUpperCase().charAt(0);
            if (yon != 'Y' && yon != 'D') {
                System.out.println("Geçersiz yön seçtiniz. Lütfen tekrar seçiniz.");
                continue;
            }
            if ((yon == 'Y' && baslangicSutun + gemiBoyutu > tahta.length) || (yon == 'D' && baslangicSatir + gemiBoyutu > tahta.length)) {
                System.out.println("Gemi uzunluğu tahtanın dışına taşıyor. Lütfen yeni bir koordinat giriniz.");
                continue;
            }
            boolean yerleştirmeBaşarılı = true;
            if (yon == 'Y'||yon == 'y') {
                for (int i = 0; i < gemiBoyutu; i++) {
                    if (tahta[baslangicSatir][baslangicSutun + i] != '-') {
                        System.out.println("Gemi mevcut bir konumun üzerine yerleştirilemez. Lütfen yeni bir koordinat giriniz.");
                        yerleştirmeBaşarılı = false;
                        break;
                    }
                }
                if (yerleştirmeBaşarılı) {
                    for (int i = 0; i < gemiBoyutu; i++) {
                        tahta[baslangicSatir][baslangicSutun + i] = 'O';
                    }
                }
            } else {
                for (int i = 0; i < gemiBoyutu; i++) {
                    if (tahta[baslangicSatir + i][baslangicSutun] != '-') {
                        System.out.println("Gemi mevcut bir konumun üzerine yerleştirilemez. Lütfen yeni bir koordinat girin.");
                        yerleştirmeBaşarılı = false;
                        break;
                    }
                }
                if (yerleştirmeBaşarılı) {
                    for (int i = 0; i < gemiBoyutu; i++) {
                        tahta[baslangicSatir + i][baslangicSutun] = 'O';
                    }
                }
            }

            if (yerleştirmeBaşarılı) {
                break;
            }
        }
    }
    private boolean gemiKaldiMi() {
        for (int i = 0; i < tahta.length; i++) {
            for (int j = 0; j < tahta[i].length; j++) {
                if (tahta[i][j] == 'O') {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean oyunBittiMi() {
        return  !gemiKaldiMi();
    }
    public boolean isabetliAtis(int satir, int sutun) {
        if (tahta[satir][sutun] == 'X') {
            System.out.println("Bu hedefi daha önce vurdunuz!");
            return false;
        } else if (tahta[satir][sutun] == 'O') {
            System.out.println("Tebrikler! Hedefi vurdunuz!");
            tahta[satir][sutun] = 'X';
            return true;
        } else {
            System.out.println("Üzgünüm, Karavana! :(");
            return false;
        }

    }

}