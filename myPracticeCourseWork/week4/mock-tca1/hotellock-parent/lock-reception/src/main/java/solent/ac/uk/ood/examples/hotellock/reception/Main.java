/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.hotellock.reception;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import solent.ac.uk.ood.examples.hotellock.model.HotelReceptionService;
import solent.ac.uk.ood.examples.hotellock.model.SecretKeyProvider;
import solent.ac.uk.ood.examples.hotellock.secretkey.SecretKeyProviderImpl;

/**
 *
 * @author 3moulj69
 */
public class Main {
    private HotelReceptionService hrs = new HotelReceptionServiceImpl();
    
    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(); //Creates new date object which is set to the day it is created
        HotelReceptionService hrs = new HotelReceptionServiceImpl();
        SecretKeyProvider skp = new SecretKeyProviderImpl(); // Creation of secret key proivder

        boolean validArguments = true;
        
        hrs.setSecretKeyProvider(skp); // Assignment of secret key provider
        boolean loop = true;
        while(loop){
            //Displays commands to user
            System.out.println("\n==============================\n"
                    + "Please select a command:\n"
                    + "1) Create card\n"
                    + "2) Exit");

            // Create card
            switch(sc.nextLine()){
                case "1":
                    try{
                        System.out.println("\nPlease enter room number: ");
                        String roomNum = sc.nextLine(); //Sets room number
                        System.out.println("\nPlease enter Start Date for card validity (dd/mm/yyyy): ");
                        Date startDate = dateFormatter.parse(sc.nextLine()); //Sets start date
                        System.out.println("\nPlease enter date this card is valid to (dd/mm/yyyy): ");
                        Date endDate = dateFormatter.parse(sc.nextLine()); //Sets end date
                        
                        if(startDate.after(endDate)){
                            throw new IllegalArgumentException("End date is set before start date!"); // Throws exception if card end date is before card start date
                        }
                        
                        System.out.println("\nCard successfully created!"
                                + "\nCard details: ");
                        System.out.println(hrs.createCardCode(roomNum, startDate, endDate)); // Return card code to receptionist
                        
                    }catch (ParseException|IllegalArgumentException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        break;
                    }
                    break;
                case "2":
                    loop = false;
                    break;
            }
        }
    }
}
