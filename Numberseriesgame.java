import java.util.Scanner;

public class Numberseriesgame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] series = {
                { 2, 4, 6, 8 },
                { 3, 6, 9, 12 },
                { 5, 10, 15, 20 },
                { 1, 3, 5, 7 },
                { 10, 20, 30, 40 }
        };
        int[] answers = { 10, 15, 25, 9, 50 };
        int score = 0;
        System.out.println("=== NUMBER SERIES GAME ===");
        System.out.println("Find the next number in the series\n");

        for (int i = 0; i < series.length; i++) {
            System.out.println("Question " + (i + 1));
            for (int j = 0; j < series[i].length; j++) {
                System.out.print(series[i][j] + " ");
            }
            System.out.println("?");
            System.out.print("Enter your answer: ");
            int userAnswer = sc.nextInt();
            if (userAnswer == answers[i]) {
                System.out.println("Correct Answer!");
                score += 10;
            } else {
                System.out.println("Wrong Answer!");
                System.out.println("Correct answer is: " + answers[i]);
            }
            System.out.println();
        }
        System.out.println("Game Over");
        System.out.println("Your Score: " + score);
        sc.close();
    }
}
