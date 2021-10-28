# Distributed Systems & Big Data 2020/2021 - Progetto 3B - Calabretta-Mauro
<br />

## Relazione finale       

### 1. Introduzione
Nella realizzazione di un sistema “e-commerce" distribuito, ci siamo dedicati allo sviluppo del micro-servizio relativo alla gestione dei pagamenti (Progetto 3) nella sua variante B con database Mongo. 
Le tecnologie utilizzate sono : 
- **Database non relazionale -MongoDB-** 
- **Java Spring MVC**
- **Spring Data MongoDB**

Tramite Docker, vengono generati 4 container rispettivamente per la gestione di mongodb, del micro-servizio payment-manager, del broker kafka e di zookeeper.

Per avviare il progetto è verificarne le principali funzionalità è consigliabile visionare questo breve video di 3 minuti:   https://youtu.be/dhuKJxQmh1w

### 2. Diagramma delle classi

Ecco il diagramma delle classi principali:

![ClassDiagram](img/Class_Diagram.jpg)


### 3. Controller - Entrypoints

Come da specifiche di progetto, è stata realizzata una classe PaymentController con l'annotation @Controller che espone i due entrypoint:

- POST /payment/ipn
- GET /payment/transactions

Il primo: "/payment/ipn" è utilizzato per simulare l'invio di un pagamento.
Il risultato dell'operazione è che viene salvato sul database Mongo e inoltre memorizzato nella coda Kafka sul topic orders, un oggetto payment di questo tipo:

``` JSON
 {
     "orderId": "0015",
     "userId": 2,
     "amountPaid": 2000,
     "timestamp": 1611858477
 }
```

Ecco il diagramma di sequenza che descrive il funzionamento dell'entrypoint /payment/ipn  :

![ipn](img/ipn_seq_diagram.jpg)

Invece, l'entrypoint /transactions?fromTimestamp=timestamp1&Timestamp=timestamp2 restituisce le transazioni effettuate all'interno di un dato intervallo di tempo i cui estremi sono fromTimestamp ed endTimestamp, solamente se l'header della richiesta ha "userId" = 0.

``` JSON
{
   {
     "orderId": "0013",
     "userId": 2,
     "amountPaid": 2500,
     "timestamp": 1611856883
   },
   {
     "orderId": "0014",
     "userId": 4,
     "amountPaid": 1000,
     "timestamp": 1611857573
    },
    {
     "orderId": "0015",
     "userId": 1,
     "amountPaid": 750,
     "timestamp": 1611858477
    }
}
```

Ecco il diagramma di sequenza relativo all'entrypoint /payment/transaction?fromTimestamp=timestamp1&Timestamp=timestamp2   :

![transactions](img/Transaction_seq_diagram.jpg)


### 4. Errori

E' stato implementato il meccanismo per la gestione degli errori facendo uso della notazione @ControllerAdvice che permette di gestire le eccezioni che si verificano non solo in uno specifico Controller ma in tutta l'applicazione.
Possiamo intenderlo come un intercettatore di eccezioni generate da qualunque metodo che abbia notazione:  @RequestMapping, @GetMapping, @PostMapping, @PutMapping e così via.

Anche la classe relativa alla gestione delle eccezioni (HttpExceptionHandler) fa utilizzo della coda Kafka per tenere traccia degli errori sull'apposito topic "logging"

Il formato del messaggio scritto su Kafka, presenta il seguente formato:

```
 key = http_errors
 value = {
     timestamp: UnixTimestamp,
     sourceIp: sourceIp,
     service: payments,
     request: path + method
     error: error
}
```


### 5. Ping Ack

La strategia di Health-Check utilizzata è Ping Ack ed è finalizzato a verificare che il database sia raggiungibile e che il microservizio sia in esecuzione.
Ecco il diagramma di sequenza che ne descrive il funzionamento:

![PingAck](img/PingAck_seq_diagram.jpg)


Studenti:
</br>
Alessandro Mauro
</br>
Andrea Calabretta

