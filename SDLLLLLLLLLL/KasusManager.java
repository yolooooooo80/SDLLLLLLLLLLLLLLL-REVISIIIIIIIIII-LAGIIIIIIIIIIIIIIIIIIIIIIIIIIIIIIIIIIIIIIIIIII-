import java.util.Scanner;


// UDAHHH PUSINGGGGG KEPALA KU ANJINGGGGGGGGGGGGGGGGGG

public class KasusManager {
    private KasusKejahatan[] dataKasus = new KasusKejahatan[100];
    private int jumlahKasus = 0;
    private Scanner scanner = new Scanner(System.in);

    public void tampilkanSemuaKasus() {
        if (jumlahKasus == 0) {
            System.out.println("Belum ada data kasus.");
            return;
        }
        System.out.println("\n\t\t\t-- Daftar Kasus --");
        System.out.println("ID    | Jenis Kejahatan       | Lokasi     | Tanggal     | Status                 | Tingkat");
        System.out.println("-----------------------------------------------------------------------------------------------");
        for (int i = 0; i < jumlahKasus; i++) {
            System.out.println(dataKasus[i]);
        }
    }

    public void tambahKasusBaru() {
        System.out.print("ID Kasus: ");
        String id = scanner.nextLine();
        System.out.print("Jenis Kejahatan: ");
        String jenis = scanner.nextLine();
        System.out.print("Lokasi: ");
        String lokasi = scanner.nextLine();
        System.out.print("Tanggal (yyyy-mm-dd): ");
        String tanggal = scanner.nextLine();

        String status = "";
        while (true) {
            System.out.println("Pilih Status:\n1. Sudah Selesai\n2. Masih Dalam Penyelidikan\n3. Belum Selesai");
            System.out.print("Pilihan (1-3): ");
            String pilihan = scanner.nextLine();
            if (pilihan.equals("1")) { status = "Sudah Selesai"; break; }
            else if (pilihan.equals("2")) { status = "Masih Dalam Penyelidikan"; break; }
            else if (pilihan.equals("3")) { status = "Belum Selesai"; break; }
            else { System.out.println("Pilihan tidak valid."); }
        }

        String tingkat = "";
        while (true) {
            System.out.println("Tingkat Kasus:\n1. Berat\n2. Sedang\n3. Ringan");
            System.out.print("Pilihan (1-3): ");
            String pilih = scanner.nextLine();
            if (pilih.equals("1")) { tingkat = "Berat"; break; }
            else if (pilih.equals("2")) { tingkat = "Sedang"; break; }
            else if (pilih.equals("3")) { tingkat = "Ringan"; break; }
            else { System.out.println("Pilihan tidak valid."); }
        }

        dataKasus[jumlahKasus++] = new KasusKejahatan(id, jenis, lokasi, tanggal, status, tingkat);
        System.out.println("Kasus berhasil ditambahkan!");
    }

    public void cariKasus() {
        System.out.println("\n--- Cari Kasus ---");
        System.out.println("1. Berdasarkan ID");
        System.out.println("2. Berdasarkan Tanggal");
        System.out.print("Pilihan (1/2): ");
        String pilihan = scanner.nextLine();

        if (pilihan.equals("1")) {
            quickSortByID(0, jumlahKasus - 1);
            System.out.print("Masukkan ID Kasus: ");
            String id = scanner.nextLine();
            int index = binarySearchByID(id);
            if (index != -1) {
                System.out.println("\n\t\t\t-- Kasus Ditemukan --");
                System.out.println("ID    | Jenis Kejahatan       | Lokasi     | Tanggal     | Status                 | Tingkat");
                System.out.println("-----------------------------------------------------------------------------------------------");
                System.out.println(dataKasus[index]);
            } else {
                System.out.println("Kasus tidak ditemukan.");
            }
        } else if (pilihan.equals("2")) {
            System.out.print("Masukkan Tanggal Kasus (yyyy-mm-dd): ");
            String tanggal = scanner.nextLine();
            int[] hasil = binarySearchByTanggal(tanggal);
            if (hasil.length > 0) {
                System.out.println("\n\t\t\t-- Kasus Pada Tanggal Tersebut --");
                System.out.println("ID    | Jenis Kejahatan       | Lokasi     | Tanggal     | Status                 | Tingkat");
                System.out.println("-----------------------------------------------------------------------------------------------");
                for (int i : hasil) {
                    System.out.println(dataKasus[i]);
                }
            } else {
                System.out.println("Tidak ada kasus pada tanggal tersebut.");
            }
        } else {
            System.out.println("Pilihan tidak valid.");
        }
    }

    public void urutkanKasus() {
        System.out.println("\n--- Urutkan Kasus ---");
        System.out.println("1. Berdasarkan Tingkat Kasus");
        System.out.println("2. Berdasarkan Tanggal Kasus");
        System.out.print("Pilihan (1/2): ");
        String pilihan = scanner.nextLine();

        if (pilihan.equals("1")) {
            quickSortByTingkat(0, jumlahKasus - 1);
            System.out.println("\n-- Data Diurutkan berdasarkan Tingkat Kasus --");
        } else if (pilihan.equals("2")) {
            quickSortByTanggal(0, jumlahKasus - 1);
            System.out.println("\n-- Data Diurutkan berdasarkan Tanggal --");
        } else {
            System.out.println("Pilihan tidak valid.");
            return;
        }

        tampilkanSemuaKasus();
    }

    public void hapusKasusByID() {
        System.out.print("Masukkan ID Kasus yang ingin dihapus: ");
        String id = scanner.nextLine();
        for (int i = 0; i < jumlahKasus; i++) {
            if (dataKasus[i].idKasus.equalsIgnoreCase(id)) {
                for (int j = i; j < jumlahKasus - 1; j++) {
                    dataKasus[j] = dataKasus[j + 1];
                }
                jumlahKasus--;
                System.out.println("Kasus dengan ID " + id + " berhasil dihapus.");
                return;
            }
        }
        System.out.println("Kasus dengan ID tersebut tidak ditemukan.");
    }

   // SORTINGGGGGGGGGGGGGGGGGG DAN SEARCHINGGGGGGGGGGGGGGGGGG ANJINGGGGGGGGGGGGGGGGGG

    private void quickSortByTingkat(int low, int high) {
        if (low < high) {
            int pi = partitionTingkat(low, high);
            quickSortByTingkat(low, pi - 1);
            quickSortByTingkat(pi + 1, high);
        }
    }

    private int partitionTingkat(int low, int high) {
        KasusKejahatan pivot = dataKasus[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (dataKasus[j].compareTo(pivot) <= 0) {
                i++;
                KasusKejahatan temp = dataKasus[i];
                dataKasus[i] = dataKasus[j];
                dataKasus[j] = temp;
            }
        }
        KasusKejahatan temp = dataKasus[i + 1];
        dataKasus[i + 1] = dataKasus[high];
        dataKasus[high] = temp;
        return i + 1;
    }

    private void quickSortByTanggal(int low, int high) {
        if (low < high) {
            int pi = partitionTanggal(low, high);
            quickSortByTanggal(low, pi - 1);
            quickSortByTanggal(pi + 1, high);
        }
    }

    private int partitionTanggal(int low, int high) {
        KasusKejahatan pivot = dataKasus[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (dataKasus[j].tanggal.compareTo(pivot.tanggal) <= 0) {
                i++;
                KasusKejahatan temp = dataKasus[i];
                dataKasus[i] = dataKasus[j];
                dataKasus[j] = temp;
            }
        }
        KasusKejahatan temp = dataKasus[i + 1];
        dataKasus[i + 1] = dataKasus[high];
        dataKasus[high] = temp;
        return i + 1;
    }

    private void quickSortByID(int low, int high) {
        if (low < high) {
            int pi = partitionID(low, high);
            quickSortByID(low, pi - 1);
            quickSortByID(pi + 1, high);
        }
    }

    private int partitionID(int low, int high) {
        KasusKejahatan pivot = dataKasus[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (dataKasus[j].idKasus.compareToIgnoreCase(pivot.idKasus) <= 0) {
                i++;
                KasusKejahatan temp = dataKasus[i];
                dataKasus[i] = dataKasus[j];
                dataKasus[j] = temp;
            }
        }
        KasusKejahatan temp = dataKasus[i + 1];
        dataKasus[i + 1] = dataKasus[high];
        dataKasus[high] = temp;
        return i + 1;
    }

    private int binarySearchByID(String id) {
        int low = 0, high = jumlahKasus - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = dataKasus[mid].idKasus.compareToIgnoreCase(id);
            if (cmp == 0) return mid;
            else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    private int[] binarySearchByTanggal(String tanggal) {
        quickSortByTanggal(0, jumlahKasus - 1);
        int low = 0, high = jumlahKasus - 1;
        int firstMatch = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = dataKasus[mid].tanggal.compareTo(tanggal);
            if (cmp == 0) {
                firstMatch = mid;
                break;
            } else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }

        if (firstMatch == -1) return new int[0];

        int start = firstMatch;
        while (start > 0 && dataKasus[start - 1].tanggal.equals(tanggal)) start--;

        int end = firstMatch;
        while (end < jumlahKasus - 1 && dataKasus[end + 1].tanggal.equals(tanggal)) end++;

        int[] hasil = new int[end - start + 1];
        for (int i = 0; i < hasil.length; i++) hasil[i] = start + i;
        return hasil;
    }
}
