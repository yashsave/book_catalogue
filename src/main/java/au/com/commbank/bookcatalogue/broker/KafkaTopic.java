package au.com.commbank.bookcatalogue.broker;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

@Component
public class KafkaTopic {

	public void append(String event) {
		String BootStrapServer = "NS441F2Z2.acustaff.acu.edu.au:9092";
		//create producer properties
		Properties prop = new Properties();
		
		prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BootStrapServer);
		prop.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		prop.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		
		//create producer 
		@SuppressWarnings("resource")
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(prop);
		
		ProducerRecord<String, String> record = new ProducerRecord<String, String>("BookEventTopic", event);
		producer.send(record);
	}
}
