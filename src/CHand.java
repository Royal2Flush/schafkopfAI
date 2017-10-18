import java.util.ArrayList;
import java.util.List;

public class CHand {
    private List<CCard> cards;

    public CHand() {
        this.cards = new ArrayList<CCard>();
    }

    public int getSize() {
        return cards.size();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public CCard getCard(int number) {
        return cards.get(number);
    }

    public List<CCard> getCards() {
        return cards;
    }

    public void addCard(int cardID) {
        this.cards.add(new CCard(cardID));
    }

    public void addCard(CCard card) {
        this.cards.add(card);
    }

    public void removeCard(int cardID) {
        for (int i=0;i<this.cards.size();i++) {
            if (this.cards.get(i).getID() == cardID) {
                this.cards.remove(i);
                return;
            }
        }
    }

    public boolean hasColor(int color) {
        for (int i=0;i<this.cards.size();i++) {
            if (this.cards.get(i).getColor() == color) {
                return true;
            }
        }
        return false;
    }
}
