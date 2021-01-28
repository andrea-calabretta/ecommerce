# Distributed Systems & Big Data 2020/2021 - Progetto 3B - Calabretta-Mauro 

## Relazione finale

### 1. Introduzione
Nella realizzazione di un sistema “e-commerce” distribuito, ci siamo dedicati allo sviluppo del micro-servizio relativo alla gestione dei pagamenti (Progetto 3) nella sua variante B. 
Come da specifiche di progetto, le tecnologie utilizzate sono : 
- **Database non relazionale -MongoDB-** 
- **Java Spring MVC**
- **Spring Data MongoDB**

Tramite la piattaforma PaaS Docker, sono stati generati 4 container rispettivamente per la gestione di mongodb, del micro-servizio payment_manager, del broker kafka e di zookeeper.

Per avviare il progetto è possibile seguire questo breve video: "link".

### 2. Diagramma delle classi

![alt text](https://github.com/andrea-calabretta/ecommerce/tree/main/img/Class_Diagram.jpg?raw=true)


### 3. Controller - Entrypoint

Come da specifiche di progetto, è stata realizzata una classe PaymentController con l'annotation @Controller che espone i due entrypoint:

- POST /payment/ipn
- GET /payment/transactions

Il primo è utilizzato per simulare l'invio di un pagamento, ovvero una API POST contenente i parametri userId, orderId, amountPaid e timestamp relativi all'ordine.
Tramite la funzione "add" implementata all'interno del controller e i metodi get e set della classe Payment, tutti i valori saranno inseriti in un json tramite .toJson(updateRequest). 
updateRequest è un parametro di tipo PaymentUpdateRequest ovvero una classe che estende Payment.   

``` JSON
 {
     "orderId": "0015",
     "userId": 2,
     "amountPaid": 2000,
     "timestamp": 1611858477
 }
```

L'entrypoint /transactions?fromTimestamp={$timestamp}&endTimestamp={$timestamp} restituisce gli elementi con userId -passato come header della request- uguale a 0, e soltanto all'interno di un dato intervallo di tempo i cui estremi sono fromTimestamp ed endTimestamp.

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


### 4. Controller - Error handling

All'interno del controller è stato inoltre implementato il meccanismo per la gestione degli errori.
Come da specifiche, al fallimento della richiesta HTTP, il micro-servizio deve pubblicare il messaggio all'interno del topic "logging" di Kafka; questo è stato realizzato

### 5. Kafka e Ping Ack

La strategia di Health-Check utilizzata è Ping Ack.
