import java.util.*;
class Loan {
    int id;
    String name;
    double amount, rate, emi;
    int years, monthsPaid;

    Loan(int id, String name, double amount, double rate, int years) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.rate = rate;
        this.years = years;
        this.monthsPaid = 0;
        calculateEMI();
    }

    void calculateEMI() {
        double r = rate / (12 * 100);
        int n = years * 12;
        emi = (amount * r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
    }

    void payEMI() {
        if (monthsPaid < years * 12) {
            monthsPaid++;
            System.out.println("EMI Paid Successfully!");
        } else {
            System.out.println("Loan Already Completed!");
        }
    }

    void display() {
        System.out.println("\nLoan ID: " + id);
        System.out.println("Customer: " + name);
        System.out.println("Loan Amount: " + amount);
        System.out.println("EMI: " + emi);
        System.out.println("Months Paid: " + monthsPaid);
        System.out.println("Status: " + (monthsPaid == years * 12 ? "Completed" : "Ongoing"));
    }
}

public class Finance {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Loan> list = new ArrayList<>();

        int choice, idCounter = 1;

        do {
            System.out.println("\n1.Add Loan 2.View Loans 3.Pay EMI 4.Exit");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Amount: ");
                    double amount = sc.nextDouble();

                    System.out.print("Enter Rate: ");
                    double rate = sc.nextDouble();

                    System.out.print("Enter Years: ");
                    int years = sc.nextInt();

                    list.add(new Loan(idCounter++, name, amount, rate, years));
                    System.out.println("Loan Added!");
                    break;

                case 2:
                    for (Loan l : list)
                        l.display();
                    break;

                case 3:
                    System.out.print("Enter Loan ID: ");
                    int id = sc.nextInt();

                    boolean found = false;
                    for (Loan l : list) {
                        if (l.id == id) {
                            l.payEMI();
                            found = true;
                        }
                    }
                    if (!found)
                        System.out.println("Loan Not Found!");
                    break;
            }

        } while (choice != 4);
        sc.close();
    }
}
