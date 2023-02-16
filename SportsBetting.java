import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class SportsBetting {
    private int userBalance;
    private HashMap<String, Double> sportsList;
    private int betAmount;
    private String selectedSport;
    private Double odds;
    private boolean win;

    public SportsBetting(int userBalance) {
        this.userBalance = userBalance;
        this.sportsList = new HashMap<String, Double>();
        this.sportsList.put("Football", 1.5);
        this.sportsList.put("Basketball", 1.7);
        this.sportsList.put("Tennis", 2.1);
        this.betAmount = 0;
        this.selectedSport = null;
        this.odds = null;
        this.win = false;
    }

    public void displayBalance() {
        System.out.println("Your current balance is " + this.userBalance + ".");
    }

    public void selectSport() {
        System.out.println("Please select a sport:");
        for (String sport : this.sportsList.keySet()) {
            System.out.println(sport + ": " + this.sportsList.get(sport));
        }
        Scanner scanner = new Scanner(System.in);
        this.selectedSport = scanner.nextLine();
        if (!this.sportsList.containsKey(this.selectedSport)) {
            System.out.println("Invalid sport selected!");
            selectSport();
        } else {
            this.odds = this.sportsList.get(this.selectedSport);
        }
    }

    public void placeBet() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter bet amount: ");
        this.betAmount = scanner.nextInt();
        if (this.betAmount > this.userBalance) {
            System.out.println("You do not have enough balance to place this bet!");
            placeBet();
        }
    }

    public void simulateGame() {
        String winner = new Random().nextInt(2) == 0 ? "Team A" : "Team B";
        System.out.println("The winner of the " + this.selectedSport + " game is " + winner + "!");
        if (winner.equals("Team A")) {
            this.win = true;
        } else {
            this.win = false;
        }
    }

    public void calculatePayout() {
        if (this.win) {
            double payout = this.betAmount * this.odds;
            this.userBalance += payout;
            System.out.println(
                    "You won " + payout + "! Your new balance is " + this.userBalance + ".");
        } else {
            this.userBalance -= this.betAmount;
            System.out.println(
                    "You lost " + this.betAmount + ". Your new balance is " + this.userBalance
                            + ".");
        }
    }

    public void run() {
        System.out.println("Welcome to the sports betting application!");
        while (true) {
            displayBalance();
            selectSport();
            placeBet();
            simulateGame();
            calculatePayout();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Do you want to continue betting? (y/n): ");
            String choice = scanner.nextLine();
            if (choice.toLowerCase().equals("n")) {
                System.out.println("Thank you for using the sports betting application!");
                break;
            }
        }
    }

    public static void main(String[] args) {
        SportsBetting sportsBetting = new SportsBetting(100);
        sportsBetting.run();
    }
}

