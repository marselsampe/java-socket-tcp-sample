import java.io.Serializable;

public class Mahasiswa implements Serializable{
    String nim=null;
    String nama=null;
    String progdi=null;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getProgdi() {
        return progdi;
    }

    public void setProgdi(String progdi) {
        this.progdi = progdi;
    }
}
