#include <stdio.h>
#include <string.h>
struct Ticket {
    char name[50];
    int age;
    char source[50];
    char destination[50];
    int fare;
};
struct Ticket t;
void viewStations() {
    printf("\n--- Metro Stations ---\n");
    printf("1. Central\n");
    printf("2. Egmore\n");
    printf("3. Park\n");
    printf("4. Airport\n");
    printf("5. Koyambedu\n");
}

int calculateFare(char source[], char destination[]) {
    if (strcmp(source, destination) == 0)
        return 0;
    return 30 + (strlen(source) + strlen(destination));
}

void bookTicket() {
    printf("\nEnter Name: ");
    scanf(" %[^\n]", t.name);

    printf("Enter Age: ");
    scanf("%d", &t.age);

    printf("Enter Source Station: ");
    scanf(" %[^\n]", t.source);

    printf("Enter Destination Station: ");
    scanf(" %[^\n]", t.destination);

    t.fare = calculateFare(t.source, t.destination);

    printf("\nTicket Booked Successfully!\n");
}

void showTicket() {
    printf("\n--- Ticket Details ---\n");
    printf("Name: %s\n", t.name);
    printf("Age: %d\n", t.age);
    printf("From: %s\n", t.source);
    printf("To: %s\n", t.destination);
    printf("Fare: Rs.%d\n", t.fare);
}

int main() {
    int choice;

    while (1) {
        printf("\n=== Metro Train Booking System ===\n");
        printf("1. View Stations\n");
        printf("2. Book Ticket\n");
        printf("3. Show Ticket\n");
        printf("4. Calculate Fare\n");
        printf("5. Exit\n");

        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                viewStations();
                break;

            case 2:
                bookTicket();
                break;

            case 3:
                showTicket();
                break;

            case 4:
                printf("\nFare: Rs.%d\n", calculateFare(t.source, t.destination));
                break;

            case 5:
                printf("\nThank you!\n");
                return 0;

            default:
                printf("\nInvalid choice!\n");
        }
    }

    return 0;
}
