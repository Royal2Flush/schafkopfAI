public class Main {
    public static void main(String [] args) {
        CGame game = new CGame();
        game.prepareGame();
        game.deal4();
        game.deal4();
        game.determineGame();
        int firstPlayer = 0;
        for (int i=0;i<8;i++) {
            firstPlayer = game.playTrick(firstPlayer);
        }
    }
}
