package Panes;

import java.util.LinkedList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import utils.Answer;
import utils.AnswerUtil;
import utils.Question;
import utils.Utils;

public class ModifyQuestionPane extends BorderPane {

	public ModifyQuestionPane(Stage stage, Question q) {
		LinkedList<AnswerUtil> list = new LinkedList<>();
		BackgroundImage myBI = new BackgroundImage(new Image("resources\\tiled_background.png", 256, 256, true, true),
				BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		setBackground(new Background(myBI));
		setPadding(new Insets(16));
		ToggleGroup team = new ToggleGroup();
		VBox stack = new VBox(50);
		Scene scene = new Scene(this, 500, 600);
		stage.setHeight(600);
		scene.getStylesheets().add("file:style.css");
		VBox container = new VBox(20);
		container.setPadding(new Insets(32, 0, 0, 0));
		Label instructions = new Label("Fill the required fields to submit a new question");
		instructions.setFont(Font.font("SansSerif", FontWeight.THIN, FontPosture.REGULAR, 16));
		instructions.setPadding(new Insets(4));
		instructions.setStyle("-fx-text-fill: white");

		container.setStyle("-fx-background: rgba(0, 100, 100, 0)");
		GridPane questionContainer = new GridPane();
		questionContainer.setHgap(20);
		TextField questionText = new TextField();
		questionText.setMinHeight(30);
		questionText.setPromptText("Enter question text");
		questionText.setMinWidth(325);
		Label questionLabel = new Label("Question");
		questionLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 16));
		questionLabel.getStyleClass().add("question-label");
		questionContainer.add(instructions, 0, 0, 2, 1);
		questionContainer.add(questionLabel, 0, 1);
		questionContainer.add(questionText, 1, 1);
		questionContainer.setAlignment(Pos.BOTTOM_CENTER);
		questionContainer.setPadding(new Insets(16));
		questionContainer.setStyle("-fx-background-radius: 8px; -fx-background-color: rgba(53, 53, 53, 100)");
		questionContainer.setVgap(20);
		HBox.setMargin(questionContainer, new Insets(20, 0, 0, 0));
		container.getChildren().add(questionContainer);
		setTop(questionContainer);
		stack.getChildren().add(container);

		if (q != null) {
			// Populate the textfields with the question
			questionText.setText(q.getQuestion());
			for (int i = 0; i < q.getAnswers().length; i++) {
				AnswerUtil answer3 = new AnswerUtil(new HBox(20));
				answer3.getField().setMinWidth(400);
				answer3.getField().setPromptText("Enter answer text");
				answer3.getBox().setAlignment(Pos.CENTER);
				answer3.getField().setText(q.getAnswers()[i].getAnswer());
				answer3.getField().textProperty().addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> ov, String oldVal, String newVal) {
						if (answer3.getField().getText().length() > 32) {
							String s = answer3.getField().getText().substring(0, 32);
							answer3.getField().setText(s);
						}

					}
				});
				if (q.getAnswers()[i].isCorrect()) {
					answer3.getButton().setSelected(true);
					answer3.getField().getStyleClass().add("correct-answer");
				}
				answer3.getButton().setToggleGroup(team);
				answer3.getBox().setPadding(new Insets(8));
				list.add(answer3);
				container.getChildren().add(answer3.getBox());
			}
			if (q.getAnswers().length >= 5)
				stage.setHeight(660);
		} else {
			// Create empty textfields if no question
			for (int i = 0; i < 2; i++) {
				AnswerUtil ans = new AnswerUtil(new HBox(20));
				ans.getField().setMinWidth(400);
				ans.getBox().setAlignment(Pos.CENTER);
				ans.getField().setPromptText("Enter answer text");
				ans.getField().textProperty().addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> ov, String oldVal, String newVal) {
						if (ans.getField().getText().length() > 32) {
							String s = ans.getField().getText().substring(0, 32);
							ans.getField().setText(s);
						}

					}
				});
				if (i == 0) {
					ans.getButton().setSelected(true);
					ans.getField().getStyleClass().add("correct-answer");
				}
				ans.getButton().setToggleGroup(team);
				list.add(ans);
				container.getChildren().addAll(ans.getBox());
			}
		}
		HBox bottomBox = new HBox(20);

		bottomBox.getStyleClass().add("button-box-container");

		bottomBox.setPadding(new Insets(16));
		bottomBox.setStyle("");
		bottomBox.setAlignment(Pos.CENTER);
		Button cancelButton = new Button("Cancel", new ImageView(new Image("resources\\cancel_icon.png")));
		cancelButton.setContentDisplay(ContentDisplay.TOP);
		cancelButton.setMinSize(90, 100);
		cancelButton.setStyle("-fx-border-color: none; -fx-background-radius:8px");
		Button removeAnswerButton = new Button("Remove", new ImageView(new Image("resources\\remove_icon.png")));
		removeAnswerButton.setContentDisplay(ContentDisplay.TOP);
		removeAnswerButton.setMinSize(90, 100);
		removeAnswerButton.setStyle("-fx-border-color: none; -fx-background-radius:8px");
		Button addAnswerButton = new Button("Add", new ImageView(new Image("resources\\add_icon.png")));
		addAnswerButton.setContentDisplay(ContentDisplay.TOP);
		addAnswerButton.setMinSize(90, 100);
		addAnswerButton.setStyle("-fx-border-color: none; -fx-background-radius:8px");
		Button saveButton = new Button("Save", new ImageView(new Image("resources\\save_icon.png")));
		saveButton.setContentDisplay(ContentDisplay.TOP);
		saveButton.setMinSize(90, 100);
		saveButton.setStyle("-fx-border-color: none; -fx-background-radius:8px");
		bottomBox.getChildren().addAll(cancelButton, addAnswerButton, removeAnswerButton, saveButton);
		stack.getStyleClass().add("viewer-box");
		setBottom(bottomBox);
		setCenter(stack);
		stage.setScene(scene);
		questionText.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> ov, String oldVal, String newVal) {
				if (questionText.getText().length() > 80) {
					String s = questionText.getText().substring(0, 80);
					questionText.setText(s);
				}

			}
		});
		team.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> o, Toggle previous, Toggle next) {
				try {
					find(list, (RadioButton) previous).getField().getStyleClass().remove("correct-answer");
				} catch (NullPointerException e) {
				}
				find(list, (RadioButton) next).getField().getStyleClass().add("correct-answer");
			}

		});
		saveButton.setOnAction(e -> {

			if (questionText.getText().isBlank())
				Utils.error("Empty Question Field", "Question is blank, please fill the question field");
			else if (q != null && !questionText.getText().equals(q.getQuestion())
					&& Utils.getStack().hasDuplicate(questionText.getText()))
				Utils.error("Error", "Question is already in the question bank");
			else if (q == null && Utils.getStack().hasDuplicate(questionText.getText()))
				Utils.error("Duplicate question", "Question is already in the question bank");
			else {
				for (AnswerUtil answerutil : list) {
					if (answerutil.getField().getText().isBlank()) {
						Utils.error("Empty Answer Field", "Some answers are blank, please fill all answers fields");
						return;
					}
				}
				if (q != null)
					Utils.getStack().getQuestions().remove(q);
				Answer[] answer = new Answer[list.size()];
				int i = 0;
				for (AnswerUtil answerutil : list) {
					answer[i] = new Answer(answerutil.getField().getText(), answerutil.getButton().isSelected());
					i++;
				}
				Utils.getStack().getQuestions().add(new Question(questionText.getText(), answer));
				new MainPane(stage);
			}

		});
		addAnswerButton.setOnAction(e -> {

			if (list.size() >= 6)
				Utils.error("Error", "Maximum answers is 6");
			else {
				AnswerUtil answer3 = new AnswerUtil(new HBox(20));
				answer3.getField().setMinWidth(400);
				answer3.getField().setPromptText(":Enter answer text");
				answer3.getBox().setAlignment(Pos.CENTER);
				answer3.getButton().setToggleGroup(team);
				list.add(answer3);
				if (list.size() == 6)
					stage.setHeight(650);
				container.getChildren().addAll(answer3.getBox());
			}
		});
		cancelButton.setOnAction(e -> {
			new MainPane(stage);
		});
		removeAnswerButton.setOnAction(e -> {
			if (list.size() <= 2)
				Utils.error("Minimum answer count is 2", "Cannot go below two answers");
			else {
				AnswerUtil answer = list.getLast();
				list.removeLast();
				container.getChildren().removeAll(answer.getBox());
				if (answer.getButton().isSelected()) {
					list.getLast().getField().getStyleClass().add("correct-answer");
					list.getLast().getButton().setSelected(true);
				}
				if (list.size() == 5)
					stage.setHeight(600);
			}
		});

	}

	public static AnswerUtil find(LinkedList<AnswerUtil> list, RadioButton button) {
		for (AnswerUtil util : list)
			if (util.getButton() == button)
				return util;
		return null;
	}
}
