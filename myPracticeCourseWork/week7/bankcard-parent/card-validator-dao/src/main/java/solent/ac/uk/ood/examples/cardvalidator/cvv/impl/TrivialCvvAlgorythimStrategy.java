/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.cardvalidator.cvv.impl;

import solent.ac.uk.ood.examples.cardvalidator.model.CreditCard;
import solent.ac.uk.ood.examples.cardvalidator.model.CvvAlgorythimStrategy;

/**
 *
 * @author cgallen
 */
public class TrivialCvvAlgorythimStrategy implements CvvAlgorythimStrategy {

    private String CVV = "000";
    private String BankCompany = "0";
    
    @Override
    public CreditCard addCvv(CreditCard card) {
                
        //CVV algorythm goes here
        switch(BankCompany){
            case "513388": // TSB Bank (Gets first three digits of the cards number and multiplies it with the first three digits of the iin)
                CVV = Integer.toString(Integer.parseInt(card.getCardnumber().substring(0,3)) * Integer.parseInt(BankCompany.substring(0,3)));
                break;
            case "428586": // Visa Natwest (Turns last letter of card holders name into ascii and multiplies it by the cards first two digits)
                CVV = Integer.toString(((int)card.getName().charAt(card.getName().length()-1)) * Integer.parseInt(card.getEndDate().substring(0,2)));
                break;
            case "424519": // Bank of ireland (Takes first character of card holders name and multiplies it with iin)
                CVV = Integer.toString(((int)card.getName().charAt(0)) * Integer.parseInt(BankCompany));
                break;
            case "512569": // Bank of ireland (Takes first character of card holders name in ascii and multiplies it with its expiration date)
                CVV = Integer.toString(((int)card.getName().charAt(0)) * Integer.parseInt(card.getEndDate()));
                break;
            case "377064": // American express (Multiplies ascii values of both the first letter of the card holders name and the last)
                CVV = Integer.toString(((int)card.getName().charAt(0)) * (int)card.getName().charAt(card.getName().length() - 1));
                break;
        }
        CVV = CVV.substring(0,3);
        
                
        card.setCvv(CVV);
        return card;
    }

    @Override
    public boolean checkCvv(CreditCard card) {
        return card.getCvv().equals(CVV);
    }
    
    public CvvAlgorythimStrategy setAlgorythim(String iin){
        this.BankCompany = iin;
        return this;
    }
}
