package Mensageria.Produtor;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Classe responsavel por enviar itens à fila
 */
public class Produtor {

    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);

        String NOME_FILA = "filaOla";
        try(
                Connection connection = connectionFactory.newConnection();
                Channel channel = connection.createChannel()) {

            channel.queueDeclare(NOME_FILA, false, false, false, null);
            String mensagem = "Olá mundo e Gabriel!";
            channel.basicPublish("", NOME_FILA, null, mensagem.getBytes());
            System.out.println("Eu, Gabriel, enviei a mensagem: " + mensagem);
        }
    }
}
