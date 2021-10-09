import java.util.ArrayList;
import java.util.Date;

public class Ucus  {
    private String Kod;
    private Sehir KalkisSehir;
    private Date Tarih;

    public Ucus(String kod, Sehir kalkisSehir, Date tarih) {
        Kod = kod;
        KalkisSehir = kalkisSehir;
        Tarih = tarih;
    }

    public String getKod() {
        return Kod;
    }

    public void setKod(String kod) {
        Kod = kod;
    }

    public Sehir getKalkisSehir() {
        return KalkisSehir;
    }

    public void setKalkisSehir(Sehir kalkisSehir) {
        KalkisSehir = kalkisSehir;
    }


    public Date getTarih() {
        return Tarih;
    }

    public void setTarih(Date tarih) {
        Tarih = tarih;
    }
    @Override
    public String toString(){
        return "Uçuş Bilgisi: [Kod: "+getKod()+" Kalkış Şehri: "+getKalkisSehir().toString()+" Tarih: "+getTarih()+"]";
    }
}
