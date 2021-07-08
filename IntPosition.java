
public class IntPosition {
    private int row; // row position
    private int col; // column position


    public IntPosition(String strPosition){
        int len = strPosition.length();
        char[] arrChar = strPosition.toCharArray();
        this.row = parseCharRow(arrChar[len - 1]);
        this.col = len == 2 ? oneDigitCol(arrChar[0]) : twoDigitCol(arrChar[0],arrChar[1]);
    }

    // Get int row position of character
    private int parseCharRow(char charRow){
        for (int i = 0; i < Constans.rows.length; i++) {
            if (charRow == Constans.rows[i]) {
                return this.row = i + 1;
            }
        }
        return 0;
    }

    // Parse two numerics character to one integer
    private int twoDigitCol(char firstCharCol, char secondCharCol){
        StringBuilder s = new StringBuilder(2);
        s.append(firstCharCol);
        s.append(secondCharCol);
        return Integer.parseInt(s.toString());
    }

    // Parse one numeric character to one integer
    private int oneDigitCol(char charCol){
        return Character.getNumericValue(charCol);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
