import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static String res = "";

    public static class SimpleMqttCallBack implements MqttCallback {

        public void connectionLost(Throwable throwable) {
            System.out.println("Connection to MQTT broker lost!");
        }

        public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
            res = new String(mqttMessage.getPayload());
            System.out.println("Message received:\n\t"+ res );
        }

        public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            // not used in this example
        }
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String s = in.nextLine();


        String topic        = "IU9";
        String content      = s;
        int qos             = 2;
        String broker       = "tcp://mqtt.eclipseprojects.io:1883";
        String clientId     = "Vsevolond";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: "+broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            System.out.println("Publishing message: "+content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            sampleClient.publish(topic, message);
            System.out.println("Message published");

            MqttClient client=new MqttClient(broker, MqttClient.generateClientId());
            SimpleMqttCallBack call = new SimpleMqttCallBack();
            client.setCallback( call);
            client.connect();
            client.subscribe(topic);
            //client.getTopic(topic);
            call.messageArrived(topic, message);
            ArrayList<Integer> coord1 = new ArrayList<Integer>(3);
            ArrayList<Integer> coord2 = new ArrayList<Integer>(3);
            boolean full = false;
            int j = 0, i = 0;
            while (i < res.length()) {
                if (res.charAt(i) != '(' && res.charAt(i) != ' ' && res.charAt(i) != ')') {
                    StringBuilder num = new StringBuilder(String.valueOf(res.charAt(i)));
                    i++;
                    while (res.charAt(i) >= '0' && res.charAt(i) <= '9' && i < res.length()) {
                        num.append(res.charAt(i));
                        i++;
                    }
                    String num_tostring = num.toString();
                    if (!full) {
                        coord1.add(Integer.parseInt(num_tostring));
                    } else {
                        coord2.add(Integer.parseInt(num_tostring));
                    }
                }
                if (res.charAt(i) == ')') {
                    full = true;
                }
                i++;
            }
            System.out.println(coord1);
            System.out.println(coord2);
            int answer = (coord1.get(0) * coord2.get(0) +
                    coord1.get(1) * coord2.get(1) + coord1.get(2) * coord2.get(2)) == 0 ? 1 : 0;

            MqttMessage messageDet = new MqttMessage(("" + answer).getBytes());
            message.setQos(qos);
            System.out.println("Pablishing message: " + answer);
            System.out.println("Message published");
            sampleClient.publish(topic, messageDet);


            MqttClient client2=new MqttClient(broker, MqttClient.generateClientId());
            SimpleMqttCallBack call2 = new SimpleMqttCallBack();
            client2.setCallback( call2);
            client2.connect();
            client2.subscribe(topic);
            call2.messageArrived(topic, messageDet);

            System.out.println("answer: " + res);

            client.disconnect();
            client2.disconnect();
            sampleClient.disconnect();
            System.out.println("Disconnected");
            System.exit(0);
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
