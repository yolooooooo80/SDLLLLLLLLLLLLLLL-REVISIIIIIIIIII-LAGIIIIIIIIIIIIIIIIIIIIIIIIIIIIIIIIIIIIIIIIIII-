 
import java.util.Scanner;
 
public class MainKasus {


    // UDAHHHHHHHHH CAPEEEEEKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK
 
 public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        KasusManager manager = new KasusManager();
        int pilihan;

        do {
            System.out.println("\n===== MENU KASUS KEJAHATAN =====");
            System.out.println("1. Tambah Kasus");
            System.out.println("2. Tampilkan Semua Kasus");
            System.out.println("3. Cari Kasus (ID / Tanggal)");
            System.out.println("4. Urutkan Kasus (Tingkat / Tanggal)");
            System.out.println("5. Hapus Kasus");
            System.out.println("0. Keluar");
            System.out.print("Pilihan: ");
            pilihan = Integer.parseInt(input.nextLine());

            switch (pilihan) {
                case 1: manager.tambahKasusBaru(); 
                    break;
                case 2: manager.tampilkanSemuaKasus(); 
                    break;
                case 3: manager.cariKasus(); 
                    break;
                case 4: manager.urutkanKasus(); 
                    break;
                case 5: manager.hapusKasusByID(); 
                    break;
                case 0: 
                    System.out.println("Terima kasih!"); break;
                default: 
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }
}