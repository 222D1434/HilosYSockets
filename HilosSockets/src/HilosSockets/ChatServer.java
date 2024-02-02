package HilosSockets;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Servidor esperando conexiones...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    // Mostrar opciones al cliente
                    showMenu(out);

                    // Esperar y manejar la opción del cliente
                    while (true) {
                        String clientChoice = in.readLine();
                        System.out.println("Cliente eligió: '" + clientChoice + "'");

                        if (clientChoice.equals("0")) {
                            System.out.println("Cliente eligió salir. Desconectando...");
                            break;
                        }

                        String response = getServerResponse(clientChoice);
                        System.out.println("Enviando respuesta al cliente: " + response);
                        out.println(response);

                        // Mostrar opciones al cliente nuevamente
                        showMenu(out);
                    }

                    System.out.println("Cliente desconectado.");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void showMenu(PrintWriter out) {
        out.println("Bienvenido al servidor de chat. Seleccione una opción:");
        out.println("1. Saludo");
        out.println("2. Pregunta");
        out.println("3. Despedida");
        out.println("4. Agradecimiento");
        out.println("5. Otra opción");
        out.println("0. Salir");
        out.println("Seleccione una opción: "); // Nueva línea para indicar al cliente que espere la entrada
    }

    private static String getServerResponse(String clientChoice) {
        switch (clientChoice) {
            case "1":
                return "Hola, ¿cómo estás?";
            case "2":
                return "¿En qué puedo ayudarte?";
            case "3":
                return "Hasta luego, ¡ten un buen día!";
            case "4":
                return "¡De nada! ¿Hay algo más en lo que pueda ayudarte?";
            default:
                return "No entendí tu opción. Por favor, elige una opción válida.";
        }
    }
}
