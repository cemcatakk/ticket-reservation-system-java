import java.util.Date;

public interface ISistem {
    void BiletGoruntule();
    void BiletAl();
    void BiletIptalEt();
    void Menu();
    void BiletIptalEt(Musteri musteri,int BiletNo);
    void BiletIptalEt(Musteri musteri,Bilet bilet);
    void BiletIptalEt(Musteri musteri,Ucus ucus);
    void TumBiletleriListele();
    void TumMusterileriListele();
    void TumUcuslariListele();
    Musteri musteriBul(String Ad,String Soyad);
    Ucus UcusGirdisiAl();
    Ucus UcusBul(String kod);
    void UcusListele(Sehir sehir,Date tarih);
    int UcusSayisi(Sehir sehir,Date tarih);
    void SehirYazdir();
    int SehirGirdisiAl();
    Date TarihGirdisiAl();
    double rastgeleFiyat();
    void Yonetici();
    int biletNoAl(Musteri musteri);
    Bilet biletBul(int biletNo);
}
