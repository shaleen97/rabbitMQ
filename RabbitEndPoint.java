import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public abstract class RabbitEndPoint{

    protected Channel channel;
    protected Connection connection;
    protected String endPointName;

    public RabbitEndPoint(String endpointName) throws IOException{
        this.endPointName = endpointName;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        connection = factory.newConnection();
        channel = connection.createChannel();
        //Create a queue for the channel
        channel.queueDeclare(endpointName, false, false, false, null);
    }

    public void close() throws IOException{
        this.channel.close();
        this.connection.close();
    }
}
