package utils;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class AnswerUtil {
	private HBox box;
	private RadioButton button;
	private TextField field;

	public AnswerUtil(HBox box) {
		this.box = box;
		this.button = new RadioButton();
		this.field = new TextField();
		box.getChildren().addAll(button, field);

	}

	public HBox getBox() {
		return box;
	}

	public RadioButton getButton() {
		return button;
	}

	public TextField getField() {
		return field;
	}
}
