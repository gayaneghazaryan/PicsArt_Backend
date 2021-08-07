import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class server {

    private static String globalMessage = "Hello";

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket()) {

            HttpServer httpServer = HttpServer.create(new InetSocketAddress(server.getInetAddress(), 8080), 5);

            httpServer.createContext("/hello", (HttpExchange exchange) -> {
                        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8));
                            globalMessage = reader.readLine();
                            System.out.println(globalMessage);
                            OutputStreamWriter writer =new OutputStreamWriter(exchange.getResponseBody(), StandardCharsets.UTF_8);
                            String responseBody="Thank you for the message";
                            exchange.sendResponseHeaders(201, responseBody.length());
                            writer.write(responseBody.toCharArray());
                            writer.close();
                        } else if (exchange.getRequestMethod().equalsIgnoreCase("GET")) {
                            OutputStreamWriter writer = new OutputStreamWriter(exchange.getResponseBody(), StandardCharsets.UTF_8);
                            exchange.sendResponseHeaders(200, globalMessage.length());
                            writer.write(globalMessage.toCharArray());

                            writer.close();
                        }
                    }
            );
            ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
            httpServer.setExecutor(threadPoolExecutor);
            httpServer.start();
        } catch (
                IOException ex) {
            ex.printStackTrace();
        }
    }
}
