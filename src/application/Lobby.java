package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Lobby extends VBox {
	private String name;
	
	public Lobby(String name) {
		this.name = name;
		setSpacing(10);
		setAlignment(Pos.CENTER);
		Text welcomeTxt = new Text("Welcome, " + this.name);
		welcomeTxt.setFont(new Font("Arial", 30));
		getChildren().add(welcomeTxt);
		setMargin(welcomeTxt, new Insets(10));
	}
}
