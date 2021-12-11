package org.example;

import java.time.LocalDateTime;

public class Email {
    public void emailReminder(int passengerId, int vehicleId, int year, int month, int day, int hour, int minute,
                              double startLatitude, double startLongitude, double endLatitude, double endLongitude,
                              double cost)
    {
        LocalDateTime time = LocalDateTime.of(year, month, day, hour, minute);
        LocationGPS startLocation = new LocationGPS(startLatitude,startLongitude);
        LocationGPS endLocation = new LocationGPS(endLatitude,endLongitude);
        System.out.println("----------------------------------------------------------------");
        System.out.println("Thank you for making a booking with us");
        System.out.println("----------------------------------------------------------------");
        System.out.println("Passenger ID: " + passengerId);
        System.out.println("Vehicle ID: " + vehicleId);
        System.out.println("Time: " + time);
        System.out.println("Start Location: " + startLocation);
        System.out.println("End Location: " + endLocation);
        System.out.println("Cost: " + cost);
        System.out.println("You can change your booking at any time by visiting the edit menu");
        System.out.println("-----------------------------------------------------------------");
    }

}
