package com.yearup.dealership;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractDataManager {

    public static void saveContract(Contract contract) {
        try{
            // Create our BufferedWriter
            BufferedWriter bfr = new BufferedWriter(new FileWriter("contracts.csv",true));

            // Initialize file string
            String inputString = String.format("|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f",
                    contract.getDate(),contract.getName(),contract.getEmail(), // Basic contract data
                    contract.getVehicleSold().getVin(),contract.getVehicleSold().getYear(), // Vehicle data
                    contract.getVehicleSold().getMake(),contract.getVehicleSold().getModel(), // Vehicle data
                    contract.getVehicleSold().getVehicleType(),contract.getVehicleSold().getColor(), // Vehicle data
                    contract.getVehicleSold().getOdometer(),contract.getVehicleSold().getPrice()); // Vehicle data);

            // Check what type of contract the user is saving
            if (contract instanceof SalesContract){

                String wasFinanced;
                if (((SalesContract) contract).isFinanced()){
                    wasFinanced = "YES";
                } else {
                    wasFinanced = "NO";
                }
                String salesInputString = String.format("|%.2f|%.2f|%.2f|%.2f|%s|",
                        ((SalesContract) contract).getSalesTaxAmount(),((SalesContract) contract).getRecordingFee(), // Sales contract data
                        ((SalesContract) contract).getProcessingFee(), contract.getTotalPrice(), wasFinanced);// Sales contract data


                bfr.write("SALE"+inputString+salesInputString+contract.getMonthlyPayment()+"\n");

            } else if (contract instanceof LeaseContract) { // A LeaseContract
                String leaseInputString = String.format("|%.2f|%.2f|%.2f|%.2f",
                        ((LeaseContract) contract).getExpectedEndingVal(),((LeaseContract) contract).getLeaseFee(),
                        contract.getTotalPrice(),contract.getMonthlyPayment());

                bfr.write("LEASE"+inputString+leaseInputString+"\n");

            }
            bfr.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
