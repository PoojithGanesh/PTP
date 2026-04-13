import java.util.*;
import java.io.*;
class Candidate {
    private String name;
    private int votes;

    public Candidate(String name) {
        this.name = name;
        this.votes = 0;
    }

    public String getName() {
        return name;
    }

    public int getVotes() {
        return votes;
    }

    public void addVote() {
        votes++;
    }
}
public class VotingSystem {

    private static HashMap<String, Candidate> candidates = new HashMap<>();
    private static HashSet<String> votedUsers = new HashSet<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== ONLINE VOTING SYSTEM =====");
            System.out.println("1. Add Candidate");
            System.out.println("2. Cast Vote");
            System.out.println("3. View Results");
            System.out.println("4. Announce Winner");
            System.out.println("5. Save Results");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");
            
            while (!sc.hasNextInt()) {
                System.out.println("Enter valid number!");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addCandidate();
                case 2 -> castVote();
                case 3 -> displayResults();
                case 4 -> showWinner();
                case 5 -> saveResults();
                case 6 -> System.out.println("Thank you!");
                default -> System.out.println("Invalid choice!");
            }

        } while (choice != 6);
    }
    private static void addCandidate() {
        System.out.print("Enter candidate name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty!");
            return;
        }

        if (candidates.containsKey(name)) {
            System.out.println("Candidate already exists!");
        } else {
            candidates.put(name, new Candidate(name));
            System.out.println("Candidate added successfully!");
        }
    }
    private static void castVote() {
        try {
            System.out.print("Enter Voter ID: ");
            String voterId = sc.nextLine().trim();

            if (voterId.isEmpty()) {
                System.out.println("Invalid Voter ID!");
                return;
            }

            if (votedUsers.contains(voterId)) {
                System.out.println("You have already voted!");
                return;
            }

            if (candidates.isEmpty()) {
                System.out.println("No candidates available!");
                return;
            }

            System.out.println("Available Candidates:");
            for (String name : candidates.keySet()) {
                System.out.println("- " + name);
            }

            System.out.print("Enter candidate name: ");
            String name = sc.nextLine();

            Candidate c = candidates.get(name);

            if (c != null) {
                c.addVote();
                votedUsers.add(voterId);
                System.out.println("Vote cast successfully!");
            } else {
                System.out.println("Candidate not found!");
            }

        } catch (Exception e) {
            System.out.println("Error occurred while voting!");
        }
    }
    private static void displayResults() {
        if (candidates.isEmpty()) {
            System.out.println("No candidates available!");
            return;
        }

        System.out.println("\n===== RESULTS =====");
        for (Candidate c : candidates.values()) {
            System.out.println(c.getName() + " -> " + c.getVotes() + " votes");
        }
    }
    private static void showWinner() {
        if (candidates.isEmpty()) {
            System.out.println("No candidates!");
            return;
        }

        int maxVotes = -1;
        ArrayList<String> winners = new ArrayList<>();

        for (Candidate c : candidates.values()) {
            if (c.getVotes() > maxVotes) {
                maxVotes = c.getVotes();
                winners.clear();
                winners.add(c.getName());
            } else if (c.getVotes() == maxVotes) {
                winners.add(c.getName());
            }
        }

        if (maxVotes == 0) {
            System.out.println("No votes cast yet!");
        } else if (winners.size() == 1) {
            System.out.println("Winner: " + winners.get(0) + " (" + maxVotes + " votes)");
        } else {
            System.out.println("It's a tie between:");
            for (String w : winners) {
                System.out.println("- " + w);
            }
        }
    }
    private static void saveResults() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("results.txt"))) {

            for (Candidate c : candidates.values()) {
                bw.write(c.getName() + " : " + c.getVotes());
                bw.newLine();
            }

            System.out.println("Results saved successfully!");

        } catch (IOException e) {
            System.out.println("Error saving results!");
        }
    }
}
