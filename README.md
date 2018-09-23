# NurseryQueue

## Co robi aplikacja

- Loguje się automatycznie do Portalu Gdańskiego Zespołu Żłobków i odnotowuje w bazie danych miejsce w kolejkach do żłobków, do których zapisane jest dziecko.
- W przypadku zmiany miejsca w którejś z kolejek wysyła maila z informacją (do zrobienia).
- Automatycznie, co miesiąc, potwierdza chęć dalszego oczekiwania w kolejkach (do zrobienia).

## Instalacja

### Utworzenie bazy danych

Tworzymy bazę danych o nazwie nureseryqueue, usera nq oraz role pokazane poniżej:

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

Następnie odpalamy migrację FlyWay:

```
-Dflyway.configFiles=config/flyway/docker.conf flyway:migrate
```

### Wysyłka maili

Ustawiamy serwer SMTP w standalone.xml:

```xml
<mail-session name="default" jndi-name="java:jboss/mail/Default" debug="true">
    <smtp-server outbound-socket-binding-ref="mail-smtp" username="user" password="pass" ssl="true" tls="true"/>
</mail-session>
```

oraz:

```xml
<outbound-socket-binding name="mail-smtp">
    <remote-destination host="smtp.example_host.com" port="465"/>
</outbound-socket-binding>
```




