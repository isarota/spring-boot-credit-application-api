# spring-boot-credit-application-api
Credit Application API developed with Spring Boot


## Bitirme Projesi: `Credit Application` Application

### Proje Konusu: 
Bir kredi başvuru sistemi için, kredi başvuru isteklerini alıp ilgili kriterlere göre müşteriye kredi
sonucunu dönen servisin içinde yer alacak restful bir uygulamanın Spring Boot framework kullanılarak
yazılması ve isteğe bağlı olarak önyüzünün yazılması.

Gereksinimler dokümanın en alt kısmında bulunmaktadır.

### Projenin Çalışabilmesi için Gereken Komutlar

`infra-up.sh` scripti çalıştırılarak Postgresql container'ının çalıştırılması gerekiyor.
```shell
sh infra-up.sh
```

Projeyle işimiz bittiğinde `infra-down.sh` scripti çalıştırılarak container'lerimizi silebiliriz.
```shell
sh infra-down.sh
```

### Proje Geliştirilirken Uygulanan Yaklaşımlar
Spring Boot projesini geliştirirken Hexagonal Architecture kullandım.
Bu mimariyi uygularken 
eğitmenimiz Mustafa Güneş'in [geliştirdiği projeyi](https://github.com/Payten-Java-Spring-Bootcamp/bootcamp-examples) ve 
Baeldung'un [Hexagonal Architecture yazısıyla](https://www.baeldung.com/hexagonal-architecture-ddd-spring) beraber paylaştığı 
[GitHub repository'sini](https://github.com/eugenp/tutorials/tree/master/ddd/src/main/java/com/baeldung/dddhexagonalspring) inceledim.
Klasik Layered Architecture'a kıyasla katmanları daha çok domain'deki mantık yapısı üzerinden konumlandıran
bu yaklaşımın daha ölçeklenebilir olduğunu düşündüm.
Hexagonal Architecture'a göre yapılan her iş kendi katmanında yapılır.
Diğer katmanları etkilemeden kendi katmanımıza odaklanabiliriz.

Veritabanı olarak PostgreSQL kullandım. Çünkü PostgreSQL hem güçlü mimarisi hem de donanımlı destekçileriyle
gün geçtikçe Java dünyasında yerini sağlamlaştıran veritabanlarından bir tanesi.

### Gereksinimler:

1. Backend:
    - Kullanıcıdan kimlik numarası, ad-soyad, aylık gelir ve telefon bilgileri alınarak, kimlik
    numarasıyla daha önceden yazıldığı varsayılan kredi skoru servisine gidilir ve 
    ilgili kişiye ait kredi skoru alınarak aşağıdaki kurallara göre kullanıcıya 
    kredi sonucu gösterilir.
    (Onay veya Red olarak iki seçenek olabilir.)
2. Frontend(opsiyonel):
    - Kimlik numarası, ad-soyad, aylık gelir ve telefon bilgileri form ile alınır ve 
    kullanıcıya kredi sonucu ve kredi limiti gösterilir.
    (JavaScript, HTML, CSS en basit şekilde yeterlidir, 
    isteğe bağlı olarak front-end framework kullanılabilir. Arayüz tasarımı ve deneyimi size bırakılmıştır.)

### Kurallar:
- Sisteme yeni kullanıcılar tanımlanabilir, mevcut müşteriler güncellenebilir veya silinebilir.
- Kredi skoru 500’ün altında ise kullanıcı reddedilir. (Kredi sonucu: Red)
- Kredi skoru 500 puan ile 1000 puan arasında ise ve 
aylık geliri 5000 TL’nin altında ise kullanıcının kredi başvurusu onaylanır ve kullanıcıya 10.000 TL limit atanır. 
(Kredi Sonucu: Onay)
- Kredi skoru 500 puan ile 1000 puan arasında ise ve aylık geliri 5000 TL’nin üstünde ise
kullanıcının kredi başvurusu onaylanır ve kullanıcıya 20.000 TL limit atanır. 
(Kredi Sonucu: Onay)
- Kredi skoru 1000 puana eşit veya üzerinde ise kullanıcıya 
AYLIK GELİR BİLGİSİ * KREDİ LİMİT ÇARPANI kadar limit atanır. (Kredi Sonucu: Onay)
- Kredinin neticelenmesi sonucunda ilgili başvuru veritabanına kaydedilir. 
Daha sonrasında ise ilgili telefon numarasına bilgilendirme SMS’i gönderilir ve 
endpoint’ten onay durum bilgisi (red veya onay), limit bilgisi dönülür.
- Gerçekleştirilmiş bir kredi başvurusu sadece kimlik numarası ile sorgulanabilir.

**Notlar**: Kredi limit çarpanı varsayılan olarak 4’tür.

### Projeden Beklenenler:
- Backend projesinin belirtilen kurallara göre doğru çalışır olmasi.
- Kodun kalitesine (Clean Code), mimarisine dikkat edilmesi (layere, hexagonal veya 
tercih ettiğiniz başka bir mimari), yeni özellikler için geliştirmeye açık olması ve anlaşılabilir olması.
(SOLID principles)
- REST convention’larına uygun olması
- Exception durumlarının handle edilmesi
- Test yazılması (Hem unit, hem integration testler)
- Dokümantasyon(Swagger, OpenApi vb)
- İlişkisel veri tabanı kullanımı (Postgresql veya istediğiniz başka bir teknoloji kullanabilirsiniz)
- ORM kullanıcı (JPA/Hibernate veya istediğiniz başka bir teknoloji kullanabilirsiniz)
- Cache yapısının kurulması (Redis veya istediğiniz başka bir teknoloji kullanabilirsiniz)
- Readme.md dosyası oluşturulacaktır. (projeyi çalıştırmak için gerekli komutlarla birlikte, 
tercih ettiğiniz yöntemler, teknolojiler, mimari yaklaşım ve bunları tercih etme sebeplerinizi belirtiniz.)

### Bonus
- Dockerfile hazırlanması
- Frontend projesinin çalışır olması
- Endpoint’ lerin secure olması (JWT veya istediğiniz başka bir teknoloji kullanabilirsiniz.)
