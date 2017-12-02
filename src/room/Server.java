package room;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class Server {
	
	private ArrayList<ClientWorker> clientList;
	private LinkedBlockingQueue<Object> datas;
	private ServerSocket serverSocket;
	private String IPAddress;
	
	public Server(int port) throws IOException {
		clientList = new ArrayList<>();
		datas = new LinkedBlockingQueue<>();
		serverSocket = new ServerSocket(port);
		IPAddress = InetAddress.getLocalHost().getHostAddress();
		acceptClient();
		recieveAndSend();
	}
	
	public String getIPAddress() {
		return IPAddress;
	}
	
	private void sendAll(Object message) {
		for (ClientWorker client : clientList) {
			client.send(message);
		}
	}
	
	private void acceptClient() {
		Thread t = new Thread() {
			@Override
            public void run(){
                while(true){
                    try{
                        Socket socket = serverSocket.accept();
                        clientList.add(new ClientWorker(socket));
                    } catch(IOException e) {
                    	e.printStackTrace();
                    }
                }
            }
        };
        t.setDaemon(true);
        t.start();
	}
	
	private void recieveAndSend() {
		Thread t = new Thread() {
        	@Override
        	public void run() {
        		while (true) {
        			try {
						Object message = datas.take();
						sendAll(message);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
        		}
        	}
        };
        t.setDaemon(true);
        t.start();
	}
	
	private class ClientWorker {
		
		private ObjectInputStream in;
		private ObjectOutputStream out;
		
		public ClientWorker(Socket socket) throws IOException {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			recieve();
		}
		
		private void recieve() {
			Thread t = new Thread() {
				@Override
				public void run() {
					while (true) {
						try {
							Object obj = in.readObject();
							datas.put(obj);
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			};
			t.setDaemon(true);
			t.start();
		}

		public void send(Object obj) {
			try {
				out.writeObject(obj);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
