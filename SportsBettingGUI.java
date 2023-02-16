import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Random;

public class SportsBettingGUI extends Application {
    private int userBalance;
    private HashMap<String, Double> sportsList;
    private int betAmount;
    private String selectedSport;
    private Double odds;
    private boolean win;

    @Override
    public void start(Stage primaryStage) {
        this.userBalance = 100;
        this.sportsList = new HashMap<String, Double>();
        this.sportsList.put("Football", 1.5);
        this.sportsList.put("Basketball", 1.7);
        this.sportsList.put("Tennis", 2.1);
        this.betAmount = 0;
        this.selectedSport = null;
        this.odds = null;
        this.win = false;

        Label balanceLabel = new Label("Balance: " + this.userBalance);
        Label sportLabel = new Label("Sport: ");
        Label oddsLabel = new Label("Odds: ");
        Label betAmountLabel = new Label("Bet Amount: ");

        TextField betAmountTextField = new TextField();
        betAmountTextField.setPromptText("Enter bet amount");

        Button footballButton = new Button("Football");
        footballButton.setOnAction(e -> {
            this.selectedSport = "Football";
            this.odds = this.sportsList.get("Football");
            sportLabel.setText("Sport: " + this.selectedSport);
            oddsLabel.setText("Odds: " + this.odds);
        });

        Button basketballButton = new Button("Basketball");
        basketballButton.setOnAction(e -> {
            this.selectedSport = "Basketball";
            this.odds = this.sportsList.get("Basketball");
            sportLabel.setText("Sport: " + this.selectedSport);
            oddsLabel.setText("Odds: " + this.odds);
        });

        Button tennisButton = new Button("Tennis");
        tennisButton.setOnAction(e -> {
            this.selectedSport = "Tennis";
            this.odds = this.sportsList.get("Tennis");
            sportLabel.setText("Sport: " + this.selectedSport);
            oddsLabel.setText("Odds: " + this.odds);
        });

        Button placeBetButton = new Button("Place Bet");
        placeBetButton.setOnAction(e -> {
            try {
                this.betAmount = Integer.parseInt(betAmountTextField.getText());
                if (this.betAmount > this.userBalance) {
                    throw new Exception("You do not have enough balance to place this bet!");
                }
                simulateGame();
                calculatePayout();
                balanceLabel.setText("Balance: " + this.userBalance);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.add(balanceLabel, 0, 0);
        gridPane.add(footballButton, 0, 1);
        gridPane.add(basketballButton, 1, 1);
        gridPane.add(tennisButton, 2, 1);
        gridPane.add(sportLabel, 0, 3);
        gridPane.add(oddsLabel, 0, 4);
        gridPane.add(betAmountLabel, 0, 5);
        gridPane.add(betAmountTextField, 1, 5);
        gridPane.add(placeBetButton, 1, 6);
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(gridPane);

        Scene scene = new Scene(vBox, 300, 300);
        primaryStage.setTitle("Sports Betting");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void simulateGame() {
        // Simulate a game outcome randomly
        Random random = new Random();
        this.win = random.nextBoolean();
    }

    private void calculatePayout() {
        // Calculate the payout based on the bet amount and odds
        if (this.win) {
            this.userBalance += this.betAmount * this.odds;
        } else {
            this.userBalance -= this.betAmount;
        }
        this.betAmount = 0;
        this.selectedSport = null;
        this.odds = null;
        this.win = false;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

