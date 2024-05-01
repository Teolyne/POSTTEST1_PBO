import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

abstract class Kain {
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

    public final String getNama() {
        return nama;
    }

    public final void setNama(String nama) {
        this.nama = nama;
    }

    public final String getWarna() {
        return warna;
    }

    public final void setWarna(String warna) {
        this.warna = warna;
    }

    public final int getPanjang() {
        return panjang;
    }

    public final void setPanjang(int panjang) {
        this.panjang = panjang;
    }

    public final int getJumlah() {
        return jumlah;
    }

    public final void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public abstract double hitungHarga();

    @Override
    public String toString() {
        return "Nama: " + nama + "\nWarna: " + warna + "\nPanjang: " + panjang + " meter\nJumlah: " + jumlah + " lembar";
    }
}

final class KainBiasa extends Kain {
    private double hargaPerMeter;

    public KainBiasa(String nama, String warna, int panjang, int jumlah, double hargaPerMeter) {
        super(nama, warna, panjang, jumlah);
        this.hargaPerMeter = hargaPerMeter;
    }

    public void setHargaPerMeter(double hargaPerMeter) {
        this.hargaPerMeter = hargaPerMeter;
    }

    @Override
    public String toString() {
        return super.toString() + "\nHarga per Meter: Rp " + hargaPerMeter;
    }

    @Override
    public final double hitungHarga() {
        return hargaPerMeter * panjang;
    }
}

final class KainPremium extends Kain {
    private String garansi;
    private double hargaPerMeterPremium;

    public KainPremium(String nama, String warna, int panjang, int jumlah, double hargaPerMeterPremium, String garansi) {
        super(nama, warna, panjang, jumlah);
        this.garansi = garansi;
        this.hargaPerMeterPremium = hargaPerMeterPremium;
    }

    public void setHargaPerMeter(double hargaPerMeterPremium) {
        this.hargaPerMeterPremium = hargaPerMeterPremium;
    }

    public String getGaransi() {
        return garansi;
    }

    public void setGaransi(String garansi) {
        this.garansi = garansi;
    }

    @Override
    public String toString() {
        return super.toString() + "\nGaransi: " + garansi + "\nHarga per Meter: Rp " + hargaPerMeterPremium;
    }

    @Override
    public final double hitungHarga() {
        return hargaPerMeterPremium * panjang;
    }
}

interface StokOperations {
    void tambahKain(Kain kain);
    void lihatStok();
    void hapusKain(String nama);
    void updateKain(String nama, String namaBaru, String warnaBaru, int panjangBaru, int jumlahBaru, double hargaPerMeterBaru);
}

abstract class StokKain implements StokOperations {
    public final ArrayList<Kain> stok;

    public StokKain() {
        this.stok = new ArrayList<>();
    }

    public final void tambahKain(Kain kain) {
        stok.add(kain);
        System.out.println("Kain berhasil ditambahkan ke stok.");
    }

    public final void lihatStok() {
        for (Kain kain : stok) {
            System.out.println(kain);
            System.out.println("===========================================");
        }
    }

    public abstract void hapusKain(String nama);

    public abstract void updateKain(String nama, String namaBaru, String warnaBaru, int panjangBaru, int jumlahBaru, double hargaPerMeterBaru);

    public static void validatePanjang(int panjang) throws IllegalArgumentException {
        if (panjang <= 0) {
            throw new IllegalArgumentException("Panjang kain harus lebih dari 0");
        }
    }

    public static void validateJumlah(int jumlah) throws IllegalArgumentException {
        if (jumlah <= 0) {
            throw new IllegalArgumentException("Jumlah kain harus lebih dari 0");
        }
    }
}

final class App {
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
        StokKain stokKain = new StokKain() {
            @Override
            public void hapusKain(String nama) {
                for (Iterator<Kain> iterator = stok.iterator(); iterator.hasNext();) {
                    Kain kain = iterator.next();
                    if (kain.getNama().equals(nama)) {
                        iterator.remove();
                        System.out.println("Kain " + nama + " berhasil dihapus dari stok.");
                        return;
                    }
                }
                System.out.println("Kain " + nama + " tidak ditemukan dalam stok.");
            }

            @Override
            public void updateKain(String nama, String namaBaru, String warnaBaru, int panjangBaru, int jumlahBaru, double hargaPerMeterBaru) {
                for (Kain kain : stok) {
                    if (kain.getNama().equals(nama)) {
                        kain.setNama(namaBaru);
                        kain.setWarna(warnaBaru);
                        kain.setPanjang(panjangBaru);
                        kain.setJumlah(jumlahBaru);
                        if (kain instanceof KainBiasa) {
                            ((KainBiasa) kain).setHargaPerMeter(hargaPerMeterBaru);
                        } else if (kain instanceof KainPremium) {
                            ((KainPremium) kain).setHargaPerMeter(hargaPerMeterBaru);
                        }
                        System.out.println("Stok kain " + nama + " berhasil diperbarui.");
                        return;
                    }
                }
                System.out.println("Kain " + nama + " tidak ditemukan dalam stok.");
            }
        };

        boolean lanjut = true;
        while (lanjut) {
            clearScreen();
            System.out.println("|==================================|");
            System.out.println("| Pendataan Kain Toko FTH Industry |");
            System.out.println("|==================================|\n");
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
                    System.out.println("|===================|");
                    System.out.println("| Tambah Kain Biasa |");
                    System.out.println("|===================|\n");
                    System.out.print("Masukkan nama kain biasa: ");
                    String namaKainBiasa = scanner.nextLine();
                    System.out.print("Masukkan warna kain biasa: ");
                    String warnaKainBiasa = scanner.nextLine();
                    System.out.print("Masukkan panjang kain biasa: ");
                    int panjangKainBiasa = scanner.nextInt();
                    StokKain.validatePanjang(panjangKainBiasa);
                    System.out.print("Masukkan jumlah kain biasa: ");
                    int jumlahKainBiasa = scanner.nextInt();
                    StokKain.validateJumlah(jumlahKainBiasa);
                    System.out.print("Masukkan harga per meter kain biasa: ");
                    double hargaPerMeterBiasa = scanner.nextDouble();
                    scanner.nextLine();
                    clearScreen();
                    stokKain.tambahKain(new KainBiasa(namaKainBiasa, warnaKainBiasa, panjangKainBiasa, jumlahKainBiasa, hargaPerMeterBiasa));
                    pressEnterToContinue();
                    break;
                case 2:
                    clearScreen();
                    System.out.println("|=====================|");
                    System.out.println("| Tambah Kain Premium |");
                    System.out.println("|=====================|\n");
                    System.out.print("Masukkan nama kain premium: ");
                    String namaKainPremium = scanner.nextLine();
                    System.out.print("Masukkan warna kain premium: ");
                    String warnaKainPremium = scanner.nextLine();
                    System.out.print("Masukkan panjang kain premium: ");
                    int panjangKainPremium = scanner.nextInt();
                    StokKain.validatePanjang(panjangKainPremium);
                    System.out.print("Masukkan jumlah kain premium: ");
                    int jumlahKainPremium = scanner.nextInt();
                    StokKain.validateJumlah(jumlahKainPremium);
                    scanner.nextLine();
                    System.out.print("Masukkan harga per meter kain premium: ");
                    double hargaPerMeterPremium = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Masukkan garansi kain premium: ");
                    String garansiKainPremium = scanner.nextLine();
                    clearScreen();
                    stokKain.tambahKain(new KainPremium(namaKainPremium, warnaKainPremium, panjangKainPremium, jumlahKainPremium, hargaPerMeterPremium, garansiKainPremium));
                    pressEnterToContinue();
                    break;
                case 3:
                    clearScreen();
                    System.out.println("|===========|");
                    System.out.println("| Stok Kain |");
                    System.out.println("|===========|\n");
                    stokKain.lihatStok();
                    pressEnterToContinue();
                    break;
                case 4:
                    clearScreen();
                    System.out.println("|================|");
                    System.out.println("| Ubah Data Kain |");
                    System.out.println("|================|\n");
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
                    StokKain.validatePanjang(panjangBaru);
                    System.out.print("Masukkan jumlah kain baru: ");
                    int jumlahBaru = scanner.nextInt();
                    StokKain.validateJumlah(jumlahBaru);
                    System.out.print("Masukkan harga per meter kain baru: ");
                    double hargaPerMeterBaru = scanner.nextDouble();
                    scanner.nextLine();
                    clearScreen();
                    stokKain.updateKain(namaKainUpdate, namaBaru, warnaBaru, panjangBaru, jumlahBaru, hargaPerMeterBaru);
                    pressEnterToContinue();
                    break;
                case 5:
                    clearScreen();
                    System.out.println("|============|");
                    System.out.println("| Hapus Kain |");
                    System.out.println("|============|\n");
                    stokKain.lihatStok();
                    pressEnterToContinue();
                    System.out.print("Masukkan nama kain yang akan dihapus: ");
                    String namaKainHapus = scanner.nextLine();
                    clearScreen();
                    stokKain.hapusKain(namaKainHapus);
                    pressEnterToContinue();
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
