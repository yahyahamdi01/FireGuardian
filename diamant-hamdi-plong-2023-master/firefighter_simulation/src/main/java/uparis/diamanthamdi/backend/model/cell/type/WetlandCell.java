package uparis.diamanthamdi.backend.model.cell.type;

import uparis.diamanthamdi.backend.model.cell.Celltype;

public class WetlandCell extends Cell {

    public WetlandCell(int x, int y) {
        super(x, y);

        setType(Celltype.WETLAND);
    }
    
    @Override
    public String toString() {
        return "W";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof WetlandCell)) {
            return false;
        }

        WetlandCell cell = (WetlandCell) obj;
        return this.getX() == cell.getX() && this.getY() == cell.getY();
    }

    @Override
    public int hashCode() {
        return this.getX() * 31 + this.getY();
    }
}
