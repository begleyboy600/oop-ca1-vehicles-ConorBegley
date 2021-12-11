package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDateTime;

public class BookingManager
{
    private final ArrayList<Booking> bookingList;
    private PassengerStore passengerStore;
    private VehicleManager vehicleManager;
    private Email email;

    //TODO implement functionality as per specification
    public BookingManager(String fileName, PassengerStore passengerStore, VehicleManager vehicleManager) throws FileNotFoundException {
        this.passengerStore = passengerStore;
        this.vehicleManager = vehicleManager;
        this.bookingList = new ArrayList<>();
        loadBookingsFromFile(fileName);
    }

    public void displayAllBookings() {
        for (Booking b : bookingList)
            System.out.println(b.toString());
    }


    //add booking, find booking. call from App

    public void loadBookingsFromFile(String fileName) throws FileNotFoundException {
        try {
            Scanner sc = new Scanner(new File(fileName));
            sc.useDelimiter("[,\r\n]+");
            while (sc.hasNext()) {
                int bookingId = sc.nextInt();
                int passengerId = sc.nextInt();
                int vehicleId = sc.nextInt();
                int year = sc.nextInt();
                int month = sc.nextInt();
                int day = sc.nextInt();
                int hours = sc.nextInt();
                int mins = sc.nextInt();
                double startLat = sc.nextDouble();
                double startLong = sc.nextDouble();
                double endLat = sc.nextDouble();
                double endLong = sc.nextDouble();
                double cost = sc.nextDouble();

                bookingList.add(new Booking(bookingId, passengerId, vehicleId, year, month, day, hours, mins,
                        startLat, startLong, endLat, endLong, cost));
            }
            sc.close();
        }

        catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
    }

    public Booking findBookingByName(String name)
    {
        for(Booking b : bookingList)
        {
            if(b.equals(name))
            {
                return b;
            }
        }
        return null;
    }
    public boolean addBooking(int passengerId, int vehicleId, int year, int month, int day, int hour, int minute,
                              double startLatitude, double startLongitude, double endLatitude, double endLongitude, double cost) {

        LocalDateTime localDateTime = LocalDateTime.of(year, month, day, hour, minute);
        LocationGPS locationStart = new LocationGPS(startLatitude,startLongitude);
        LocationGPS locationEnd = new LocationGPS(endLatitude,endLongitude);
        Booking b1 = new Booking(passengerId, vehicleId, year, month, day,hour, minute, startLatitude, startLongitude,
                endLatitude, endLongitude, cost);
        boolean found = false;
        for (Booking b : bookingList) {
            if (b.equals(b1)) {
                found = true;
                break;
            }
        }
        if (!found) {
            bookingList.add(b1);
        }
        email.emailReminder(passengerId, vehicleId, year, month, day,hour, minute, startLatitude, startLongitude,
                endLatitude, endLongitude, cost);
        return found;
    }

    public void addNewBooking() {

        Scanner kb = new Scanner(System.in);
        System.out.println("Vehicle Ids");
        vehicleManager.displayAllVehicleId();
        System.out.println("Passenger Ids");
        passengerStore.displayAllPassengerId();
        try {
            System.out.println("Enter Passenger ID");
            int passengerId = kb.nextInt();
            System.out.println("Enter Vehicle ID");
            int vehicleId = kb.nextInt();
            System.out.println("Enter Booking Year");
            int year = kb.nextInt();
            System.out.println("Enter Booking Month");
            int month = kb.nextInt();
            System.out.println("Enter Booking Day");
            int day = kb.nextInt();
            System.out.println("Enter Booking Hour");
            int hour = kb.nextInt();
            System.out.println("Enter Booking Minute");
            int minute = kb.nextInt();
            System.out.println("Enter Start Latitude");
            double latStart = kb.nextDouble();
            System.out.println("Enter Start Longitude");
            double longStart = kb.nextDouble();
            System.out.println("Enter End Latitude");
            double latEnd = kb.nextDouble();
            System.out.println("Enter End Longitude");
            double longEnd = kb.nextDouble();
            System.out.println("Enter Cost");
            double cost = kb.nextDouble();

            if (passengerStore.findPassengerById(passengerId) == null) {
                System.out.println("Passenger " + passengerId + " was not found");
            }

            else if (vehicleManager.findVehicleById(vehicleId) == null) {
                System.out.println("Vehicle " + vehicleId + " was not found");
            }

            else {
                boolean found = addBooking(passengerId, vehicleId, year, month, day, hour, minute, latStart, longStart, latEnd, longEnd, cost);
                if (!found) {
                    System.out.println("Booking was added");
                } else {
                    System.out.println("Booking already exists");
                }
            }

        } catch (InputMismatchException | NumberFormatException e) {
            System.out.print("Invalid option - please enter valid details");
        }
    }

    public void averageLengthOfJourneys()
    {
        double avg;
        double arrLength = 0.0;
        double total = 0.0;
        for (Booking b : this.bookingList) {
            total = b.getCost() + total;
            arrLength += 1.0;
        }
        avg = total / arrLength;
        System.out.println("average: " + avg);
    }

    public void futureBookings()
    {
        LocalDateTime now = LocalDateTime.now();
        for(Booking b : this.bookingList) {
            if (b.getBookingDateTime().isAfter(now)) {
                System.out.println(b);
            }
        }
    }
    public void deleteBooking(int bookingId)
    {
        for(Booking b: bookingList)
        {
            if(b.getBookingId() == bookingId)
            {
               bookingList.remove(b);
               break;
            }
        }
    }
    public Booking findBookingById(int BId)
    {
        for(Booking b: bookingList)
        {
            if(b.getBookingId() == BId)
            {
                return b;
            }
        }
        return null;
    }
    public void editBookingMenu()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Edit Booking Menu");
        displayAllBookings();
        System.out.println("Enter Booking Id That You Want To Edit: ");
        int BId = keyboard.nextInt();
        Booking b = findBookingById(BId);
        final String EDIT_MENU_ITEMS = "\n*** EDIT BOOKING MENU ***\n"
                + "1. Edit Booking Passenger ID\n"
                + "2. Edit Booking Vehicle ID\n"
                + "3. Edit Booking Year\n"
                + "4. Edit Booking Month\n"
                + "5. Edit Booking Day\n"
                + "6. Edit Booking Hour\n"
                + "7. Edit Booking Minutes\n"
                + "8. Edit Booking Start Latitude\n"
                + "9. Edit Booking End Longitude\n"
                + "10. Edit Booking Start Latitude\n"
                + "11. Edit Booking End Longitude\n"
                + "12. Edit Booking Cost\n"
                + "13. Exit\n"
                + "Enter Option [1,13]";

        final int EDIT_BOOKING_PASSENGER_ID = 1;
        final int EDIT_BOOKING_VEHICLE_ID = 2;
        final int EDIT_BOOKING_YEAR = 3;
        final int EDIT_BOOKING_MONTH = 4;
        final int EDIT_BOOKING_DAY = 5;
        final int EDIT_BOOKING_HOUR = 6;
        final int EDIT_BOOKING_MINUTE = 7;
        final int EDIT_BOOKING_START_LATITUDE = 8;
        final int EDIT_BOOKING_START_LONGITUDE = 9;
        final int EDIT_BOOKING_END_LATITUDE = 10;
        final int EDIT_BOOKING_END_LONGITUDE = 11;
        final int EDIT_BOOKING_COST = 12;
        final int EXIT_BOOKING_MENU = 13;

        int opt = 0;
        do
        {
            System.out.println("\n"+EDIT_MENU_ITEMS);
            try
            {
                String usersInput = keyboard.nextLine();
                opt = Integer.parseInt(usersInput);
                int year = 0;
                int month = 0;
                int day = 0;
                int hour = 0;
                int minute = 0;
                double startLat = 0.0;
                double startLong = 0.0;
                double endLat = 0.0;
                double endLong = 0.0;
                switch (opt)
                {
                    case EDIT_BOOKING_PASSENGER_ID:
                        System.out.println("Enter Passenger ID: ");
                        int pId = keyboard.nextInt();
                        b.setPassengerId(pId);
                        break;
                    case EDIT_BOOKING_VEHICLE_ID:
                        System.out.println("Enter Vehicle ID: ");
                        int vId = keyboard.nextInt();
                        b.setVehicleId(vId);
                        break;
                    case EDIT_BOOKING_YEAR:
                        System.out.println("Enter Year: ");
                        year = keyboard.nextInt();
                        break;
                    case EDIT_BOOKING_MONTH:
                        System.out.println("Enter Month: ");
                        month = keyboard.nextInt();
                        break;
                    case EDIT_BOOKING_DAY:
                        System.out.println("Enter Day: ");
                        day = keyboard.nextInt();
                        break;
                    case EDIT_BOOKING_HOUR:
                        System.out.println("Enter Hour: ");
                        hour = keyboard.nextInt();
                        break;
                    case EDIT_BOOKING_MINUTE:
                        System.out.println("Enter Minute: ");
                        minute = keyboard.nextInt();
                        break;
                    case EDIT_BOOKING_START_LATITUDE:
                        System.out.println("Enter Start Latitude: ");
                        startLat = keyboard.nextDouble();
                        break;
                    case EDIT_BOOKING_START_LONGITUDE:
                        System.out.println("Enter Start Longitude: ");
                        startLong = keyboard.nextDouble();
                        break;
                    case EDIT_BOOKING_END_LATITUDE:
                        System.out.println("Enter End Latitude: ");
                        endLat = keyboard.nextDouble();
                        break;
                    case EDIT_BOOKING_END_LONGITUDE:
                        System.out.println("Enter End Longitude: ");
                        endLong = keyboard.nextDouble();
                        break;
                    case EDIT_BOOKING_COST:
                        System.out.println("Enter Cost");
                        double cost = keyboard.nextDouble();
                        b.setCost(cost);
                        break;
                }
                LocalDateTime time = LocalDateTime.of(year,month,day,hour,minute);
                b.setBookingDateTime(time);
                LocationGPS startLocation = new LocationGPS(startLat, startLong);
                b.setStartLocation(startLocation);
                LocationGPS endLocation = new LocationGPS(endLat, endLong);
                b.setEndLocation(endLocation);
                System.out.println(b);
            }
            catch (InputMismatchException |NumberFormatException e)
            {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (opt != EXIT_BOOKING_MENU);
    }

}









