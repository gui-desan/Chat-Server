package application;

import java.io.IOException;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import room.Client;
import room.Message;
import room.Server;

public class Room extends VBox {
	
	private Server server;
	private Client client;
	
	private final int PORT = 8900;
	
	public Room() throws IOException {
		server = new Server(PORT);
		client = new Client(server.getIPAddress(), PORT);
		createUI(true);
	}
	
	public Room(String address) throws IOException {
		client = new Client(address, PORT);
		createUI(false);
	}
	
	private void createUI(boolean showIP) {
		setPadding(new Insets(10));
		setSpacing(8);
		if (showIP) {
			Text IPTxt = new Text("Your IP: " + server.getIPAddress());
			IPTxt.setFont(new Font("Arial", 16));
			getChildren().add(IPTxt);
		}
		TextArea chatBox = new TextArea();
		chatBox.setEditable(false);
		chatBox.setFocusTraversable(false);
		getChildren().add(chatBox);
		setVgrow(chatBox, Priority.ALWAYS);
		HBox sendBox = new HBox(5);
		getChildren().add(sendBox);
		TextField sendFld = new TextField();
		sendFld.setPromptText("Enter Your Message");
		HBox.setHgrow(sendFld, Priority.ALWAYS);
		sendBox.getChildren().add(sendFld);
		Button sendBtn = new Button("Send");
		sendBtn.setPrefWidth(60);
		sendBox.getChildren().add(sendBtn);
		
		sendBtn.setOnAction((event) -> {
			if (!sendFld.getText().equals("")) {
				client.send(new Message(sendFld.getText()));
				sendFld.clear();
			}
		});
		
		client.setOnDataRecieved(e -> {
			Message m = (Message)e;
			chatBox.appendText(m.getSender() + ": " + m.getMessage() + "\n");
		});
	}
	
}
