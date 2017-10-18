import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CPlayer {
    private boolean hasAI;
    private CHand hand;
    private int position;
    private int team = 0;
    private String name;
    private Random rng;

    public CPlayer() {
        this.hand = new CHand();
        this.name = "";
        this.rng = new Random();
    }

    public void setPosition (int pos){
        this.position = pos;
    }

    public void setAI(boolean AI) {
        this.hasAI = AI;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public CHand getHand() {
        return hand;
    }

    public void setTeam(int team){
        this.team = team;
    }

    public int getTeam (){
        return this.team;
    }

    public void deal(CCard card) {
        hand.addCard(card);
        System.out.println(this.name + " received " + card.echoName());
    }

    public CCard playCard(CTrick currentTrick) {
        //int n = rng.nextInt(hand.getSize());
        //CCard card = hand.getCard(n);

        CHand legalCards = new CHand();

        if (currentTrick.isEmpty()) {
            legalCards = hand;
        }
        else {
            int trickColor = currentTrick.getColor();
            for (CCard card : hand.getCards()) {
                if (card.getColor() == trickColor) {
                    legalCards.addCard(card);
                }
            }
            if (legalCards.isEmpty()) {
                legalCards = hand;
            }
        }

        CCard chosenCard = legalCards.getCard(rng.nextInt(legalCards.getSize()));

        String logString = this.name + " has ";
        for (CCard card : legalCards.getCards()) {
            logString += card.echoName() + " ";
        }
        logString += "and plays " + chosenCard.echoName();
        System.out.println(logString);

        hand.removeCard(chosenCard.getID());
        return chosenCard;
    }
}
