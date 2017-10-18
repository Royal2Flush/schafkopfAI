import java.util.ArrayList;
import java.util.List;

public class CTrick {
    private List<CCard> cards;
    private int color;
    private int firstPlayer;
    private int winner = -1;
    private int value = -1;

    public CTrick(int firstPlayer) {
        this.cards = new ArrayList<CCard>();
        this.firstPlayer = firstPlayer;
    }

    public void addCard(int CID) {
        this.cards.add(new CCard(CID));
    }

    public void addCard(CCard card) {
        if (cards.isEmpty()) {
            this.color = card.getColor();
        }
        this.cards.add(card);
    }

    public int getColor() {
        return this.color;
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int getWinner() {
        if (this.winner<0) { // not yet calculated
            this.winner = determineWinner();
        }
        return this.winner;
    }

    public int getValue() {
        if (this.value<0) { // not yet calculated
            this.value = determineValue();
        }
        return this.value;
    }

    private int determineWinner() {
        int trickColor = cards.get(0).getColor();
        int highestRank = -1;
        int winner = -1;

        int it=-1;
        for (CCard card : cards) {
            it++;
            int currentRank = -1;
            if (card.getColor() == trickColor) {
                currentRank = card.getNumber();
            }
            else if (card.getColor() == 4) { // Trump
                currentRank = 8+card.getNumber();
            }

            if (currentRank>highestRank){
                highestRank = currentRank;
                winner = it;
            }
        }

        return mapCardToPlayer(winner);
    }

    private int determineValue(){
        int value = 0;
        for (CCard card : cards){
            value += card.getValue();
        }
        return value;
    }

    private int mapCardToPlayer (int position) {
        return (this.firstPlayer + position)%4;
    }

    private int mapPlayerToCard (int player) {
        return (this.firstPlayer - player + 4)%4; // the +4 is because mod only takes positive numbers
    }
}
