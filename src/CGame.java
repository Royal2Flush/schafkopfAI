import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CGame {
    private List<CPlayer> players;
    private List<CTrick> tricks;
    private List<CCard> deck;
    private Random rng;
    private int gameType; // 0..3 Sauspiel, 4..7 Solo, 8 Wenz
    private int caller;

    public CGame() {
        this.players = new ArrayList<CPlayer>();
        initializePlayers();
        this.tricks = new ArrayList<CTrick>();
        this.deck = new ArrayList<CCard>();
        this.rng = new Random();
    }

    public void prepareGame() {
        // Create sorted deck of IDs
        List<CCard> sortedDeck = new ArrayList<CCard>();
        for (int i=0; i<32; i++)
        {
            sortedDeck.add(new CCard(i));
        }

        // Pull IDs from sorted deck and make shuffled deck
        for (int i=0; i<32; i++)
        {
            int card = rng.nextInt(32-i);
            this.deck.add(sortedDeck.get(card));
            sortedDeck.remove(card);
        }
    }

    public void deal4() {
        for (CPlayer player : players) {
            for (int j=0;j<4;j++) {
                player.deal(this.deck.get(0));
                this.deck.remove(0);
            }
        }
        System.out.println("");
    }

    public void determineGame() {
        this.gameType = rng.nextInt(9);
        this.caller = rng.nextInt(4);
        players.get(this.caller).setTeam(1);
        System.out.println(players.get(this.caller).getName() + " plays game " + this.gameType);

        for (CPlayer player : players) {
            for (CCard card : player.getHand().getCards()) {
                if (this.gameType < 4) { // Sauspiel
                    if (card.getColor() == 1 || card.getNumber() == 3 || card.getNumber() == 4) {
                        card.makeTrump();
                    }
                    if (card.getColor() == gameType && card.getNumber() == 7){ // Mitspieler
                        player.setTeam(1);
                    }
                }
                else if (this.gameType < 8) { // Solo
                    if (card.getColor() == gameType % 4 || card.getNumber() == 3 || card.getNumber() == 4) {
                        card.makeTrump();
                    }
                }
                else if (this.gameType == 8) { // Wenz
                    if (card.getNumber() == 3) {
                        card.makeTrump();
                    }
                }
            }
        }
    }

    public int playTrick(int startingPlayer) {
        System.out.println("Starting trick " + (tricks.size()+1));
        CTrick currentTrick = new CTrick(startingPlayer);
        for (int i=startingPlayer; i<startingPlayer+4;i++) {
            final CCard playedCard = players.get(i%4).playCard(currentTrick);
            currentTrick.addCard(playedCard);
        }

        int winner = currentTrick.getWinner();
        int points = currentTrick.getValue();
        System.out.println(players.get(winner).getName() + " gets " + points + " points.");

        tricks.add(currentTrick);
        System.out.println("");

        return winner;
    }

    private void initializePlayers() {
        for (int i=0;i<4;i++){
            this.players.add(new CPlayer());
            this.players.get(i).setPosition(i);
            this.players.get(i).setAI(false);
        }
        this.players.get(0).setName("Amelie");
        this.players.get(1).setName("Bert");
        this.players.get(2).setName("Celine");
        this.players.get(3).setName("Dennis");
    }
}
