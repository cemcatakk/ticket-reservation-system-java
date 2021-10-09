import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Sistem implements ISistem{
    private static final int MAXFIYAT=200;
    private static final int MINFIYAT=50;
    Scanner sc = new Scanner(System.in);
    private ArrayList<Ucus> Ucuslar;
    private ArrayList<Musteri> Musteriler;
    private ArrayList<Sehir> Sehirler;
    private int biletNo;

    public Sistem() {
        Ucuslar = new ArrayList<Ucus>();
        Musteriler = new ArrayList<Musteri>();
        Sehirler = new ArrayList<Sehir>();
        biletNo=0;
        SehirEkle();
        OrnekUcusEkle();
        Menu();
    }
    private Sehir rastgeleSehir(){
        return getSehirler().get(new Random().nextInt(getSehirler().size()));
    }
    private void OrnekUcusEkle(){
        getUcuslar().add(new Ucus("T090H",rastgeleSehir(),new Date()));
        getUcuslar().add(new Ucus("T110M",rastgeleSehir(),new Date()));
        getUcuslar().add(new Ucus("C980I",rastgeleSehir(),new Date("1/1/2021")));
        getUcuslar().add(new Ucus("E900I",rastgeleSehir(),new Date("2/1/2021")));
        getUcuslar().add(new Ucus("C150I",rastgeleSehir(),new Date("3/1/2021")));
    }
    private void SehirEkle(){
        getSehirler().add(new Sehir("İzmir"));
        getSehirler().add(new Sehir("İstanbul"));
        getSehirler().add(new Sehir("Ankara"));
        getSehirler().add(new Sehir("Adana"));
    }
    @Override
    public void Menu(){
        int secim;
        do {
            System.out.print("1-Bilet Al\n2-Bilet Görüntüle\n3-Bilet İptal Et\n>>");
            secim=sc.nextInt();
            sc.nextLine();
            switch (secim){
                case 1:
                    BiletAl();
                    break;
                case 2:
                    BiletGoruntule();
                    break;
                case 3:
                    BiletIptalEt();
                    break;
                case 0:
                    System.out.println("Güle güle..");
                    break;
                    //Gizli yonetici menusu icin 99 girilmeli
                case 99:
                    Yonetici();
                    break;
            }
        }while(secim!=0);
    }

    @Override
    public void Yonetici() {
        int secim;
        do {
            System.out.print("1-Tüm biletler\n2-Tüm Müşteriler\n3-Tüm Uçuşlar\n0-Geri\n>>");
            secim=sc.nextInt();
            sc.nextLine();
            switch (secim){
                case 1:
                    TumBiletleriListele();
                    break;
                case 2:
                    TumMusterileriListele();
                    break;
                case 3:
                    TumUcuslariListele();
                    break;
                case 0:
                    System.out.println("Üst menüye dönülüyor..");
                    break;
            }
        }while(secim!=0);
    }


    @Override
    public void BiletGoruntule() {
        String Ad,Soyad;
        System.out.print("Adınız:");
        Ad = sc.nextLine();
        System.out.print("Soyadınız:");
        Soyad = sc.nextLine();
        Musteri musteri = musteriBul(Ad,Soyad);
        if (musteri==null){
            System.out.println("Bu isme ait bir müşteri bulunamadı.");
        }
        else{
            if (musteri.getBiletler().size()==0){
                System.out.println("Kayıtlı biletiniz yoktur.");
            }
            else{
                System.out.print("-----"+musteri.toString()+" Adlı Müşterinin Biletleri-----\n");
                for (Bilet bilet:musteri.getBiletler()){
                    System.out.println(bilet.toString());
                }
            }
        }
    }

    @Override
    public void BiletAl() {
        String Ad,Soyad;
        System.out.print("Adınız:");
        Ad = sc.nextLine();
        System.out.print("Soyadınız:");
        Soyad = sc.nextLine();
        Musteri musteri = musteriBul(Ad,Soyad);
        if (musteri==null){
            System.out.println("Yeni Müşteri Kaydı Oluşturuldu.");
            musteri = new Musteri(Ad,Soyad);
            getMusteriler().add(musteri);
        }
        SehirYazdir();
        System.out.println("Şehir Seçiniz:");
        Sehir sehir = getSehirler().get(SehirGirdisiAl());
        Date tarih = TarihGirdisiAl();
        Ucus ucus;
        if(UcusSayisi(sehir,tarih)>0){
            UcusListele(sehir,tarih);
            ucus=UcusGirdisiAl();
        }
        else{
            System.out.println("Bu bilgilere ait uçuş bulunamadı. Ana menüye dönülüyor..");
            return;
        }
        int biletTuru;
        do {
            System.out.print("1-Öğrenci\n2-Normal\n>>");
            biletTuru=sc.nextInt();
            if(biletTuru==1||biletTuru==2)break;
        }while(true);
        Bilet yeniBilet;
        if (biletTuru==1){
            yeniBilet = new OgrenciBilet(biletNo,ucus,musteri,rastgeleFiyat());
        }
        else{
            yeniBilet = new TamBilet(biletNo,ucus,musteri,rastgeleFiyat());
        }
        System.out.println("Bilet Özeti:\n"+yeniBilet.toString());
        int onay;
        do {
            System.out.print("1-Bileti Onayla\n2-İptal Et\n>>");
            onay=sc.nextInt();
            if(onay==1||onay==2)break;
        }while(true);
        if (onay==1){
            musteri.BiletAl(yeniBilet);
            System.out.println("Bilet satışınız onaylanmıştır!");
            biletNo++;
        }
    }

    @Override
    public void BiletIptalEt() {
        String Ad,Soyad;
        System.out.print("Adınız:");
        Ad = sc.nextLine();
        System.out.print("Soyadınız:");
        Soyad = sc.nextLine();
        Musteri musteri = musteriBul(Ad,Soyad);
        if (musteri==null){
            System.out.println("Müşteri kaydı bulunamadı.");
            return;
        }
        if (musteri.getBiletler().size()>0){
            System.out.print("-----"+musteri.toString()+" Adlı Müşterinin Biletleri-----\n");
            for (Bilet bilet:musteri.getBiletler()){
                System.out.println(bilet.toString());
            }
            int biletNo = biletNoAl(musteri);
            int onay;
            while(true){
                System.out.print("1-İptali Onayla\n2-İptalden Vazgeç\n>>");
                onay=sc.nextInt();
                if(onay==1||onay==2)break;
            }
            if(onay==1){
                BiletIptalEt(musteri,biletNo);
            }

        }
        else System.out.println("Hiç biletiniz yok.");
    }

    @Override
    public int biletNoAl(Musteri musteri) {
        int biletNo;
        while (true){
            System.out.println("Bilet numarası giriniz:");
            biletNo=sc.nextInt();
            sc.nextLine();
            for (Bilet bilet:musteri.getBiletler()){
                if (bilet.getBiletNo()==biletNo)return biletNo;
            }
            System.out.println("Bilet bulunamadı.");
        }
    }

    @Override
    public Bilet biletBul(int biletNo) {
        for (Musteri musteri:getMusteriler()){
            for (Bilet bilet:musteri.getBiletler()){
                if (bilet.getBiletNo()==biletNo)return bilet;
            }
        }
        return null;
    }

    @Override
    public void BiletIptalEt(Musteri musteri, int biletNo) {
        for (Object bilet:musteri.getBiletler().toArray()){
            if (((Bilet)bilet).getBiletNo()==biletNo){
                BiletIptalEt(musteri,(Bilet)bilet);
                System.out.println(biletNo+" Numaralı bilet iptal edildi.");
                break;
            }
        }
    }

    @Override
    public void BiletIptalEt(Musteri musteri, Bilet bilet) {
        musteri.getBiletler().remove(biletBul(biletNo));
        musteri.Cezalandir();
    }

    @Override
    public void BiletIptalEt(Musteri musteri, Ucus ucus) {
        for (Object bilet:musteri.getBiletler().toArray()){
            if (((Bilet)bilet).getUcus().getKod().equals(ucus.getKod())){
                BiletIptalEt(musteri,(Bilet)bilet);
                System.out.println(ucus.getKod()+" Kodlu uçuşa ait tüm biletler iptal edildi.");
            }
        }
    }

    @Override
    public void TumBiletleriListele() {
        System.out.println("\n-----Sisteme kayıtlı tüm biletler-----");
        for (Musteri musteri:getMusteriler()){
            for (Bilet bilet:musteri.getBiletler()){
                System.out.println(bilet.toString());
            }
        }
        System.out.println("----------------------------------------\n");
    }

    @Override
    public void TumMusterileriListele() {
        System.out.println("\n-----Sisteme kayıtlı tüm Musteriler-----");
        for (Musteri musteri:getMusteriler()){
            System.out.println(musteri.toString());
        }
        System.out.println("----------------------------------------\n");
    }

    @Override
    public void TumUcuslariListele() {
        System.out.println("\n-----Sisteme kayıtlı tüm Uçuşlar-----");
        for (Ucus ucus:getUcuslar()){
            System.out.println(ucus.toString());
        }
        System.out.println("----------------------------------------\n");
    }

    @Override
    public Musteri musteriBul(String Ad, String Soyad) {
        for (Musteri musteri:getMusteriler()){
            if (musteri.getAd().toLowerCase().equals(Ad.toLowerCase())&&musteri.getSoyad().toLowerCase().equals(Soyad.toLowerCase()))return musteri;
        }
        return null;
    }

    @Override
    public Ucus UcusGirdisiAl() {
        String kod;
        while (true){
            System.out.println("Uçuş kodu giriniz:");
            kod=sc.nextLine();
            if (UcusBul(kod)==null){
                System.out.println("Hatalı kod girdiniz.");
            }
            else return UcusBul(kod);
        }
    }

    @Override
    public Ucus UcusBul(String kod) {
        for (Ucus ucus:getUcuslar()){
            if (ucus.getKod().equals(kod))return ucus;
        }
        return null;
    }

    @Override
    public void UcusListele(Sehir sehir, Date tarih) {
        if(UcusSayisi(sehir,tarih)==0){
            System.out.println("Bu kriterlere ait uçuş bulunamadı.");
            return;
        }
        System.out.println("\n-----"+sehir.toString()+" Adlı Şehirde "+tarih.toString()+" Tarihindeki Uçuşlar------");
        for (Ucus ucus:getUcuslar()){
            if (ucus.getKalkisSehir().equals(sehir)&&ucus.getTarih().equals(tarih)){
                System.out.println(ucus.toString());
            }
        }
        System.out.println("-----------------------------------------------------------\n");
    }

    @Override
    public int UcusSayisi(Sehir sehir, Date tarih) {
        int sayac=0;
        for (Ucus ucus:getUcuslar()){
            if (ucus.getKalkisSehir().equals(sehir)&&ucus.getTarih().equals(tarih)){
                sayac++;
            }
        }
        return sayac;
    }
    @Override
    public void SehirYazdir() {
        int i=1;
        for (Sehir sehir:getSehirler()){
            System.out.println(i+"."+sehir.toString());
            i++;
        }
    }

    @Override
    public int SehirGirdisiAl() {
        int sehir;
        while(true){
            sehir=sc.nextInt()-1;
            if (sehir<0||sehir>=getSehirler().size()){
                System.out.println("Hatali seçim, böyle bir şehir yok.");
            }
            else{
                break;
            }
        }
        sc.nextLine();
        return sehir;
    }

    @Override
    public Date TarihGirdisiAl() {
        String tarih;
        Date dTarih;
        while(true){
            try {
                System.out.println("Tarih Giriniz(gg/aa/yyyy):");
                tarih=sc.nextLine();
                dTarih = new SimpleDateFormat("dd/MM/yyyy").parse(tarih);
                break;
            }
            catch (ParseException e) {
                System.out.println("Hatalı tarih bilgisi girildi.");
            }
        }
        return dTarih;
    }

    @Override
    public double rastgeleFiyat() {
        return new Random().nextInt(MAXFIYAT-MINFIYAT)+MINFIYAT;
    }


    public ArrayList<Ucus> getUcuslar() {
        return Ucuslar;
    }

    public void setUcuslar(ArrayList<Ucus> ucuslar) {
        Ucuslar = ucuslar;
    }

    public ArrayList<Musteri> getMusteriler() {
        return Musteriler;
    }

    public void setMusteriler(ArrayList<Musteri> musteriler) {
        Musteriler = musteriler;
    }

    public ArrayList<Sehir> getSehirler() {
        return Sehirler;
    }

    public void setSehirler(ArrayList<Sehir> sehirler) {
        Sehirler = sehirler;
    }

}
