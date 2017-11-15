package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setScene(logInScene());
		primaryStage.setTitle("Chat Server");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private Scene logInScene() {
		VBox root = new VBox(10);
		root.setAlignment(Pos.CENTER);
		Label logInLbl = new Label("Enter your name");
		root.getChildren().add(logInLbl);
		TextField logInFld = new TextField();
		logInFld.setMaxWidth(250);
		logInFld.setAlignment(Pos.BASELINE_CENTER);
		root.getChildren().add(logInFld);
		VBox.setMargin(logInFld, new Insets(0, 10, 0, 10));
		Button logInBtn = new Button("OK");
		logInBtn.setPrefWidth(60);
		root.getChildren().add(logInBtn);
		Scene scene = new Scene(root, 400, 200);
		return scene;
	}
}
