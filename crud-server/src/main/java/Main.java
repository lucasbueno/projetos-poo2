import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class Main {

	public static void main(String[] args) {
		ServerSocket server = null;
		try {
			printServerInfo();
			server = openSocket();
			System.out.println("O servidor está aberto na porta " + server.getLocalPort());
			while (true) {
				listen(server);
			}
		} catch (PortException ex) {
			System.err.println("Nenhuma porta disponível no servidor.");
		} catch (NetDeviceException ex) {
			System.err.println("A placa de rede está com algum problema.");
		} catch (CommException ex) {
			System.err.println("Ocorreu algum problema em uma comunicação com um cliente.");
		} finally {
			if (server != null) {
				try {
					server.close();
				} catch (IOException e) {
				}
			}
		}
	}

	private static void listen(ServerSocket server) throws CommException {
		try {
			Socket client = server.accept();
			process(client);
			client.close();
		} catch (IOException e) {
			throw new CommException();
		}
	}

	private static void process(Socket client) throws IOException {
		System.out.println("Cliente conectado: " + client.getInetAddress().getHostAddress());
		
		ObjectInputStream in = new ObjectInputStream(client.getInputStream());
		String msg = in.readUTF();
		System.out.println("Cliente enviou: " + msg);

		ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
		out.writeUTF("tchau");
		out.flush();
		out.close();
		in.close();
	}	

	private static ServerSocket openSocket() throws PortException {
		int port = 1024;
		while (port <= 65535) {
			try {
				return new ServerSocket(port);
			} catch (IOException ex) {
				port++;
			}
		}
		throw new PortException();
	}

	private static void printServerInfo() throws NetDeviceException {
		try {
			System.out.println("-----------------------------------");
			System.out.println("Informações do servidor:");
			String hostname = InetAddress.getLocalHost().getHostName();
			System.out.println("Hostname: " + hostname);
			Enumeration<NetworkInterface> myNetInterfaces = NetworkInterface.getNetworkInterfaces();
			while (myNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = myNetInterfaces.nextElement();
				System.out.println("Interface: " + netInterface.getName());
				Enumeration<InetAddress> interfaceAddrs = netInterface.getInetAddresses();
				while (interfaceAddrs.hasMoreElements()) {
					InetAddress addr = interfaceAddrs.nextElement();
					System.out.println("  " + addr.getHostAddress());
				}
			}
			System.out.println("-----------------------------------");
		} catch (SocketException e1) {
			System.err.println("Não tenho nenhuma interface de rede :(");
			throw new NetDeviceException();
		} catch (UnknownHostException e) {
			System.err.println("Não tenho um hostname :(");
			throw new NetDeviceException();
		}
	}
}
