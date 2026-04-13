import java.util.*;
import java.io.*;
class Patient {
    private int id;
    private String name;
    private int age;
    private String disease;
    public Patient(int id, String name, int age, String disease) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.disease = disease;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getDisease() { return disease; }

    public void setDisease(String disease) {
        this.disease = disease;
    }
    public void display() {
        System.out.println("ID: " + id + ", Name: " + name +
                ", Age: " + age + ", Disease: " + disease);
    }
}
public class PatientSystem {
    static ArrayList<Patient> list = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== PATIENT RECORD SYSTEM =====");
            System.out.println("1. Add Patient");
            System.out.println("2. Search Patient");
            System.out.println("3. Update Medical Record");
            System.out.println("4. Delete Patient");
            System.out.println("5. Save to File");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            while (!sc.hasNextInt()) {
                System.out.println("Enter valid number!");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> addPatient();
                case 2 -> searchPatient();
                case 3 -> updatePatient();
                case 4 -> deletePatient();
                case 5 -> saveToFile();
                case 6 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }

        } while (choice != 6);
    }
    static void addPatient() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Patient p : list) {
            if (p.getId() == id) {
                System.out.println("Patient ID already exists!");
                return;
            }
        }
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Disease: ");
        String disease = sc.nextLine();
        list.add(new Patient(id, name, age, disease));
        System.out.println("Patient added successfully!");
    }
    static void searchPatient() {
        System.out.print("Enter ID to search: ");
        int id = sc.nextInt();
        for (Patient p : list) {
            if (p.getId() == id) {
                p.display();
                return;
            }
        }
        System.out.println("Patient not found!");
    }
    static void updatePatient() {
        System.out.print("Enter ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Patient p : list) {
            if (p.getId() == id) {
                System.out.print("Enter new disease: ");
                String disease = sc.nextLine();
                p.setDisease(disease);
                System.out.println("Record updated!");
                return;
            }
        }
        System.out.println("Patient not found!");
    }
    static void deletePatient() {
        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt();
        Iterator<Patient> it = list.iterator();
        while (it.hasNext()) {
            Patient p = it.next();
            if (p.getId() == id) {
                it.remove();
                System.out.println("Patient deleted!");
                return;
            }
        }
        System.out.println("Patient not found!");
    }
    static void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("patients.txt"))) {

            for (Patient p : list) {
                bw.write(p.getId() + "," + p.getName() + "," +
                        p.getAge() + "," + p.getDisease());
                bw.newLine();
            }
            System.out.println("Data saved to file!");
        } catch (IOException e) {
            System.out.println("Error saving file!");
        }
    }
}
