package HilosSockets;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            String serverMessage;

            // Loop principal del cliente
            while ((serverMessage = in.readLine()) != null) {
                System.out.println("Servidor: " + serverMessage);

                // Si el servidor solicita una elección, esperar la entrada del usuario
                if (serverMessage.equals("Seleccione una opción: ")) {
                    System.out.print("Seleccione una opción: ");
                    String userChoice = userInput.readLine();
                    System.out.println("Enviando opción al servidor: " + userChoice);
                    out.println(userChoice);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
