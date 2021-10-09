import javax.swing.text.NumberFormatter;
import java.text.DecimalFormat;

public class OgrenciBilet  extends Bilet{
    public OgrenciBilet(int biletNo, Ucus ucus, Musteri biletSahibi, double tabanFiyat) {
        super(biletNo, ucus, biletSahibi, tabanFiyat);
    }

    @Override
    public double BiletFiyati() {
        //20% indirim
        return getTabanFiyat()*0.8;
    }
    @Override
    public String toString(){
        return "Öğrenci Bileti(%20 indirimli) [Bilet Numarası: "+
                        getBiletNo()+" Bilet Fiyatı:  "+new DecimalFormat("#0.00").format(getTabanFiyat()) +
                "TL\nBilet Sahibi: "+getBiletSahibi()+"\nUçuş Bilgisi:"+getUcus().toString()+"\n";
    }
}
