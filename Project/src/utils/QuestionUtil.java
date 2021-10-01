package utils;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class QuestionUtil {
	
	private HBox box;
	private Question question;
	
	

	public QuestionUtil(Question q) {
		this.question = q;
		this.box = new HBox();		
		box.setPadding(new Insets(20, 20, 20, 20));
		Text label = new Text(q.getQuestion());
		label.setWrappingWidth(350);
		label.getStyleClass().add("listview-text");
		box.getChildren().add(label);
		
	}

	public HBox getBox() {
		return box;
	}

	
	public Question getQuestion() {
		return question;
	}
}
