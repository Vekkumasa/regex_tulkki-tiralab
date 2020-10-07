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

import regex.nfa.nfaFragmentit;
import regex.nfa.regexToPostfix;
import regex.nfa.nfa;
import regex.domain.*;
import java.util.Stack;
import regex.dfa.dfa;

public class UI extends Application {
    
    private BackgroundFill bf;
    private Scene mainScene;
    private Label match;
    private regexToPostfix postfix;
    private nfaFragmentit nfaFrag;
    
    private Stack<Kaari> kaaret;
    
    @Override
    public void init() throws Exception {
        
        bf = new BackgroundFill(Color.ANTIQUEWHITE, new CornerRadii(1),
         new Insets(0.0, 0.0, 0.0, 0.0));
        postfix = new regexToPostfix();
        this.nfaFrag = new nfaFragmentit();
        this.kaaret = new Stack();
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
        
        match = new Label("");
        
        Button luoNfa = new Button("Tarkista syöte");
        luoNfa.setOnAction(e -> {
            nfa nfa;
            if (inputField.getText().isEmpty()) {
                match.setText("Input field is empty");
                match.setTextFill(Color.RED);
                return;
            } else {
                nfa = new nfa(inputField.getText());
                nfa.luoNfa();
                nfa.faktatTiskiin(nfa.getKaari().getAlku());
            }
            
            dfa dfa = new dfa(nfa, regexField.getText());
            dfa.luoDfa();
            if (!dfa.tarkista()) {
                match.setText("String is not valid");
                match.setTextFill(Color.RED);
                System.out.println("false");
            } else {
                match.setText("String is valid");
                match.setTextFill(Color.GREEN);
                System.out.println("true");
            }
        });
        
        mainPane.setBackground(new Background(bf));
        
        mainPane.getChildren().addAll(input, inputField, regex, regexField, match, luoNfa);
        
        mainScene = new Scene(mainPane, 500, 300); 
        
        window.setTitle("Regex-kääntäjä");
        window.setScene(mainScene);
        window.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
