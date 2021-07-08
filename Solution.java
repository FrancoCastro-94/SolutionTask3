
import java.util.ArrayList;

public class Solution {

    public String solution(int N, String S, String T) {

        ArrayList<String> shipsPosition = splitStringPositions(S);
        ArrayList<Ship> ships = createShips(shipsPosition);

        ArrayList<String> hitsPosition = splitStringHits(T);
        ArrayList<IntPosition> hits = createHitsPositions(hitsPosition);

        int[] result = compareShipsWithHits(ships, hits);
        return builderSolution(result);
    }

    //Splits positions and puts them in a list, and separates the ships by "|"
    private ArrayList<String> splitStringPositions(String S){
        char[] shipsCharArray = S.toCharArray();
        ArrayList<String> shipPosition = new ArrayList<>();
        StringBuilder newPosition = new StringBuilder(); // max capacity is 3, example : 15F
        for (char c : shipsCharArray){
            switch (c){
                case ' ':
                    shipPosition.add(newPosition.toString());
                    newPosition.delete(0, newPosition.length());
                    break;
                case ',':
                    shipPosition.add(newPosition.toString());
                    shipPosition.add("|"); // ship separator
                    newPosition.delete(0, newPosition.length());
                    break;
                default:
                    newPosition.append(c);
                    break;
            }
        }
        shipPosition.add(newPosition.toString());
        return shipPosition;
    }

    //Splits hits and puts them in a list
    private ArrayList<String> splitStringHits(String T){
        char[] shipsCharArray = T.toCharArray();
        ArrayList<String> hits = new ArrayList<>();
        StringBuilder hit = new StringBuilder(); // max capacity is 3, example : 15F
        for (char c : shipsCharArray){
            switch (c){
                case ' ':
                    hits.add(hit.toString());
                    hit.delete(0, hit.length());
                    break;
                default:
                    hit.append(c);
                    break;
            }
        }
        hits.add(hit.toString());
        return hits;
    }

    //
    private int[] compareShipsWithHits(ArrayList<Ship> ships, ArrayList<IntPosition> positions){
        boolean hitCol = false, hitRow =false;
        for (Ship ship : ships){
            for (IntPosition position : positions){
                for (int shipCol : ship.getColValues()){
                    if (shipCol == position.getCol()){
                        hitCol = true;
                        break;
                    }
                }
                for (int shipRow : ship.getRowValues()){
                    if (shipRow == position.getRow()){
                        hitRow = true;
                        break;
                    }
                }
                if (hitCol && hitRow){
                    ship.addHit();
                }
                hitCol = false;
                hitRow = false;
            }
        }
        return countSunkAndHits(ships);
    }

    private int[] countSunkAndHits(ArrayList<Ship> ships){
        int sunkenShip = 0, hitShip = 0;
        int aux;
        for (Ship ship : ships) {
            aux = ship.getShipSize() - ship.getReceivedHit();
            if (aux == 0) {
                sunkenShip++;
                continue;
            }
            if (ship.getReceivedHit() > 0){
                hitShip += ship.getReceivedHit();
            }
        }
        return new int[]{sunkenShip, hitShip};
    }

    private ArrayList<IntPosition> createHitsPositions(ArrayList<String> strPositions){
        ArrayList<IntPosition> positions = new ArrayList<>();
        for (String s : strPositions){
            positions.add(new IntPosition(s));
        }
        return positions;
    }

    private ArrayList<Ship> createShips(ArrayList<String> shipsCorners){
        ArrayList<Ship> ships = new ArrayList<>();
        ArrayList<String> positions = new ArrayList<>();
        for (String p : shipsCorners) {
            if (p.equals("|")){
                switch (positions.size()){
                    case 1:
                        ships.add(new Ship(positions.get(0)));
                        positions.clear();
                        break;
                    case 2:
                        ships.add(new Ship(positions.get(0), positions.get(1)));
                        positions.clear();
                        break;
                }
            }else {
                positions.add(p);
            }
        }
        // add last ship
        if (positions.size() == 1){
            ships.add(new Ship(positions.get(0)));
        }else {
            ships.add(new Ship(positions.get(0), positions.get(1)));
        }
        return ships;
    }

    private String builderSolution(int[] result){
        StringBuilder s = new StringBuilder();
        s.append('(');
        s.append(result[0]);
        s.append(',');
        s.append(result[1]);
        s.append(')');
        return s.toString();
    }


}

