import java.util.ArrayList;
import java.util.Scanner;

class Kain {
    protected String nama;
    protected String warna;
    protected int panjang;
    protected int jumlah;

    public Kain(String nama, String warna, int panjang, int jumlah) {
        this.nama = nama;
        this.warna = warna;
        this.panjang = panjang;
        this.jumlah = jumlah;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public int getPanjang() {
        return panjang;
    }

    public void setPanjang(int panjang) {
        this.panjang = panjang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
}

class KainBiasa extends Kain {
    private double hargaPerMeter;

    public KainBiasa(String nama, String warna, int panjang, int jumlah, double hargaPerMeter) {
        super(nama, warna, panjang, jumlah);
        this.hargaPerMeter = hargaPerMeter;
    }

    public double getHargaPerMeter() {
        return hargaPerMeter;
    }

    public void setHargaPerMeter(double hargaPerMeter) {
        this.hargaPerMeter = hargaPerMeter;
    }
}

class KainPremium extends Kain {
    private String garansi;

    public KainPremium(String nama, String warna, int panjang, int jumlah, String garansi) {
        super(nama, warna, panjang, jumlah);
        this.garansi = garansi;
    }

    public String getGaransi() {
        return garansi;
    }

    public void setGaransi(String garansi) {
        this.garansi = garansi;
    }
}

class StokKain {
    private ArrayList<Kain> stok;

    public StokKain() {
        this.stok = new ArrayList<>();
    }

    public void tambahKain(Kain kain) {
        stok.add(kain);
        System.out.println("Kain berhasil ditambahkan ke stok.");
    }

    public void lihatStok() {
        System.out.println("|===========|");
        System.out.println("| Stok Kain |");
        System.out.println("|===========|");
        for (Kain kain : stok) {
            System.out.println("Nama: " + kain.getNama());
            System.out.println("Warna: " + kain.getWarna());
            System.out.println("Panjang: " + kain.getPanjang() + " meter");
            System.out.println("Jumlah: " + kain.getJumlah() + " lembar");
            if (kain instanceof KainBiasa) {
                System.out.println("Harga per Meter: " + "Rp " + ((KainBiasa) kain).getHargaPerMeter());
            } else if (kain instanceof KainPremium) {
                System.out.println("Garansi: " + ((KainPremium) kain).getGaransi());
            }
            System.out.println("===========================================");
        }
    }

    public void hapusKain(String nama) {
        for (Kain kain : stok) {
            if (kain.getNama().equals(nama)) {
                stok.remove(kain);
                System.out.println("Kain berhasil dihapus dari stok.");
                return;
            }
        }
        System.out.println("Kain tidak ditemukan dalam stok.");
    }

    public void updateKain(String nama, String namaBaru, String warnaBaru, int panjangBaru, int jumlahBaru) {
        for (Kain kain : stok) {
            if (kain.getNama().equals(nama)) {
                kain.setNama(namaBaru);
                kain.setWarna(warnaBaru);
                kain.setPanjang(panjangBaru);
                kain.setJumlah(jumlahBaru);
                System.out.println("Stok kain berhasil diperbarui.");
                return;
            }
        }
        System.out.println("Kain tidak ditemukan dalam stok.");
    }
}


public class App {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void pressEnterToContinue() {
        System.out.println("Tekan ENTER untuk melanjutkan");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StokKain stokKain = new StokKain();

        boolean lanjut = true;
        while (lanjut) {
            clearScreen();
            System.out.println("\nPilih operasi:");
            System.out.println("1. Tambah Kain Biasa");
            System.out.println("2. Tambah Kain Premium");
            System.out.println("3. Lihat Stok Kain");
            System.out.println("4. Ubah Stok Kain");
            System.out.println("5. Hapus Kain");
            System.out.println("6. Keluar");
            System.out.print("Masukkan pilihan: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    clearScreen();
                    System.out.print("Masukkan nama kain biasa: ");
                    String namaKainBiasa = scanner.nextLine();
                    System.out.print("Masukkan warna kain biasa: ");
                    String warnaKainBiasa = scanner.nextLine();
                    System.out.print("Masukkan panjang kain biasa: ");
                    int panjangKainBiasa = scanner.nextInt();
                    System.out.print("Masukkan jumlah kain biasa: ");
                    int jumlahKainBiasa = scanner.nextInt();
                    System.out.print("Masukkan harga per meter kain biasa: ");
                    double hargaPerMeterBiasa = scanner.nextDouble();
                    scanner.nextLine();
                    stokKain.tambahKain(new KainBiasa(namaKainBiasa, warnaKainBiasa, panjangKainBiasa, jumlahKainBiasa, hargaPerMeterBiasa));
                    break;
                case 2:
                    clearScreen();
                    System.out.print("Masukkan nama kain premium: ");
                    String namaKainPremium = scanner.nextLine();
                    System.out.print("Masukkan warna kain premium: ");
                    String warnaKainPremium = scanner.nextLine();
                    System.out.print("Masukkan panjang kain premium: ");
                    int panjangKainPremium = scanner.nextInt();
                    System.out.print("Masukkan jumlah kain premium: ");
                    int jumlahKainPremium = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Masukkan garansi kain premium: ");
                    String garansiKainPremium = scanner.nextLine();
                    stokKain.tambahKain(new KainPremium(namaKainPremium, warnaKainPremium, panjangKainPremium, jumlahKainPremium, garansiKainPremium));
                    break;
                case 3:
                    clearScreen();
                    stokKain.lihatStok();
                    pressEnterToContinue();
                    break;
                case 4:
                    clearScreen();
                    stokKain.lihatStok();
                    pressEnterToContinue();
                    System.out.print("Masukkan nama kain yang akan diupdate: ");
                    String namaKainUpdate = scanner.nextLine();
                    System.out.print("Masukkan nama kain baru: ");
                    String namaBaru = scanner.nextLine();
                    System.out.print("Masukkan warna kain baru: ");
                    String warnaBaru = scanner.nextLine();
                    System.out.print("Masukkan panjang kain baru: ");
                    int panjangBaru = scanner.nextInt();
                    System.out.print("Masukkan jumlah kain baru: ");
                    int jumlahBaru = scanner.nextInt();
                    scanner.nextLine();
                    stokKain.updateKain(namaKainUpdate, namaBaru, warnaBaru, panjangBaru, jumlahBaru);
                    break;
                case 5:
                    clearScreen();
                    stokKain.lihatStok();
                    pressEnterToContinue();
                    System.out.print("Masukkan nama kain yang akan dihapus: ");
                    String namaKainHapus = scanner.nextLine();
                    stokKain.hapusKain(namaKainHapus);
                    break;
                case 6:
                    clearScreen();
                    lanjut = false;
                    System.out.println("Anda keluar dari program, Selamat Tinggal :D");
                    break;
                default:
                    clearScreen();
                    System.out.println("Pilihan tidak valid.");
                    pressEnterToContinue();
            }
        }
        scanner.close();
    }
}