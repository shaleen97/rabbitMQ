import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.lang.SerializationUtils;

public class ServerProgram extends RabbitEndPoint{

    public ServerProgram(String endPointName) throws IOException{
        super(endPointName);
    }

    public void sendMessage(Serializable object) throws IOException {
        channel.basicPublish("",endPointName, null, SerializationUtils.serialize(object));
    }
}