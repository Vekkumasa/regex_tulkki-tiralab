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
    private nfa nfa;
    private dfa dfa;
    
    private Stack<Kaari> kaaret;
    
    @Override
    public void init() throws Exception {
        
        bf = new BackgroundFill(Color.ANTIQUEWHITE, new CornerRadii(1),
         new Insets(0.0, 0.0, 0.0, 0.0));
        postfix = new regexToPostfix();
        this.nfaFrag = new nfaFragmentit();
        this.kaaret = new Stack();
        this.nfa = new nfa("a(bb)+a");
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
        
        Button luoNfa = new Button("luoNfa");
        luoNfa.setOnAction(e -> {

            kaaret.add(nfaFrag.luoKirjainTila('a')); // 0, 1
            kaaret.add(nfaFrag.luoKirjainTila('b')); // 2, 3
            kaaret.add(nfaFrag.luoKirjainTila('b')); // 4, 5
            Kaari oikea = kaaret.pop();
            Kaari vasen = kaaret.pop();
            kaaret.add(nfaFrag.luoKonkatenaatioPisteTila(vasen, oikea));
            kaaret.add(nfaFrag.luoPlusTila(kaaret.pop()));
            oikea = kaaret.peek();
            /*
            System.out.println("ALKU: " + oikea.getAlku().getTila() + " LOPPU: " + oikea.getLoppu().getTila());
            System.out.println("Siirtymä: " + oikea.getAlku().getSiirtyma());
            System.out.println("Alun Seuraava: " + oikea.getAlku().getSeuraava().getTila() + " Alun seuraava2: " + oikea.getAlku().getSeuraava2());
            System.out.println("Lopun seuraava: " + oikea.getLoppu().getSeuraava().getTila() + " Lopun seuraava2: " + oikea.getLoppu().getSeuraava2().getTila());
            
            System.out.println("Stacki: " + kaaret.size());
*/          
            oikea = kaaret.pop();
            vasen = kaaret.pop();
            kaaret.add(nfaFrag.luoKonkatenaatioPisteTila(vasen, oikea));
            kaaret.add(nfaFrag.luoKirjainTila('a'));
            oikea = kaaret.pop();
            vasen = kaaret.pop();
            kaaret.add(nfaFrag.luoKonkatenaatioPisteTila(vasen, oikea));
            
            Kaari kaari = kaaret.peek();
  //          System.out.println("Alku: " + kaari.getAlku().getTila() + " Loppu: " + kaari.getLoppu().getTila());
    //        System.out.println("Alku seuraava: " + kaari.getAlku().getSeuraava().getTila() + " loppu seuraava " + kaari.getLoppu().getSeuraava());
      //      System.out.println("Alku seuraava2: " + kaari.getAlku().getSeuraava2() + " loppu seuraava2 " + kaari.getLoppu().getSeuraava2());
            
            nfa.luoNfa();
            kaari = nfa.getKaari();
            System.out.println("");
  //          System.out.println("Alku: " + kaari.getAlku().getTila() + " Loppu: " + kaari.getLoppu().getTila());
    //        System.out.println("Alku seuraava: " + kaari.getAlku().getSeuraava().getTila() + " loppu seuraava " + kaari.getLoppu().getSeuraava());
      //      System.out.println("Alku seuraava2: " + kaari.getAlku().getSeuraava2() + " loppu seuraava2 " + kaari.getLoppu().getSeuraava2());
            
            System.out.println("");
            nfa.faktatTiskiin(kaari.getAlku());
            System.out.println("");
            System.out.println("Alkutila: " + kaari.getAlku().getTila() + " Lopputila: " + kaari.getLoppu().getTila());
            System.out.println("---DFA---");
            System.out.println("");
            this.dfa = new dfa(nfa, "abbbba");
            dfa.luoDfa();
        });
        
        mainPane.setBackground(new Background(bf));
        
        mainPane.getChildren().addAll(input, inputField, regex, regexField, match, luoNfa);
        
        mainScene = new Scene(mainPane, 500, 300); 
        
        window.setTitle("Regex-tulkki");
        window.setScene(mainScene);
        window.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
