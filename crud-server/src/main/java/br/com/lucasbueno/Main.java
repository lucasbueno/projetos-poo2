package br.com.lucasbueno;

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
import java.util.List;

import br.com.lucasbueno.dao.Conn;
import br.com.lucasbueno.dao.UserDAO;
import br.com.lucasbueno.entities.User;
import br.com.lucasbueno.exceptions.CommException;
import br.com.lucasbueno.exceptions.NetDeviceException;
import br.com.lucasbueno.exceptions.PortException;

public class Main {

	public static void main(String[] args) {

		ServerSocket server = null;
		try {
			Conn.getEntityManager().close();
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
			Conn.closeConn();
		}
	}

	private static void listen(ServerSocket server) throws CommException {
		try {
			// método para falar que o servidor deve aceitar conexões
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

		String recebido[] = msg.split(";");
		if (recebido[0].contentEquals("user")) {
			if (recebido[1].contentEquals("get"))
				getUser(out, recebido);
			else if (recebido[1].contentEquals("getAll"))
				getAllUser(out);
			else if (recebido[1].contentEquals("add"))
				addUser(out, recebido);
			else if (recebido[1].contentEquals("delete"))
				deleteUser(out, recebido);
			else if (recebido[1].contentEquals("update"))
				updateUser(out, recebido);
		}
		out.flush();
		out.close();
		in.close();
	}

	private static void updateUser(ObjectOutputStream out, String[] recebido) throws IOException {
		User user = new User(recebido[2], Integer.valueOf(recebido[3]));
		new UserDAO().update(user);
	}

	private static void deleteUser(ObjectOutputStream out, String[] recebido) throws IOException {
		User user = new User(recebido[2], Integer.valueOf(recebido[3]));
		new UserDAO().delete(user);
	}

	private static void addUser(ObjectOutputStream out, String[] recebido) throws IOException {
		User user = new User(recebido[2], Integer.valueOf(recebido[3]));
		new UserDAO().add(user);
	}

	private static void getAllUser(ObjectOutputStream out) throws IOException {
		String msg = "";
		List<User> users = new UserDAO().getAll();
		if (users == null)
			out.writeUTF("404");
		else
			for (User user : users)
				msg = msg.concat(user.getName() + ";" + user.getAge() + ";" + user.getRegisterDate() + ";");
		out.writeUTF(msg);
	}

	private static void getUser(ObjectOutputStream out, String[] recebido) throws IOException {
		User user = new UserDAO().get(recebido[2]);
		if (user == null) {
			out.writeUTF("404");
		} else {
			out.writeUTF(user.getName() + ";" + user.getAge() + ";" + user.getRegisterDate());
		}
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
			throw new NetDeviceException();
		} catch (UnknownHostException e) {
			throw new NetDeviceException();
		}
	}
}
