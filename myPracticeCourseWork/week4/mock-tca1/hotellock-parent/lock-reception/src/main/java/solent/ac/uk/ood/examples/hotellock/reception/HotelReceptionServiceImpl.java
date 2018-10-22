/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.hotellock.reception;

import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import solent.ac.uk.ood.examples.hotellock.model.CardKey;
import solent.ac.uk.ood.examples.hotellock.model.HotelReceptionService;
import solent.ac.uk.ood.examples.hotellock.model.SecretKeyProvider;

/**
 *
 * @author cgallen
 */
public class HotelReceptionServiceImpl implements HotelReceptionService {

    public static final Logger LOG = LogManager.getLogger(HotelReceptionServiceImpl.class);
    private SecretKeyProvider secretKeyProvider;
    private int issueNumber = 0;
    private CardKey cards[];
    
    @Override
    public String createCardCode(String roomNumber, Date startDate, Date endDate) {
        CardKey card = new CardKey();
        card.setRoomNumber(roomNumber);
        card.setStartDate(startDate);
        card.setEndDate(endDate);
        card.setIssueNumber(issueNumber);
        issueNumber += 1;
        
        LOG.info("New card issued. IssuNo: " + card.getIssueNumber());
        cards[cards.length] = card;
        
        return secretKeyProvider.encodeCard(card);
    }

    @Override
    public CardKey readCard(String cardCode) {
        for (CardKey card : cards) {
            if (card.getIssueNumber() == Integer.parseInt(cardCode)) {
                return card;
            }
        }
        return null;
    }

    @Override
    public void setSecretKeyProvider(SecretKeyProvider secretKeyProvider) {
        this.secretKeyProvider= secretKeyProvider;
    }
    
}
