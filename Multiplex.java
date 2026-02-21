import java.util.Scanner;
class Screen {
    int screenNumber;
    String moviePlaying;
    char[][] seats;
    int ticketPrice;
    public Screen(int screenNumber, String moviePlaying, int ticketPrice) {
        this.screenNumber = screenNumber;
        this.moviePlaying = moviePlaying;
        this.ticketPrice = ticketPrice;
        this.seats = new char[10][10];
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                seats[row][col] = 'O';
            }
        }
    }
    public void displaySeats() {
        System.out.println("\n--- Screen " + screenNumber + ": " + moviePlaying + " ---");
        System.out.println("Ticket Price: ₹" + ticketPrice);
        System.out.print("   ");
        for (int i = 0; i < 10; i++) System.out.print(i + " ");
        System.out.println();
        for (int row = 0; row < 10; row++) {
            System.out.print(row + "  ");
            for (int col = 0; col < 10; col++) {
                System.out.print(seats[row][col] + " ");
            }
            System.out.println();
        }
    }
    public boolean bookSeat(int row, int col) {
        if (row < 0 || row > 9 || col < 0 || col > 9) {
            System.out.println("Invalid seat! Choose between 0-9.");
            return false;
        }
        if (seats[row][col] == 'X') {
            System.out.println("Sorry, seat [" + row + "," + col + "] is already taken!");
            return false;
        } else {
            seats[row][col] = 'X';
            System.out.println("Success! Booked: " + moviePlaying + " | Row " + row + ", Col " + col);
            return true;
        }
    }
}
public class Multiplex {
    public static void main(){
        Scanner scanner = new Scanner(System.in);
        int totalBill = 0;
        Screen[] screens = new Screen[5];
        screens[0] = new Screen(1, "Spider-Man: No Way Home", 350);
        screens[1] = new Screen(2, "Sholay", 150);
        screens[2] = new Screen(3, "Inception", 250);
        screens[3] = new Screen(4, "The Dark Knight", 250);
        screens[4] = new Screen(5, "Interstellar", 300);
        boolean running = true;
        while (running) {
            System.out.println("\n======= JAVA MULTIPLEX MENU =======");
            for (int i = 0; i < screens.length; i++) {
                System.out.println((i + 1) + ". " + screens[i].moviePlaying + " (₹" + screens[i].ticketPrice + ")");
            }
            System.out.println("6. Checkout & Exit");
            System.out.print("Select a movie (1-5) or 6 to Exit: ");
            int choice = scanner.nextInt();
            if (choice >= 1 && choice <= 5) {
                Screen selectedScreen = screens[choice - 1]; 
                selectedScreen.displaySeats();
                System.out.print("\nEnter Row (0-9): ");
                int row = scanner.nextInt();
                System.out.print("Enter Column (0-9): ");
                int col = scanner.nextInt();
                if (selectedScreen.bookSeat(row, col)) {
                    totalBill += selectedScreen.ticketPrice;
                    System.out.println("Current Total Bill: ₹" + totalBill);
                }
            } else if (choice == 6) {
                System.out.println("\n------------------------------");
                System.out.println("       RECEIPT SUMMARY        ");
                System.out.println("------------------------------");
                System.out.println("Total Amount Payable: ₹" + totalBill);
                System.out.println("------------------------------");
                System.out.println("Thank you for visiting!");
                running = false;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}