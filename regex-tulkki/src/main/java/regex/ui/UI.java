
package regex.ui;

import java.text.DecimalFormat;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class UI extends Application {
    
    private BackgroundFill bf;
    private Scene mainScene;
    private Label match;
    
    @Override
    public void init() throws Exception {
        
        bf = new BackgroundFill(Color.ANTIQUEWHITE, new CornerRadii(1),
         new Insets(0.0, 0.0, 0.0, 0.0));
    }
    @Override
    public void start(Stage window) {  
        VBox mainPane = new VBox(10);
        HBox input = new HBox(10);
        HBox regex = new HBox(10);
        
        mainPane.setPadding(new Insets(10));
        
        Label inputLabel = new Label();
        Label regexLabel = new Label();
        
        TextField inputField = new TextField();
        TextField regexField = new TextField();
        
        input.getChildren().addAll(inputLabel, inputField);
        regex.getChildren().addAll(regexLabel, regexField);
        
        inputLabel.setText("Input");
        regexLabel.setText("Regex");
        
        inputField.setPromptText("Input");
        regexField.setPromptText("Regex");
        
        match = new Label("It's a match");
        match.setTextFill(Color.GREEN);
        
        mainPane.setBackground(new Background(bf));
        
        mainPane.getChildren().addAll(input, inputField, regex, regexField, match);
        
        mainScene = new Scene(mainPane, 500, 300); 
        
        window.setTitle("Regex-tulkki");
        window.setScene(mainScene);
        window.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
