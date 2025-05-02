# GCP Instance Creator (Spring Boot + Java)

This project is a Spring Boot application that allows you to create Google Cloud VM instances via REST API calls.

## 🔧 Features

- Create VM instances using Google Cloud's Compute Engine API
- Uses service account credentials (JSON) for authentication
- Supports custom project ID, zone, instance name, and machine configuration

## 🛠 Technologies Used

- Java 17
- Spring Boot
- Google Cloud SDK (Compute API)
- REST API + Postman

## 🚀 How to Use

1. **Set up Google Cloud:**
   - Enable Compute Engine API
   - Create a service account and download the JSON key

2. **Configure the application:**
   - Update constants in `GcpInstanceCreator.java` with your GCP settings

3. **Run the app:**

```bash
./mvnw spring-boot:run

