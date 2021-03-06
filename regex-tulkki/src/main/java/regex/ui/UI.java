package regex.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import regex.nfa.nfaFragmentit;
import regex.nfa.regexToPostfix;
import regex.nfa.nfa;
import regex.dfa.dfa;

public class UI extends Application {
    
    private BackgroundFill bf;
    private Scene mainScene;
    private Label match;
    private regexToPostfix postfix;
    private nfaFragmentit nfaFrag;
    
    
    @Override
    public void init() throws Exception {
        
        bf = new BackgroundFill(Color.ANTIQUEWHITE, new CornerRadii(1),
         new Insets(0.0, 0.0, 0.0, 0.0));
        postfix = new regexToPostfix();
        this.nfaFrag = new nfaFragmentit();
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
                match.setText("Lauseke kenttä on tyhjä");
                match.setTextFill(Color.RED);
                return;
                
            } else if (!tarkistaMerkit(inputField.getText()) ) {
                match.setText("Sallitut merkit ovat: A-Z , a-z, 0-9, * , + , ( , ) ja |");
                match.setTextFill(Color.RED); 
                return;
                
            } else {
                nfa = new nfa(inputField.getText());
                nfa.luoNfa();
            }
            
            dfa dfa = new dfa(nfa, regexField.getText());
            dfa.luoDfa();
            if (!dfa.tarkista()) {
                match.setText("Hylätty syöte");
                match.setTextFill(Color.RED);
            } else {
                match.setText("Hyväksytty syöte");
                match.setTextFill(Color.GREEN);
            }
        });
        
        mainPane.setBackground(new Background(bf));
        
        mainPane.getChildren().addAll(input, inputField, regex, regexField, match, luoNfa);
        
        mainScene = new Scene(mainPane, 500, 300); 
        
        window.setTitle("Regex-kääntäjä");
        window.setScene(mainScene);
        window.show();
    }
    
    public boolean tarkistaMerkit(String lauseke) {
        for (int i = 0; i < lauseke.length(); i++) {
            char c = lauseke.charAt(i);
            if (!sallitutMerkit(c)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Tarkistetaan että lausekkeen merkit ovat joko isoja/pieniä kirjaimia, numeroita tai
     * joku seuraavista erikoismerkeistä: + , * , ( , ) , | 
     * @param c
     * @return 
     */
    public boolean sallitutMerkit(char c) {
        if (c == 40 || c == 41 || c == 42 || c == 43 || c ==  124) {
            return true;
        } else if (c >= 97 && c <= 122 || c >= 65 && c <= 90 || c >= 48 && c <=  57) {
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
