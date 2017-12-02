package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Lobby extends VBox {
	private String name;
	private ArrayList<Stage> stageList;
	private boolean roomCreated;
	private String address;
	
	public Lobby(String name, Stage currentStage) {
		this.roomCreated = false;
		this.name = name;
		this.stageList = new ArrayList<>();
		this.address = "";
		setSpacing(10);
		setPadding(new Insets(10));
		setAlignment(Pos.CENTER);
		Text welcomeTxt = new Text(this.name.equals("") ? "มึงเป็นใครวะ? ทำไมไม่ใส่ชื่อ?" : "Welcome, " + this.name);
		welcomeTxt.setFont(new Font("Arial", 30));
		getChildren().add(welcomeTxt);
		Button crButton = new Button("Create Room");
		crButton.setPrefWidth(120);
		getChildren().add(crButton);
		Text orTxt = new Text("Or");
		orTxt.setFont(new Font("Arial", 30));
		getChildren().add(orTxt);
		TextField IPFld = new TextField();
		IPFld.setMaxWidth(200);
		IPFld.setPromptText("Enter IP Address");
		IPFld.setAlignment(Pos.BASELINE_CENTER);
		getChildren().add(IPFld);
		Button jrButton = new Button("Join Room");
		jrButton.setPrefWidth(120);
		getChildren().add(jrButton);
		
		crButton.setOnAction((event) -> {
			try {
				if (!roomCreated) {
					CreateChatRoom();
					roomCreated = true;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		jrButton.setOnAction((event) -> {
			try {
				address = IPFld.getText();
				if (!address.equals(""))
					CreateChatRoom();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		currentStage.setOnCloseRequest((event) -> {
			for (Stage s : stageList)
				s.close();
		});
	}
	
	private void CreateChatRoom() throws IOException {
		Stage stage = new Stage();
		Room room;
		if (address.equals(""))
			room = new Room();
		else
			room = new Room(address);
		Scene scene = new Scene(room, 400, 500);
		stage.setScene(scene);
		stage.setTitle("Chat Room");
		stage.show();
		this.stageList.add(stage);
	}
}
