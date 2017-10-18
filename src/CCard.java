public class CCard {
    private int ID;
    private int color;
    private int number;
    private IDTranslator translator;

    public CCard(int CID) {
        this.translator = new IDTranslator();
        this.ID = CID;
        this.color = this.translator.getColor(CID);
        this.number = this.translator.getNumber(CID);
    }

    public int getID() {
        return this.ID;
    }

    public int getColor() {
        return this.color;
    }

    public int getNumber() {
        return this.number;
    }

    public int getValue() {
        switch (this.number) {
            case 0: return 0;
            case 1: return 0;
            case 2: return 0;
            case 3: return 2;
            case 4: return 3;
            case 5: return 4;
            case 6: return 10;
            case 7: return 11;
        }
        if (this.number<12) { // Trumpf Unter
            return 2;
        }
        else { // Trumpf Ober
            return 3;
        }
    }

    public String echoName() {
        String[] c = {"S","H","G","E"};
        String[] n = {"7","8","9","U","O","K","1","A"};
        if (this.color < 4) {
            return c[this.color] + n[this.number];
        }
        else {
            if (this.number < 8) {
                return "T" + n[this.number];
            }
            else if (this.number < 12) {
                return c[this.number-8]+"U(T)";
            }
            else {
                return c[this.number-12]+"O(T)";
            }
        }
    }

    public void makeTrump() {
        if (this.number < 3) { // 7,8,9
            this.ID = this.translator.getID(4, this.number);
        }
        else if (this.number == 3) { // U
            this.ID =40+this.color;
        }
        else if (this.number == 4) { // O
            this.ID = 44+this.color;
        }
        else{ // K, 10, A
            this.ID = this.translator.getID(4, this.number);
        }
        this.color = 4;
        this.number = this.translator.getNumber(this.ID);
    }
}
