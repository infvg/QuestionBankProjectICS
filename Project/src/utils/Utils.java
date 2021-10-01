package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Utils {

	private static QuestionStack qStack;

	public static QuestionStack getStack() {
		return qStack;
	}

	public static void init() {

		qStack = new QuestionStack();
	}

	public static void error(String header, String content) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.getDialogPane().getStylesheets().add("file:style.css");
		alert.getDialogPane().getStyleClass().add("q-alert");
		((Stage) alert.getDialogPane().getScene().getWindow()).setTitle("");
		((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("resources\\wrong_icon.png"));
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
}
