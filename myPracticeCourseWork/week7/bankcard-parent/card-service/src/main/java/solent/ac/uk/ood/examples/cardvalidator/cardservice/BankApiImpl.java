/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.cardvalidator.cardservice;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solent.ac.uk.ood.examples.cardvalidator.model.Account;
import solent.ac.uk.ood.examples.cardvalidator.model.AccountDAO;
import solent.ac.uk.ood.examples.cardvalidator.model.BankApi;
import solent.ac.uk.ood.examples.cardvalidator.model.CardFactoryDAO;
import solent.ac.uk.ood.examples.cardvalidator.model.CreditCard;

/**
 *
 * @author cgallen
 */
public class BankApiImpl implements BankApi {

    private static final Logger LOG = LoggerFactory.getLogger(BankApiImpl.class);
    
    private static final Logger TRANSACTIONLOG = LoggerFactory.getLogger("transaction-log");

    private final CardFactoryDAO cardFactoryDao;

    private final AccountDAO accountDAO;
    
    private int issueNo = 10;

    public BankApiImpl(CardFactoryDAO cardFactoryDao, AccountDAO accountDAO) {
        this.cardFactoryDao = cardFactoryDao;
        this.accountDAO = accountDAO;
    }

    @Override
    public Account createAccount(String issuerIdentificationNumber, String name) {
        Account account = accountDAO.createAccount(issuerIdentificationNumber, name);
        return account;
    }

    @Override
    public boolean deleteAccount(String issuerIdentificationNumber, String individualAccountIdentifier) {
        return accountDAO.deleteAccount(issuerIdentificationNumber, individualAccountIdentifier);
    }

    @Override
    public Account retrieveAccount(String issuerIdentificationNumber, String individualAccountIdentifier) {
        return accountDAO.retrieveAccount(issuerIdentificationNumber, individualAccountIdentifier);
    }

    @Override
    public Account updateAccount(Account account) {
        Account _acount = accountDAO.updateAccount(account);
        return _acount;
    }

    @Override
    public List<Account> getAccountsForIssuer(String issuerIdentificationNumber) {
        return accountDAO.getAccountsForIssuer(issuerIdentificationNumber);
    }

    @Override
    public List<String> getSupportedIssuerNames() {
        return cardFactoryDao.getSupportedIssuerNames();
    }

    @Override
    public String getIssuerIdentifierNumberForName(String name) {
        return cardFactoryDao.getIssuerIdentifierNumberForName(name);
    }

    @Override
    public String getNameForIssuerIdentificationNumber(String issuerIdentificationNumber) {
        return cardFactoryDao.getNameForIssuerIdentificationNumber(issuerIdentificationNumber);
    }

    @Override
    public CreditCard createNewCreditCard(Account account) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 6);
        String month = String.valueOf(c.get(Calendar.MONTH));
        String year = String.valueOf(c.get(Calendar.YEAR)).substring(2);
        String expirationDate = month + year;
        return cardFactoryDao.getCreditCardFactoryAndValidator(account.getIssuerIdentificationNumber()).createCreditCard(account.getIndividualAccountIdentifier(), account.getName(),expirationDate , String.valueOf(issueNo));
    }

}
