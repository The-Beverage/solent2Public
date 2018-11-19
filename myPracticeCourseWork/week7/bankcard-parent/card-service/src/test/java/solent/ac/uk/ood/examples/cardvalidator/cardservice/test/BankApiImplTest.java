/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.cardvalidator.cardservice.test;

import org.junit.Test;
import static org.junit.Assert.*;
import solent.ac.uk.ood.examples.cardvalidator.accountdao.jaxbimpl.AccountDAOJaxbImpl;
import solent.ac.uk.ood.examples.cardvalidator.cardservice.ServiceObjectFactoryImpl;
import solent.ac.uk.ood.examples.cardvalidator.impl.CardFactoryDAOImpl;
import solent.ac.uk.ood.examples.cardvalidator.model.Account;
import solent.ac.uk.ood.examples.cardvalidator.model.BankApi;
import solent.ac.uk.ood.examples.cardvalidator.model.ServiceObjectFactory;

/**
 *
 * @author cgallen
 */
public class BankApiImplTest {
    
    @Test
    public void testBankApi() {
        // Using service object factory to create and set instances
        ServiceObjectFactoryImpl.setAccountDAO(new AccountDAOJaxbImpl("U:\\Work\\ObjectOrientated2ndYear\\solent2Public\\myPracticeCourseWork\\week7\\bankcard-parent\\card-account-dao-jaxb\\target\\testAccountDaoData.xml"));
        ServiceObjectFactoryImpl.setCardFactoryDao(new CardFactoryDAOImpl());
        ServiceObjectFactory sof = new ServiceObjectFactoryImpl();
        BankApi bank = sof.getBankApi();
        
        //System.out.println(bank.createAccount("428586", "First Account"));
        System.out.println(bank.getAccountsForIssuer("428586"));
        System.out.println(bank.deleteAccount("428586", "00000007"));
        
        Account acc = bank.retrieveAccount("428586", "00000004");
        System.out.println(bank.createNewCreditCard(acc));
        System.out.println(bank.getAccountsForIssuer("428586"));
        System.out.println(bank.getSupportedIssuerNames());
    }
}
