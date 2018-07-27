import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.SerializationUtils;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

public class Client extends RabbitEndPoint implements Runnable, Consumer{

    public Client(String endPointName) throws IOException{
        super(endPointName);
    }

    public void run() {
        try {
            channel.basicConsume(endPointName, true,this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleConsumeOk(String clientTag) {
        System.out.println("Looks like we have a new client "+clientTag);
    }

    public void handleDelivery(String consumerTag, Envelope env,
                               BasicProperties props, byte[] body) throws IOException {
        Map mapper = (HashMap)SerializationUtils.deserialize(body);
        System.out.println("Client Just sent a new message: "+ mapper.get("message_key"));

    }


    public void handleCancel(String consumerTag) {}
    public void handleCancelOk(String consumerTag) {}
    public void handleRecoverOk(String consumerTag) {}
    public void handleShutdownSignal(String consumerTag, ShutdownSignalException arg1) {}
}