package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
}

