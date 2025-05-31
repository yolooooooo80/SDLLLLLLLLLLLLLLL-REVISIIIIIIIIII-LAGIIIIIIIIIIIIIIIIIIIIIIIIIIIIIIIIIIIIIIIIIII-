public class KasusKejahatan implements Comparable<KasusKejahatan> {
    public String idKasus;
    public String jenis;
    public String lokasi;
    public String tanggal;
    public String status;
    public String tingkat;

    // YAELAHHHHHHH MALAH LUPAA GETTER SETTERRRRRR PUKIMAKKKKKKKKKKKKKKK

    public KasusKejahatan(String idKasus, String jenis, String lokasi, String tanggal, String status, String tingkat) {
        this.idKasus = idKasus;
        this.jenis = jenis;
        this.lokasi = lokasi;
        this.tanggal = tanggal;
        this.status = status;
        this.tingkat = tingkat;
    }

    // Urutan tingkat untuk compareTo
    private int getLevelValue(String tingkat) {
        switch (tingkat.toLowerCase()) {
            case "berat": return 1;
            case "sedang": return 2;
            case "ringan": return 3;
            default: return 4;
        }
    }

    @Override
    public int compareTo(KasusKejahatan other) {
        return Integer.compare(getLevelValue(this.tingkat), getLevelValue(other.tingkat));
    }

    @Override
    public String toString() {
        return String.format("%-6s| %-22s| %-11s| %-12s| %-23s| %-7s",
                idKasus, jenis, lokasi, tanggal, status, tingkat);
    }
}
