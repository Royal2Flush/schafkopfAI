public class IDTranslator {
    private int[][] IDList;

    public IDTranslator() {
        this.IDList = new int[48][2];

        for (int i=0;i<8;i++) {
            this.IDList[i][0]=0;
            this.IDList[i][1]=i;
        }
        for (int i=8;i<16;i++) {
            this.IDList[i][0]=1;
            this.IDList[i][1]=i-8;
        }
        for (int i=16;i<24;i++) {
            this.IDList[i][0]=2;
            this.IDList[i][1]=i-16;
        }
        for (int i=24;i<32;i++) {
            this.IDList[i][0]=3;
            this.IDList[i][1]=i-24;
        }
        for (int i=32;i<48;i++) {
            this.IDList[i][0]=4;
        }
        for (int i=32;i<48;i++) {
            this.IDList[i][1]=i-32;
        }
        /*for (int i=40;i<44;i++) {
            this.IDList[i][1]=3;
        }
        for (int i=45;i<48;i++) {
            this.IDList[i][1]=4;
        }*/
    }

    public int getColor(int ID) {
        return this.IDList[ID][0];
    }

    public int getNumber(int ID) {
        return this.IDList[ID][1];
    }

    public int getID(int color, int number) {
        return color*8+number;
    }
}
