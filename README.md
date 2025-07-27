# Kütüphane Yönetim Sistemi (Library Management System)

Bu proje, Spring Boot kullanılarak geliştirilmiş bir Kütüphane Yönetim Sistemi uygulamasıdır. Amaç, kitap, yazar, kategori ve yayınevi gibi temel kütüphane varlıklarının yönetimini sağlamak ve ödünç alma işlemlerini kolaylaştırmaktır.

## Özellikler
- Kitap, yazar, kategori ve yayınevi CRUD işlemleri
- Kitap ödünç alma ve iade işlemleri
- Katmanlı mimari (Controller, Service, Repository, DTO)
- ModelMapper ile DTO-Entity dönüşümleri
- Global Exception Handling
- PostgreSQL veritabanı entegrasyonu
- Temel validasyonlar

## Kurulum ve Çalıştırma
1. **Projeyi klonlayın:**
   ```bash
   git clone <repo-url>
   ```
2. **Veritabanı ayarlarını yapın:**
   `src/main/resources/application.properties` dosyasındaki PostgreSQL bağlantı bilgilerini kendi ortamınıza göre güncelleyin.
3. **Bağımlılıkları yükleyin ve projeyi başlatın:**
   Windows için:
   ```powershell
   .\mvnw.cmd clean install
   .\mvnw.cmd spring-boot:run
   ```
   veya Linux/Mac için:
   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```
4. **Uygulama** `http://localhost:8080` adresinde çalışacaktır.

## Katmanlar ve Yapı
- **Controller:** API endpoint’leri (ör. `/v1/authors`, `/v1/books`)
- **Service:** İş mantığı ve validasyonlar
- **Repository (DAO):** Veritabanı işlemleri (Spring Data JPA)
- **DTO:** Veri transfer nesneleri (request/response)
- **Entities:** JPA entity sınıfları
- **Core:** Ortak yardımcılar, hata yönetimi, result wrapper’lar

## Kullanılan Teknolojiler
- Java 17
- Spring Boot 3
- Spring Data JPA
- PostgreSQL
- Lombok
- ModelMapper

## Katkı Sağlama
Katkı sağlamak için fork’layıp pull request gönderebilirsiniz. Kod standartlarına ve proje yapısına uygun geliştirme yapmaya özen gösteriniz.

## Lisans
Bu proje eğitim amaçlıdır. Dilediğiniz gibi kullanabilirsiniz.
