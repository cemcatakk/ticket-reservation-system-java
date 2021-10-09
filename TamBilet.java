import java.text.DecimalFormat;

public class TamBilet extends Bilet {
    public TamBilet(int biletNo, Ucus ucus, Musteri biletSahibi, double tabanFiyat) {
        super(biletNo, ucus, biletSahibi, tabanFiyat);
    }

    @Override
    public double BiletFiyati() {
        return getTabanFiyat();
    }
    @Override
    public String toString(){
        return String.format("Tam Bilet[Bilet Numarası: "+getBiletNo()+" Bilet Fiyatı: "
                +new DecimalFormat("#0.00").format(getTabanFiyat()) +"TL\nBilet Sahibi:" +
                " "+getBiletSahibi()+"\nUçuş Bilgisi:"+getUcus().toString()+"\n",BiletFiyati());
    }
}
