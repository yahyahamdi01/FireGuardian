package uparis.diamanthamdi.backend.model.cell.type;

import uparis.diamanthamdi.backend.model.cell.Celltype;

public class WaterCell extends Cell {

    public WaterCell(int x, int y) {
        super(x, y);

        setType(Celltype.WATER);
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

        if (!(obj instanceof WaterCell)) {
            return false;
        }

        WaterCell cell = (WaterCell) obj;
        return this.getX() == cell.getX() && this.getY() == cell.getY();
    }

    @Override
    public int hashCode() {
        return this.getX() * 31 + this.getY();
    }
}
