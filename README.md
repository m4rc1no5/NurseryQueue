# NurseryQueue

[![Build Status](https://travis-ci.org/m4rc1no5/NurseryQueue.svg?branch=master)](https://travis-ci.org/m4rc1no5/NurseryQueue)
[![Build Status](https://sonarcloud.io/api/project_badges/measure?project=m4rc1no5_NurseryQueue&metric=alert_status)](https://sonarcloud.io/dashboard?id=m4rc1no5_NurseryQueue)

## Co robi aplikacja

- Loguje się do Portalu Gdańskiego Zespołu Żłobków i odnotowuje w bazie danych miejsce w kolejkach do żłobków, do których zapisane jest dziecko.
- W przypadku zmiany miejsca w którejś z kolejek wysyła maila z odpowiednią informacją.
- Automatycznie w odpowiednim momencie potwierdza chęć dalszego oczekiwania w kolejkach.

## Zastosowane technologie

- Java 11
- Java EE8
- WildFly 15.0.1.Final

## Instalacja

### Pliki konfiguracyjne

- Tworzymy i modyfikujemy odpowiednio standalone.xml na podstawie standalone.xml.default
- Tworzymy i modyfikujemy odpowiednio docker.conf na podstawie docker.conf.default

### Bazy danych

Tworzymy bazę danych o nazwie nureseryqueue, usera oraz grupy pokazane poniżej:

```sql
CREATE ROLE nq LOGIN
  ENCRYPTED PASSWORD 'example_password'
  SUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;
  
CREATE ROLE nq_admins
  NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;
  
CREATE ROLE nq_users
  NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;

CREATE ROLE nq_systems
  NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;
  
CREATE DATABASE nurseryqueue
  WITH OWNER = nq;
```

Odpalamy migrację bazy danych za pomocą FlyWay (z poziomu NurseryQueueApi):

```
mvn -Dflyway.configFiles=config/flyway/docker.conf flyway:migrate
```

### Wysyłka maili

Ustawiamy serwer SMTP w standalone.xml:

```xml
<mail-session name="default" jndi-name="java:jboss/mail/Default">
    <smtp-server outbound-socket-binding-ref="mail-smtp" username="user" password="pass" ssl="true" tls="true"/>
</mail-session>
```

oraz:

```xml
<outbound-socket-binding name="mail-smtp">
    <remote-destination host="smtp.example_host.com" port="465"/>
</outbound-socket-binding>
```

### Budowanie projektu 

```
mvn clean install
```
