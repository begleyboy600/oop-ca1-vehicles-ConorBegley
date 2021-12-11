package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/** Conor Begley
 * This Vehicle Bookings Management Systems manages the booking of Vehicles
 * by Passengers.
 *
 * This program reads from 3 text files:
 * "vehicles.txt", "passengers.txt", and "next-id-store.txt"
 * You should be able to see them in the project pane.
 * You will create "bookings.txt" at a later stage, to store booking records.
 *
 * "next-id-store.txt" contains one number ("201"), which will be the
 * next auto-generated id to be used to when new vehicles, passengers, or
 * bookings are created.  The value in the file will be updated when new objects
 * are created - but not when objects are recreated from records in
 * the files - as they already have IDs.  Dont change it - it will be updated by
 * the IdGenerator class.
 */

public class App
{
    PassengerStore passengerStore;  // encapsulates access to list of Passengers
    VehicleManager vehicleManager;  // encapsulates access to list of Vehicles
    BookingManager bookingManager;  // deals with all bookings

    public static void main(String[] args) throws FileNotFoundException {
        App app = new App();
        app.start();
       System.out.println("Program exiting... Goodbye");

    }
    public void start() throws FileNotFoundException {
        // create PassengerStore and load all passenger records from text file
        passengerStore = new PassengerStore("passengers.txt");

        // create VehicleManager, and load all vehicles from text file
        vehicleManager = new VehicleManager("vehicles.txt");

        bookingManager = new BookingManager("bookings.txt", passengerStore, vehicleManager);

        try {
            displayMainMenu();        // User Interface - Menu
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void displayMainMenu() throws IOException {

        final String MENU_ITEMS = "\n*** MAIN MENU OF OPTIONS ***\n"
                + "1. Passengers\n"
                + "2. Vehicles\n"
                + "3. Bookings\n"
                + "4. Exit\n"
                + "Enter Option [1,4]";

        final int PASSENGERS = 1;
        final int VEHICLES = 2;
        final int BOOKINGS = 3;
        final int EXIT = 4;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do
        {
            System.out.println("\n"+MENU_ITEMS);
            try
            {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option)
                {
                    case PASSENGERS:
                        System.out.println("Passengers option chosen");
                        displayPassengerMenu();
                        break;
                    case VEHICLES:
                        System.out.println("Vehicles option chosen");
                        displayVehicleMenu();
                        break;
                    case BOOKINGS:
                        System.out.println("Bookings option chosen");
                        displayBookingsMenu();
                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException |NumberFormatException e)
            {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

        System.out.println("\nExiting Main Menu, goodbye.");

    }
    private void displayVehicleMenu() {
        final String MENU_ITEMS = "\n*** PASSENGER MENU ***\n"
                + "1. Show all Vehicles\n"
                + "2. Find Vehicle by Registration\n"
                + "3. Add Vehicle\n"
                + "4. Find Vehicle by Type\n"
                + "5. Exit\n"
                + "Enter Option [1,5]";

        final int SHOW_ALL = 1;
        final int FIND_BY_REG = 2;
        final int ADD_VEHICLE = 3;
        final int FIND_BY_TYPE = 4;
        final int EXIT = 5;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do
        {
            System.out.println("\n"+MENU_ITEMS);
            try
            {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option)
                {
                    case SHOW_ALL:
                        System.out.println("Display ALL Vehicles");
                        vehicleManager.displayAllVehicles();
                        break;
                    case FIND_BY_REG:
                        System.out.println("Find Vehicle by Registration");
                        System.out.println("Enter Registration name: ");
                        String reg = keyboard.nextLine();
                        Vehicle v = vehicleManager.findVehicleByRegNumber(reg);
                        if(v==null)
                            System.out.println("No Vehicle matching the Registration \"" + reg +"\"");
                        else
                            System.out.println("Found Vehicle: \n" + v.toString());
                        break;
                    case ADD_VEHICLE:
                        System.out.println("Enter type: ");
                        String vehicleType = keyboard.nextLine();
                        System.out.println("Enter make: ");
                        String vehicleMake = keyboard.nextLine();
                        System.out.println("enter model: ");
                        String vehicleModel = keyboard.nextLine();
                        System.out.println("enter miles per KwH: ");
                        double vehicleMilesPerKwH = keyboard.nextDouble();
                        System.out.println("enter registration: ");
                        String vehicleRegistration = keyboard.nextLine();
                        System.out.println("enter cost per mile: ");
                        double vehicleCostPerMiles = keyboard.nextDouble();
                        System.out.println("enter year: ");
                        int vehicleYear = keyboard.nextInt();
                        System.out.println("enter month: ");
                        int vehicleMonth = keyboard.nextInt();
                        System.out.println("enter day: ");
                        int vehicleDay = keyboard.nextInt();
                        System.out.println("enter mileage: ");
                        int vehicleMileage = keyboard.nextInt();
                        System.out.println("enter latitude: ");
                        double vehicleLat = keyboard.nextDouble();
                        System.out.println("enter Longititude: ");
                        double vehicleLong = keyboard.nextDouble();
                        vehicleManager.addVehicle(vehicleType, vehicleMake, vehicleModel, vehicleMilesPerKwH, vehicleRegistration,
                                vehicleCostPerMiles, vehicleYear, vehicleMonth, vehicleDay,vehicleMileage, vehicleLat, vehicleLong);

                        break;
                    case FIND_BY_TYPE:
                        System.out.println("Find Vehicle By Type");
                        System.out.println("Enter Vehicle Type: ");
                        String vehicleType2 = keyboard.nextLine();
                        Vehicle t = vehicleManager.findVehicleByType(vehicleType2);
                        if(t==null)
                            System.out.println("No Vehicles matching the Type \"" + vehicleType2 +"\"");
                        else
                            System.out.println("Found Vehicles: \n" + t.toString());
                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException |NumberFormatException e)
            {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

    }
    private void displayPassengerMenu() {
        final String MENU_ITEMS = "\n*** PASSENGER MENU ***\n"
                + "1. Show all Passengers\n"
                + "2. Find Passenger by Name\n"
                + "3. Add Passenger\n"
                + "4. Delete Passenger\n"
                + "5. Edit Passenger\n"
                + "6. Exit\n"
                + "Enter Option [1,6]";

        final int SHOW_ALL = 1;
        final int FIND_BY_NAME = 2;
        final int ADD_PASSENGER = 3;
        final int DELETE_PASSENGER = 4;
        final int EDIT_PASSENGER = 5;
        final int EXIT = 6;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do
        {
            System.out.println("\n"+MENU_ITEMS);
            try
            {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option)
                {
                    case SHOW_ALL:
                        System.out.println("Display ALL Passengers");
                        passengerStore.displayAllPassengers();
                        break;
                    case FIND_BY_NAME:
                        System.out.println("Find Passenger by Name");
                        System.out.println("Enter passenger name: ");
                        String name = keyboard.nextLine();
                        Passenger p = passengerStore.findPassengerByName(name);
                        if(p==null)
                            System.out.println("No passenger matching the name \"" + name +"\"");
                        else
                            System.out.println("Found passenger: \n" + p.toString());
                        break;
                    case ADD_PASSENGER:
                        System.out.println("Enter name: ");
                        String passengerName = keyboard.nextLine();
                        System.out.println("Enter email: ");
                        String passengerEmail = keyboard.nextLine();
                        System.out.println("enter phone number: ");
                        String passengerPhoneNumber = keyboard.nextLine();
                        System.out.println("enter latitude: ");
                        double passengerLat = keyboard.nextDouble();
                        System.out.println("enter Longititude: ");
                        double passengerLong = keyboard.nextDouble();
                        passengerStore.addPassenger(passengerName, passengerEmail, passengerPhoneNumber,
                                passengerLat, passengerLong);
                        break;
                    case DELETE_PASSENGER:
                        System.out.println("Delete Passenger by Name");
                        System.out.println("Enter passenger Name: ");
                        String bookingName = keyboard.nextLine();
                        passengerStore.deletePassenger(bookingName);
                        break;
                    case EDIT_PASSENGER:
                        passengerStore.editPassengerMenu();
                        break;

                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException |NumberFormatException e)
            {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

    }
    public void displayBookingsMenu() {
        final String MENU_ITEMS = "\n*** BOOKING MENU ***\n"
            + "1. Show all Bookings\n"
            + "2. Find Booking by Id\n"
            + "3. Make Booking\n"
            + "4. Delete Booking\n"
            + "5. The Average Length of Journeys\n"
            + "6. Display Future Bookings\n"
            + "7. Edit Booking\n"
            + "8. Exit\n"
            + "Enter Option [1,8]";

        final int SHOW_ALL = 1;
        final int FIND_BOOKING_BY_ID = 2;
        final int MAKE_BOOKING = 3;
        final int DELETE_BOOKING = 4;
        final int THE_AVERAGE_LENGTH_OF_JOURNEYS = 5;
        final int DISPLAY_FUTURE_BOOKINGS = 6;
        final int EDIT_BOOKING = 7;
        final int EXIT = 8;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do
        {
            System.out.println("\n"+MENU_ITEMS);
            try
            {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option)
                {
                    case SHOW_ALL:
                        System.out.println("Display All Bookings");
                        bookingManager.displayAllBookings();
                        break;
                    case FIND_BOOKING_BY_ID:
                        System.out.println("Find Booking By Name");
                        System.out.println("enter name: ");
                        String name = keyboard.nextLine();
                        bookingManager.findBookingByName(name);
                        break;
                    case MAKE_BOOKING:
                        bookingManager.addNewBooking();
                        break;
                    case DELETE_BOOKING:
                        System.out.println("Delete Booking By ID");
                        System.out.println("Enter Booking ID: ");
                        int bookingId = keyboard.nextInt();
                        bookingManager.deleteBooking(bookingId);
                        bookingManager.displayAllBookings();
                        break;
                    case THE_AVERAGE_LENGTH_OF_JOURNEYS:
                        System.out.println("The Average Length of Journeys");
                        bookingManager.averageLengthOfJourneys();
                        break;
                    case DISPLAY_FUTURE_BOOKINGS:
                        bookingManager.futureBookings();

                        break;
                    case EDIT_BOOKING:
                        bookingManager.editBookingMenu();
                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException |NumberFormatException e)
            {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

    }
}
