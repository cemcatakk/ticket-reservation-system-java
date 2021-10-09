import java.util.ArrayList;

public class Musteri implements IMusteri{
    private String Ad;
    private String Soyad;
    private ArrayList<Bilet> Biletler;

    private int cezaPuani;
    public Musteri(String ad, String soyad) {
        Ad = ad;
        Soyad = soyad;
        Biletler = new ArrayList<Bilet>();
        cezaPuani=0;
    }
    @Override
    public void BiletAl(Bilet bilet){
        //Ceza puanı varsa yüzdelik olarak ceza uygulanıyor
        getBiletler().add(bilet);
    }
    @Override
    public void Cezalandir(){
        //10 ceza puanı eklenir(en fazla 30)
        if (cezaPuani<30){
            cezaPuani+=10;
            System.out.println(getAd()+" "+getSoyad()+" Adlı Müşterisine 10 Ceza Puanı Eklendi.");
        }
        else
        System.out.print("Müşteri 30 ceza puanına ulaştı.");
    }
    public String getAd() {
        return Ad;
    }

    public void setAd(String ad) {
        Ad = ad;
    }

    public String getSoyad() {
        return Soyad;
    }

    public void setSoyad(String soyad) {
        Soyad = soyad;
    }

    public ArrayList<Bilet> getBiletler() {
        return Biletler;
    }

    public void setBiletler(ArrayList<Bilet> biletler) {
        Biletler = biletler;
    }

    public int getCezaPuani() {
        return cezaPuani;
    }

    public void setCezaPuani(int cezaPuani) {
        this.cezaPuani = cezaPuani;
    }

    @Override
    public String toString() {
        return "Musteri[" + "Ad: " + Ad  + " Soyad: " + Soyad +  " Bilet Sayısı: " + Biletler.size() + " Ceza Puanı: " + cezaPuani +  ']';
    }
}
