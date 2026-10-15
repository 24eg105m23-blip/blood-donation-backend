@echo off
setlocal
set "MAVEN_OPTS=-Dmaven.repo.local=%TEMP%\blood-donation-management-maven-repository"
set "DB_USERNAME=root"
set "DB_PASSWORD=root"
set "DB_URL=jdbc:mysql://localhost:3306/blood_donation_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
set "CORS_ORIGIN=http://localhost:5173"
set "SAMPLE_DATA_ENABLED=true"
cd /d "%~dp0"
call mvn spring-boot:run
