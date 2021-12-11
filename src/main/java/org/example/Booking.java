package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.Objects;

class Booking
{
    private IdGenerator idGenerator = IdGenerator.getInstance("next-id-store.txt");  // get access to the id Generator

    private int bookingId;
    private int passengerId;
    private int vehicleId;
    private LocalDateTime bookingDateTime;
    private LocationGPS startLocation;
    private LocationGPS endLocation;

    private double cost;  //Calculated at booking time

    //TODO - see specification

    public Booking(int year, int month, int day, int hour, int mins, double startLat,
            double startLong, double endLat, double endLong, double cost)
    {
        this.bookingId = idGenerator.getNextId();
        this.passengerId = idGenerator.getNextId();
        this.vehicleId = idGenerator.getNextId();
        this.bookingDateTime = LocalDateTime.of(year, month, day, hour, mins);
        this.startLocation = new LocationGPS(startLat, startLong);
        this.endLocation = new LocationGPS(endLat, endLong);
        this.cost = cost;
    }
    public Booking(int bookingId, int passengerId, int vehicleId, int year, int month, int day, int hours, int mins, double startLat,
                   double startLong, double endLat, double endLong, double cost)
    {
        this.bookingId = bookingId;
        this.passengerId = passengerId;
        this.vehicleId = vehicleId;
        this.bookingDateTime = LocalDateTime.of(year, month, day, hours, mins);
        this.startLocation = new LocationGPS(startLat, startLong);
        this.endLocation = new LocationGPS(endLat, endLong);
        this.cost = cost;
    }

    public Booking(int passengerId, int vehicleId, int year, int month, int day, int hours, int mins, double startLat,
                   double startLong, double endLat, double endLong, double cost)
    {
        this.bookingId = idGenerator.getNextId();
        this.passengerId = idGenerator.getNextId();
        this.vehicleId = idGenerator.getNextId();
        this.bookingDateTime = LocalDateTime.of(year, month, day, hours, mins);
        this.startLocation = new LocationGPS(startLat, startLong);
        this.endLocation = new LocationGPS(endLat, endLong);
        this.cost = cost;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public void setBookingDateTime(LocalDateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }

    public LocationGPS getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(LocationGPS startLocation) {
        this.startLocation = startLocation;
    }

    public LocationGPS getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(LocationGPS endLocation) {
        this.endLocation = endLocation;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return bookingId == booking.bookingId && passengerId == booking.passengerId && vehicleId == booking.vehicleId && Double.compare(booking.cost, cost) == 0 && Objects.equals(idGenerator, booking.idGenerator) && Objects.equals(bookingDateTime, booking.bookingDateTime) && Objects.equals(startLocation, booking.startLocation) && Objects.equals(endLocation, booking.endLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGenerator, bookingId, passengerId, vehicleId, bookingDateTime, startLocation, endLocation, cost);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", passengerId=" + passengerId +
                ", vehicleId=" + vehicleId +
                ", bookingDateTime=" + bookingDateTime +
                ", startLocation=" + startLocation +
                ", endLocation=" + endLocation +
                ", cost=" + cost +
                '}';
    }
}
