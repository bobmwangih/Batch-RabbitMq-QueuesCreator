package com.bob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@SpringBootApplication
public class QueueCreaterApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(QueueCreaterApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Read queue names from the file
        List<String> queueNames = readQueueNamesFromFile("D:/queues.txt");

        // Create RabbitMQ connection and channel
        try (Connection connection = createConnection();
             Channel channel = connection.createChannel()) {

            // Create queues
            for (String queueName : queueNames) {
                channel.queueDeclare(queueName, true, false, false, null);
                System.out.println("Queue created: " + queueName);
            }
        }
    }

//    private List<String> readQueueNamesFromFile(String fileName) throws Exception {
//        ClassPathResource resource = new ClassPathResource(fileName);
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
//            return reader.lines()
//                    .map(String::trim)
//                    .filter(line -> !line.isEmpty())
//                    .collect(Collectors.toList());
//        }
//    }
    private List<String> readQueueNamesFromFile(String filePath) throws Exception {
        File file = new File(filePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines()
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .collect(Collectors.toList());
        }
    }



    private Connection createConnection() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        // Set RabbitMQ server configurations (hostname, port, etc.)
        factory.setHost("localhost");
        //factory.setPort();
        factory.setUsername("guest");
        factory.setPassword("guest");
        return factory.newConnection();
    }
}
