import java.util.ArrayList;
import java.util.Scanner;

class Kain {
    private String nama;
    private String warna;
    private int panjang;
    private int jumlah;

    public Kain(String nama, String warna ,int panjang, int jumlah) {
        this.nama = nama;
        this.warna = warna;
        this.jumlah = jumlah;
        this.panjang = panjang;
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

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
    public int getPanjang() {
        return panjang;
    }

    public void setPanjang(int panjang) {
        this.panjang = panjang;
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
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StokKain stokKain = new StokKain();

        boolean lanjut = true;
        while (lanjut) {
            System.out.println("\nPilih operasi:");
            System.out.println("1. Tambah Kain");
            System.out.println("2. Lihat Stok Kain");
            System.out.println("3. Ubah Stok Kain");
            System.out.println("4. Hapus Kain");
            System.out.println("6. Keluar");
            System.out.print("Masukkan pilihan: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan nama kain: ");
                    String namaKain = scanner.nextLine();
                    System.out.print("Masukkan warna kain: ");
                    String warnaKain = scanner.nextLine();
                    System.out.print("Masukkan panjang kain: ");
                    int panjangKain = scanner.nextInt();
                    System.out.print("Masukkan jumlah kain: ");
                    int jumlahKain = scanner.nextInt();
                    scanner.nextLine();
                    stokKain.tambahKain(new Kain(namaKain, warnaKain, panjangKain, jumlahKain));
                    break;
                case 2:
                    stokKain.lihatStok();
                    break;
                case 3:
                    stokKain.lihatStok();
                    System.out.print("Masukkan nama kain yang akan diupdate: ");
                    String namaKainUpdate = scanner.nextLine();
                    System.out.print("Masukkan nama kain : ");
                    String namaBaru = scanner.nextLine();
                    System.out.print("Masukkan warna kain : ");
                    String warnaBaru = scanner.nextLine();
                    System.out.print("Masukkan panjang kain: ");
                    int panjangBaru = scanner.nextInt();
                    System.out.print("Masukkan jumlah kain: ");
                    int jumlahBaru = scanner.nextInt();
                    scanner.nextLine(); 
                    stokKain.updateKain(namaKainUpdate, namaBaru, warnaBaru, panjangBaru, jumlahBaru);
                    break;
                case 4:
                    stokKain.lihatStok();
                    System.out.print("Masukkan nama kain yang akan dihapus: ");
                    String namaKainHapus = scanner.nextLine();
                    stokKain.hapusKain(namaKainHapus);
                    break;
                case 6:
                    lanjut = false;
                    System.out.println("Anda keluar dari program, Selamat Tinggal :D");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
        scanner.close();
    }
}
