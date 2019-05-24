import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public class HelixTest extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		Panel panel = new Panel("This is the title");
		panel.getStyleClass().add("panel-primary");
		BorderPane content = new BorderPane();
		content.setPadding(new Insets(20));
		Button button = new Button("Hello BootstrapFX");
		button.getStyleClass().setAll("btn","btn-success");
		
		button.addEventHandler(ActionEvent.ACTION, (EventHandler<Event>) event -> button.setText("Hello!"));
		
		content.setCenter(button);
		panel.setBody(content);
		
		Scene scene = new Scene(panel);
		scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
		
		primaryStage.setTitle("BootstrapFX");
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();
	}
	
	@AllArgsConstructor
	@Getter
	@ToString
	static class Person {
		
		private String name;
		private int age;
		
	}
}
