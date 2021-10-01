import Panes.MainPane;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import utils.Utils;

public class Main extends Application {

	public static void main(String[] args) {

		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Utils.init();

		stage.setTitle("Question Bank");
		stage.setResizable(false);
		new MainPane(stage);
		stage.show();
		stage.getIcons().add(new Image("resources\\project_logo32.png"));
		stage.setOnCloseRequest(e -> {
			Utils.getStack().update();
		});
	}

}
