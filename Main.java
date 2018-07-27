import java.util.HashMap;

public class Main {
    public Main() throws Exception{

        Client consumer = new Client("queue");
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        ServerProgram serverProgram = new ServerProgram("queue");

        for (int i = 0; i < 150; i++) {
            HashMap message = new HashMap();
            message.put("message_key", i);
            serverProgram.sendMessage(message);
            System.out.println("Message "+ i +" away");
        }
    }


    public static void main(String[] args) throws Exception{
        new Main();
    }
}