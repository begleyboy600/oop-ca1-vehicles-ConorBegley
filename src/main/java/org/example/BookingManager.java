package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;

public class BookingManager
{
    private final ArrayList<Booking> bookingList;
    private PassengerStore passengerStore;
    private VehicleManager vehicleManager;

    // Constructor
    public BookingManager() {
        this.bookingList = new ArrayList<>();
    }

    //TODO implement functionality as per specification
    public BookingManager(String fileName) throws FileNotFoundException {
        this.bookingList = new ArrayList<>();
        loadBookingsFromFile(fileName);
    }
    public void displayAllBookings() {
        for (Booking v : bookingList)
            System.out.println(v.toString());
    }

    //add booking, find booking. call from App

    public void loadBookingsFromFile(String fileName) throws FileNotFoundException {
        try {
            Scanner sc = new Scanner(new File(fileName));
            sc.useDelimiter("[,\r\n]+");
            while (sc.hasNext()) {
                int id = sc.nextInt();
                int passengerId = sc.nextInt();
                int vehicleId = sc.nextInt();
                int year = sc.nextInt();
                int month = sc.nextInt();
                int day = sc.nextInt();
                int hours = sc.nextInt();
                int mins = sc.nextInt();
                double startLocationLat = sc.nextDouble();
                double startLocationLong = sc.nextDouble();
                double endLocationLat = sc.nextDouble();
                double endLocationLong = sc.nextDouble();



                bookingList.add(new Booking(id, passengerId, vehicleId, year, month, day, hours, mins,
                        startLocationLat, startLocationLong, endLocationLat, endLocationLong));
            }
            sc.close();

        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

