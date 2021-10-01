package Panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import utils.Answer;
import utils.Question;

public class ViewerPane extends BorderPane {

	public ViewerPane(Question question) {
		setMinWidth(500);
		getStyleClass().add("viewer-box");
		Text questionText = new Text(question.getQuestion());
		questionText.setWrappingWidth(400);
		questionText.getStyleClass().add("question-text");
		questionText.setFont(Font.font("SansSerif", FontWeight.BOLD, 14));
		VBox box = new VBox(20);
		box.setPadding(new Insets(16));
		box.getStyleClass().add("question-box");
		HBox questionBox = new HBox();
		questionBox.setPadding(new Insets(16));
		questionBox.getChildren().add(questionText);

		box.getChildren().add(questionBox);
		for (Answer answer : question.getAnswers()) {
			GridPane optionContainer = new GridPane();
			HBox answerBox = new HBox();
			optionContainer.setPadding(new Insets(0, 16, 0, 16));
			optionContainer.setAlignment(Pos.CENTER_LEFT);

			Text answerText = new Text(answer.getAnswer());;
			answerText.getStyleClass().add("answer-text");
			answerBox.getStyleClass().add("answer-box");
			answerBox.getChildren().add(answerText);
			optionContainer.add(
					answer.isCorrect() ? new ImageView("resources\\check.png") : new ImageView("resources\\wrong_icon.png"), 0, 0);
			optionContainer.add(answerBox, 1, 0);
			optionContainer.setStyle("-fx-background-color: rgba(65, 65, 65, 100); -fx-background-radius: 8px;");
			optionContainer.setPadding(new Insets(8));
			optionContainer.setMinWidth(400);

			box.getChildren().add(optionContainer);
		}
		this.getChildren().add(box);
	}

}
