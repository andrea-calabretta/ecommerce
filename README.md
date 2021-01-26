# Distributed Systems & Big Data 2020/2021 - Progetto 3B - Calabretta-Mauro 

## Relazione finale

### 1. Introduzione
Nella realizzazione di un sistema “e-commerce” distribuito, ci siamo dedicati allo sviluppo del micro-servizio relativo alla gestione dei pagamenti (Progetto 3) nella sua variante B. 
Come da specifiche di progetto, le tecnologie utilizzate sono : 
- **un database non relazionale -MongoDB-** 
- **Java Spring MVC**
- **Spring Data MongoDB**

Tramite la piattaforma PaaS Docker, sono stati generati 4 container rispettivamente per la gestione di mongodb, del micro-servizio payment_manager, del broker kafka e di zookeeper.

Per avviare il progetto è possibile seguire questo breve video: "link".

### 2. Diagramma delle classi

La strategia di Health-Check utilizzata è Ping Ack.



