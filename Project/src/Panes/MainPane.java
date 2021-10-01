package Panes;

import java.util.HashMap;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import utils.Question;
import utils.QuestionUtil;
import utils.Utils;

public class MainPane extends BorderPane {

	public MainPane(Stage stage) {
		Scene scene = new Scene(this, 1000, 700);
		stage.setHeight(700);
		// adding styles
		scene.getStylesheets().add("file:style.css");
		ListView<HBox> view = new ListView<HBox>();
		view.setMinHeight(500);
		HashMap<HBox, QuestionUtil> map = new HashMap<HBox, QuestionUtil>();

		for (Question q : Utils.getStack().getQuestions()) {
			QuestionUtil util = new QuestionUtil(q);

			view.getItems().add(util.getBox());
			util.getBox().getStyleClass().add("listview-text");
			map.put(util.getBox(), util);
		}
		BorderPane viewRight = new BorderPane();
		viewRight.getStyleClass().add("defaultBackground");
		HBox buttonsHBox = new HBox(30);
		buttonsHBox.setMinWidth(500);

		Button addButton = new Button("Add", new ImageView(new Image("resources\\add_icon.png")));
		addButton.setMinSize(90, 100);
		addButton.setContentDisplay(ContentDisplay.TOP);
		addButton.setStyle("-fx-border-color: none; -fx-background-radius:8px");
		Button deleteButton = new Button("Delete", new ImageView(new Image("resources\\delete_icon.png")));
		deleteButton.setMinSize(90, 100);
		deleteButton.setContentDisplay(ContentDisplay.TOP);
		deleteButton.setStyle("-fx-border-color: none; -fx-background-radius:8px");
		Button editButton = new Button("Edit", new ImageView(new Image("resources\\edit_icon.png")));
		editButton.setContentDisplay(ContentDisplay.TOP);
		editButton.setMinSize(90, 100);
		editButton.setStyle("-fx-border-color: none; -fx-background-radius:8px");
		buttonsHBox.getChildren().addAll(deleteButton, editButton, addButton);
		buttonsHBox.getStyleClass().add("defaultBackground");
		buttonsHBox.setAlignment(Pos.BOTTOM_CENTER);
		buttonsHBox.setPadding(new Insets(16));
		viewRight.setBottom(buttonsHBox);
		BorderPane.setAlignment(buttonsHBox, Pos.CENTER);
		view.setMinWidth(500);

		view.getStyleClass().add("defaultBackground");
		VBox questionsContainer = new VBox(20);
		BackgroundImage myBI = new BackgroundImage(new Image("resources\\tiled_background.png", 256, 256, true, true),
				BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		questionsContainer.setBackground(new Background(myBI));
		questionsContainer.setPadding(new Insets(16));
		questionsContainer.getChildren().add(new ImageView(new Image("resources\\header.png")));
		questionsContainer.getChildren().add(view);
		questionsContainer.setAlignment(Pos.CENTER);
		setLeft(questionsContainer);
		setRight(viewRight);

		stage.setScene(scene);

		view.setOnMouseClicked(e -> {
			if (view.getSelectionModel().getSelectedItem() != null) {
				viewRight.setCenter(new ViewerPane(map.get(view.getSelectionModel().getSelectedItem()).getQuestion()));
			}

		});

		addButton.setOnAction(e -> {
			new ModifyQuestionPane(stage, null);
		});

		deleteButton.setOnAction(e -> {
			if (view.getSelectionModel().getSelectedItem() == null)
				return;
			Utils.getStack().getQuestions().remove(map.get(view.getSelectionModel().getSelectedItem()).getQuestion());
			map.remove(view.getSelectionModel().getSelectedItem());
			view.getItems().remove(view.getSelectionModel().getSelectedItem());
			viewRight.setCenter(null);

		});

		editButton.setOnAction(e -> {
			if (view.getSelectionModel().getSelectedItem() == null)
				Utils.error("No question selected", "You must select a question to edit");
			else
				new ModifyQuestionPane(stage, map.get(view.getSelectionModel().getSelectedItem()).getQuestion());
		});

	}
}
