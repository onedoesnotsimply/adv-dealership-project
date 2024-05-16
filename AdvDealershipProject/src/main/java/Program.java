import com.yearup.dealership.*;

import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        //UserInterface ui = new UserInterface();

        //ui.display();



        Vehicle vehicle = new Vehicle(55555, 2015,"Ford","Explorer", "SUV","Red", 150000, 9000.0);

        Contract salesContract = new SalesContract("2020","Emily Trifone","anEmail",vehicle, 5,100,295,false);
        Contract leaseContract = new LeaseContract("date","a name","an email", vehicle, 50,7);

        //System.out.println("Sale");
        //System.out.println(salesContract.getMonthlyPayment());
        //System.out.println(salesContract.getTotalPrice());
        //System.out.println(salesContract.getVehicleSold());

        //System.out.println("Lease");
        //System.out.println(leaseContract.getTotalPrice());
        //System.out.println(leaseContract.getMonthlyPayment());
        //System.out.println(leaseContract.getVehicleSold());

        ContractDataManager.saveContract(salesContract);

    }
}