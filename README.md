Spring Banka Uygulaması:Java Spring Web uygulaması geliştirdik. Geliştirdiğimiz uygulamada
üç farklı rol bulunmaktadır: bankacı, kullanıcı ve admin. Admin yetkisine sahip kullanıcılar,
uygulamamız üzerinde bankacı ekleme, düzenleme ve silme işlemleri gerçekleştirebilmektedirler.
Ayrıca, kredi başvurularını onaylama ve reddetme yetkisine de sahiptirler. Bankacı yetkisine sahip
kullanıcılar, uygulamaya yeni kullanıcı ekleyebilme yetkisine sahiptirler. Ayrıca, mevcut kullanıcıları
düzenleme ve silme yetkilerinin yanı sıra, uygulamada bulunan kullanıcılara para yükleme ve para
çekme işlemlerini gerçekleştirebilmektedirler. Düz kullanıcılarsa kredi başvurusu yapabilir, yaptıkları
kredi başvurularının durumlarına bakabilir ve para yükleme ile para çekme işlemleri
gerçekleştirebilirler.

Projede Bulunan Veritabanı Tablolarımız:
Veritabanında 2 adet tablomuz bulunmaktadır; bunlar "kullanici" ve "kredi_basvurusu" tablolarıdır.
Kullanıcı tablomuz, kullanıcı ID'sini, adını, rolünü, kullanıcı adını ve hashlenmiş kullanıcı şifresini
içermektedir. Kredi başvuru tablomuz ise bir ID'yi, başvuru yapan kullanıcının ID'sini, başvuru miktarını
ve durumunu barındırmaktadır. Tablolar, Spring uygulamamız tarafından, uygulamamızda bulunan
model sınıflarımıza göre otomatik olarak oluşturulmaktadır. Bu uygulamayı geliştirirken "code first"
prensibine uymaya çalıştık. Aşağıda, veritabanımızdan aldığımız iki adet ekran görüntüsü mevcuttur.
![image](https://github.com/user-attachments/assets/b01c6426-44e7-41ce-906b-4e480eccdffe)
![image](https://github.com/user-attachments/assets/075e02a8-f16f-475d-abce-d5d6a49f96de)

Projede Bulunan Sayfalar ve İşlevleri:

![image](https://github.com/user-attachments/assets/8e7ae414-5ffa-429f-a96d-ee210b32337f)
Login sayfası: Tüm kullanıcıların uygulamaya giriş yaptığı sayfadır. Kullanıcının
rolüne göre, admin, bankacı veya kullanıcı sayfasına yönlendirmektedir.

![image](https://github.com/user-attachments/assets/a3e7f385-cfb5-42d2-aef7-538bfcb65291)
Register Saydası: Kullanıcıların uygulamamıza kaydolabileceği sayfadır.

![image](https://github.com/user-attachments/assets/e5ac964d-0202-473d-8249-065dd0612652)
Admin Ana Sayfa: Adminin diğer sayfalara gidebileceği ana dizin.

![image](https://github.com/user-attachments/assets/180a8ae9-9447-4bb6-a758-6fc5cc2ba986)
Bankacı Ekleme Sayfası: Adminin bankacı ekleyebileceği sayfadır.

![image](https://github.com/user-attachments/assets/e271643b-de5e-4921-b20b-dd9a2fa1e60c)
Admin Bankacı Listesi: Adminin mevcut bankacıları görebildiği ve silebildiği
sayfadır. İsterse düzenleme sayfasına da gidebilir.

![image](https://github.com/user-attachments/assets/9207d1f2-b97f-40ce-87cb-58c5a7203d26)
Admin Bankacı Düzenleme: Adminin seçtiği bankacıyı düzenleyebileceği
sayfadır.

![image](https://github.com/user-attachments/assets/29df31aa-650b-4450-9a82-634bfa99083b)
Admin Kredi Başvurusu: Mevcut başvurularda durumu "cevap bekleyen"
başvuruların onaylandığı ya da reddedildiği sayfadır.

![image](https://github.com/user-attachments/assets/078e876c-67a6-47b5-bca1-ea6c8bed5cf1)
Bankacı Ana Sayfa: Bankacının mevcut sayfalara gidebildiği ana dizindir.

![image](https://github.com/user-attachments/assets/d37d5170-2b3e-4f61-afb6-fba11215abd0)
Kullanıcı Listesi Sayfası: Bankacının mevcut kullanıcıları listelediği, isteğine göre
silme, düzenleme veya para çekme/yükleme işlemlerini gerçekleştirebildiği
sayfadır.

![image](https://github.com/user-attachments/assets/a6115f25-6913-4a9a-b395-9cd3102beaf2)
Bankacı Kullanıcı Düzenleme: Bankacının listeden "düzenle" dediği kullanıcıyı
düzenleyebileceği sayfadır.

![image](https://github.com/user-attachments/assets/cec61817-ce60-4ce4-b4e2-0906c706c8c4)
Bankacı Kullanıcı Para Yükleme: Bankacının seçtiği kullanıcıya para
yükleyebileceği sayfadır.

![image](https://github.com/user-attachments/assets/494d2570-b1f6-46fe-a312-567f86a62cc2)
Bankacı Kullanıcı Para Çekme: Bankacının seçtiği kullanıcıdan para çekebileceği
sayfadır.

![image](https://github.com/user-attachments/assets/eb15e5d0-6b58-48f5-8404-f9be1be0a019)
Kullanıcı Ana sayfa: Kullanıcının para yükleyebileceği, çekebileceği veya mevcut
bakiyesini görebileceği ana sayfadır. Kredi ile ilgili sayfalara yönlendiren butonlar
da burada bulunmaktadır.

![image](https://github.com/user-attachments/assets/34c1a689-4d46-4b3d-a240-c065a39ef973)
Kullanıcı Kredi Başvuru: Kullanıcının kredi başvurusu yapabileceği sayfadır.

![image](https://github.com/user-attachments/assets/fbd1cdbe-e2dc-4fb2-9860-617e672bfe82)
Kullanıcı Kredi Başvuruları: Kullanıcının başvurduğu kredilerin durumlarını
görebileceği sayfadır.

















