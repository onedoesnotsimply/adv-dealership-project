package com.yearup.dealership;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    static Scanner scanner = new Scanner(System.in);
    private Dealership dealership;

    public UserInterface(){
    }

    public void display() {
        init();
        while(true) {
            System.out.println("Menu Screen");
            System.out.println("1) Search by Price");
            System.out.println("2) Search by Make and Model");
            System.out.println("3) Search by Year");
            System.out.println("4) Search by Color");
            System.out.println("5) Search by Mileage");
            System.out.println("6) Search by Vehicle Type");
            System.out.println("7) Display All Vehicles");
            System.out.println("8) Add a Vehicle");
            System.out.println("9) Remove a Vehicle");
            System.out.println("0) Exit");

            try { // Enforce input type
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice){
                    case 1:
                        processGetByPriceRequest();
                        break;
                    case 2:
                        processGetByMakeModelRequest();
                        break;
                    case 3:
                        processGetByYearRequest();
                        break;
                    case 4:
                        processGetByColorRequest();
                        break;
                    case 5:
                        processGetByMileageRequest();
                        break;
                    case 6:
                        processGetByVehicleType();
                        break;
                    case 7:
                        processGetAllVehiclesRequest();
                        break;
                    case 8:
                        processAddVehicleRequest();
                        break;
                    case 9:
                        processRemoveVehicleRequest();
                        break;
                    case 0:
                        System.exit(0);
                    default: // Enforce input range
                        System.out.println("Invalid input\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input\nPlease enter a number\n");
                scanner.nextLine(); // Prevent an infinite loop
            }
        }
    }

    public void processGetByPriceRequest() {
        // Prompt for price range
        System.out.print("Enter the minimum price : $");
        double min = scanner.nextDouble();
        System.out.print("Enter the maximum price : $");
        double max = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline

        // Call the search method
        displayVehicles(dealership.getVehiclesByPrice(min, max));
    }

    public void processGetByMakeModelRequest() {
        // Prompt
        System.out.print("Enter the make : ");
        String make = scanner.nextLine();
        System.out.print("Enter the model : ");
        String model = scanner.nextLine();

        // Display
        displayVehicles(dealership.getVehiclesByMakeModel(make,model));
    }

    public void processGetByYearRequest() {
        // Prompt
        System.out.print("Enter the minimum year : ");
        int min = scanner.nextInt();
        System.out.print("Enter the maximum year : ");
        int max = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        // Display
        displayVehicles(dealership.getVehiclesByYear(min,max));
    }

    public void processGetByColorRequest() {
        // Prompt
        System.out.print("Enter a color : ");
        String color = scanner.nextLine();

        // Display
        displayVehicles(dealership.getVehiclesByColor(color));
    }

    public void processGetByMileageRequest() {
        // Prompt
        System.out.print("Enter minimum mileage : ");
        int min = scanner.nextInt();
        System.out.print("Enter maximum mileage : ");
        int max = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        // Display
        displayVehicles(dealership.getVehiclesByMileage(min,max));
    }

    public void processGetByVehicleType() {
        // Prompt
        System.out.print("Enter the vehicle type : ");
        String vehicleType = scanner.nextLine();

        // Display
        displayVehicles(dealership.getVehiclesByType(vehicleType));
    }

    public void processGetAllVehiclesRequest() {
        // Display vehicles in the inventory
        displayVehicles(dealership.getAllVehicles());
    }

    public void processAddVehicleRequest() {
        // Prompt
        System.out.println("Please enter the following information");

        System.out.print("Vin : ");
        int vin = scanner.nextInt();
        System.out.print("Year : ");
        int year = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        System.out.print("Make : ");
        String make = scanner.nextLine();
        System.out.print("Model : ");
        String model = scanner.nextLine();
        System.out.print("Vehicle Type : ");
        String vehicleType = scanner.nextLine();
        System.out.print("Color : ");
        String color = scanner.nextLine();

        System.out.print("Odometer reading : ");
        int odometer = scanner.nextInt();
        System.out.print("Price : ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline

        // Create the new vehicle object
        Vehicle vehicle = new Vehicle(vin,year,make,model,vehicleType,color,odometer,price);

        // Add the vehicle to the dealership
        dealership.addVehicle(vehicle);

        // Print out a confirmation message
        System.out.println("Vehicle added successfully");

        // Save the dealership changes
        DealershipFileManager.saveDealership(dealership);
    }

    public void processRemoveVehicleRequest() {
        // Prompt for the vin of the vehicle to remove
        System.out.print("Enter the VIN of the vehicle you want to remove : ");
        int vin = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        // Loop through the inventory for the vehicle with the input VIN number
        Vehicle toRemove = null; // Initialize Vehicle
        for (Vehicle vehicle : dealership.getAllVehicles()){
            if (vehicle.getVin() == vin){ // If the vehicle exists
                // Change the value of toRemove to the vehicle
                toRemove = vehicle;
            }
        }
        if (toRemove == null) { // If the vehicle wasn't in the inventory
            System.out.println("Invalid input\nPlease enter a valid VIN"); // Print an error message
        } else {
            // Remove the vehicle
            dealership.removeVehicle(toRemove);
            System.out.println("Vehicle removed successfully"); // Print out a success message

            // Save the dealership changes
            DealershipFileManager.saveDealership(dealership);
        }
    }

    // Private methods
    private void init() {
        this.dealership = DealershipFileManager.getDealership();
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }
}
