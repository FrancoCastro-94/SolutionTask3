

public class Ship {

    private IntPosition startPosition;
    private IntPosition endPosition;
    private int[] rowValues;
    private int[] colValues;
    private int shipSize;
    private int receivedHit;

    public Ship(String strStarCorner, String strEndCorner) {
        this.startPosition = new IntPosition(strStarCorner);
        this.endPosition = new IntPosition(strEndCorner);
        this.rowValues = createRowValues();
        this.colValues = createColValues();
        this.shipSize = rowValues.length * colValues.length;
        this.receivedHit = 0;
    }

    public Ship(String strStarCorner) {
        this.startPosition = new IntPosition(strStarCorner);
        this.rowValues = new int[]{startPosition.getRow()};
        this.colValues = new int[]{startPosition.getCol()};
        this.shipSize = 1;
        this.receivedHit = 0;
    }

    private int[] createRowValues(){
        int width = this.endPosition.getRow() - this.startPosition.getRow() + 1;
        int[] result = new int[width];
        for (int i = 0; i < width; i++) {
            result[i] = this.startPosition.getRow() + i;
        }
        return result;
    }

    private int[] createColValues(){
        int width = this.endPosition.getCol() - this.startPosition.getCol() + 1;
        int[] result = new int[width];
        for (int i = 0; i < width; i++) {
            result[i] = this.startPosition.getCol() + i;
        }
        return result;
    }

    public int[] getRowValues() {
        return rowValues;
    }

    public int[] getColValues() {
        return colValues;
    }

    public int getShipSize() {
        return shipSize;
    }

    public int getReceivedHit() {
        return receivedHit;
    }

    public void addHit() {
        this.receivedHit++;
    }
}
