package room;

import java.io.*;
import java.net.*;

public class Client {
	
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private DataEvent event;
	
	public Client(String address, int port) {
		try {
			socket = new Socket(address, port);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			recieve();
		} catch (UnknownHostException e) {
			System.out.println("Unknown host: " + address);
		} catch (ConnectException e) {
			System.out.println("Connection time out");
		} catch (IOException e) {
			System.out.println("I/O error");
		}
	}
	
	public void send(Object obj) {
		try {
			out.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setOnDataRecieved(DataEvent e) {
		event = e;
	}
	
	private void recieve() {
		Thread t = new Thread() {
        	@Override
        	public void run() {
        		while (true) {
        			try {
						Object data = in.readObject();
						event.handle(data);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
        		}
        	}
        };
        t.setDaemon(true);
        t.start();
	}
	
}
