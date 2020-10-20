import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class Server {
	private static ArrayList<ClientHandler> clients=new ArrayList<>();
	private static ExecutorService pool=Executors.newFixedThreadPool(100);
	public static void main(String[] args) throws IOException {
        
		ServerSocket server= new ServerSocket(8888);
		while(true) {
		System.out.println("[SERVER] Waiting for Client");
		Socket client=server.accept();
		System.out.println("[SERVER] Connected to Client");
		ClientHandler clientThread=new ClientHandler(client);
		clients.add(clientThread);
		pool.execute(clientThread);
		}

	}

}
