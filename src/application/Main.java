package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import room.Account;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class Main extends Application {
	private Stage stage;
	
	@Override
	public void start(Stage primaryStage) {
		this.stage = primaryStage;
		primaryStage.setScene(logInScene());
		primaryStage.setTitle("Chat Server");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private Scene logInScene() {
		VBox root = new VBox(10);
		root.setPadding(new Insets(10));
		root.setAlignment(Pos.CENTER);
		Label logInLbl = new Label("Enter your name");
		root.getChildren().add(logInLbl);
		TextField logInFld = new TextField();
		logInFld.setMaxWidth(250);
		logInFld.setAlignment(Pos.BASELINE_CENTER);
		root.getChildren().add(logInFld);
		Button logInBtn = new Button("OK");
		logInBtn.setPrefWidth(60);
		logInBtn.setOnAction((event) -> {
			Account.name = logInFld.getText();
			Lobby lobby = new Lobby(logInFld.getText(), stage);
			Scene scene = new Scene(lobby, 500, 500);
			stage.setScene(scene);
		});
		root.getChildren().add(logInBtn);
		Scene scene = new Scene(root, 400, 200);
		return scene;
	}
}
