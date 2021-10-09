import java.util.Objects;

public class Sehir {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sehir sehir = (Sehir) o;
        return SehirAdi.equals(sehir.SehirAdi);
    }
    private String SehirAdi;
    public Sehir(String sehirAdi) {
        SehirAdi = sehirAdi;
    }


    public String getSehirAdi() {
        return SehirAdi;
    }

    public void setSehirAdi(String sehirAdi) {
        SehirAdi = sehirAdi;
    }

    @Override
    public String toString() {
        return getSehirAdi();
    }
}
