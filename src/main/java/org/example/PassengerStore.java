package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PassengerStore {

    private final ArrayList<Passenger> passengerList;

    public PassengerStore(String fileName) {
        this.passengerList = new ArrayList<>();
        loadPassengerDataFromFile(fileName);
    }

    public List<Passenger> getAllPassengers() {
        return this.passengerList;
    }

    public void displayAllPassengers() {
        for (Passenger p : this.passengerList) {
            System.out.println(p.toString());
        }
    }
    public Passenger findPassengerByName(String name){

        for (Passenger p : this.passengerList) {
            if(p.getName().equals(name))
            {
                return p;
            }
        }
        return null;
    }

    private void loadPassengerDataFromFile(String filename) {

        try {
            Scanner sc = new Scanner(new File(filename));
//           Delimiter: set the delimiter to be a comma character ","
//                    or a carriage-return '\r', or a newline '\n'
            sc.useDelimiter("[,\r\n]+");

            while (sc.hasNext()) {
                int id = sc.nextInt();
                String name = sc.next();
                String email = sc.next();
                String phone = sc.next();
                double latitude = sc.nextDouble();
                double longitude = sc.nextDouble();

                // construct a Passenger object and add it to the passenger list
                passengerList.add(new Passenger(id, name, email, phone, latitude, longitude));
            }
            sc.close();

        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
    }

    // TODO - see functional spec for details of code to add
    public void addPassenger(String name, String email, String phone, double latitude, double longitude)
    {
        boolean found = false;
        Passenger passenger = new Passenger(name, email, phone, latitude, longitude);
        for (Passenger p : passengerList)
        {
            if(p.equals(passenger))
            {
                found = true;
                break;
            }
        }
        if(!found)
        {
            passengerList.add(passenger);
        }
    }

    public void deletePassenger()
    {
        Scanner keyboard = new Scanner(System.in);
        displayAllPassengerId();
        System.out.println("enter passenger Id that you want to delete");
        int pId = keyboard.nextInt();
        passengerList.removeIf(p -> pId == p.getId());
        displayAllPassengers();
    }
    public void displayAllPassengerId()
    {
        ArrayList<Integer> pIds= new ArrayList<>();
        for(Passenger p: passengerList)
        {
            System.out.println(p.getId());
        }
        for(int id : pIds)
        {
            System.out.println(id);
        }
    }
    public Passenger findPassengerById(int pId)
    {
        for(Passenger p: passengerList)
        {
            if(p.getId() == pId)
            {
                return p;
            }
        }
        return null;
    }
    public void editPassengerMenu()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("edit menu");
        displayAllPassengerId();
        System.out.println("enter passenger Id that you want to edit");
        int pId = keyboard.nextInt();
        Passenger p = findPassengerById(pId);
        final String EDIT_MENU_ITEMS = "\n*** EDIT PASSENGER MENU ***\n"
                + "1. Edit Name\n"
                + "2. Edit Email\n"
                + "3. Edit Phone\n"
                + "4. Edit Latitude\n"
                + "5. Exit\n"
                + "Enter Option [1,5]";

        final int EDIT_NAME = 1;
        final int EDIT_EMAIL = 2;
        final int EDIT_PHONE = 3;
        final int EDIT_LOCATION = 4;
        final int EXIT_MENU = 5;

        int opt = 0;
        do
        {
            System.out.println("\n"+EDIT_MENU_ITEMS);
            try
            {
                String usersInput = keyboard.nextLine();
                opt = Integer.parseInt(usersInput);
                switch (opt)
                {
                    case EDIT_NAME:
                        System.out.println("Enter Name: ");
                        String newName = keyboard.nextLine();
                        p.setName(newName);
                        break;
                    case EDIT_EMAIL:
                        System.out.println("Enter Email: ");
                        String newEmail = keyboard.nextLine();
                        p.setEmail(newEmail);
                        break;
                    case EDIT_PHONE:
                        System.out.println("Enter Phone: ");
                        String newPhone = keyboard.nextLine();
                        p.setPhone(newPhone);
                        break;
                    case EDIT_LOCATION:
                        System.out.println("Enter Latitude: ");
                        double newLatitude = keyboard.nextDouble();
                        System.out.println("Enter Longitude: ");
                        double newLongitude = keyboard.nextDouble();
                        p.setLocation(newLatitude, newLongitude);
                        break;

                }
                System.out.println("passenger edited: " + p);
            }
            catch (InputMismatchException |NumberFormatException e)
            {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (opt != EXIT_MENU);

    }



} // end class
