public abstract class Bilet implements IBilet{
    private int biletNo;
    private Ucus ucus;
    private Musteri biletSahibi;
    private double tabanFiyat;

    public Bilet(int biletNo, Ucus ucus, Musteri biletSahibi, double tabanFiyat) {
        this.biletNo = biletNo;
        this.ucus = ucus;
        this.biletSahibi = biletSahibi;
        this.tabanFiyat = tabanFiyat+((tabanFiyat*biletSahibi.getCezaPuani())/100);
    }

    public int getBiletNo() {
        return biletNo;
    }

    public void setBiletNo(int biletNo) {
        this.biletNo = biletNo;
    }

    public Ucus getUcus() {
        return ucus;
    }

    public void setUcus(Ucus ucus) {
        this.ucus = ucus;
    }

    public Musteri getBiletSahibi() {
        return biletSahibi;
    }

    public void setBiletSahibi(Musteri biletSahibi) {
        this.biletSahibi = biletSahibi;
    }

    public double getTabanFiyat() {
        return tabanFiyat;
    }

    public void setTabanFiyat(double tabanFiyat) {
        this.tabanFiyat = tabanFiyat;
    }
    public abstract double BiletFiyati();

}
