package calabretta_mauro.payment_MongoDB.kafka;

import com.google.gson.Gson;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//definiamo il listener ovvero il meccanismo secondo cui il consumer
//farà da sottoscrittore per un determinato topic kafka e quindi
// verrà notificato per ogni nuovo msg sul topic

@Component
public class KafkaConsumerPayments {

    //devo leggere le info del prodotto di tale id per vedere se esiste e effettuare il pagamento

    //l'istanza sarà un sottoscrittore di quel topic kafka
    @KafkaListener(topics = "${kafkaTopic}", groupId = "${kafkaGroup}")
    public void listenerPayments(String message){

        //è l'oggetto paymentsUpdateRequest serializzato in gson
        //quindi appena arriva il msg devo aggiornare la lista dei pagamenti
        if (message != null){
            PaymentsUpdateRequest updateRequest = new Gson().fromJson(message, PaymentsUpdateRequest.class);

        }
    }
}
