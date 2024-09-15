package uparis.diamanthamdi.backend.model.cell.type;

import uparis.diamanthamdi.backend.model.cell.Celltype;

public class ShrublandCell extends Cell {

    public ShrublandCell(int x, int y) {
        super(x, y);

        setType(Celltype.SHRUBLAND);
    }
    
    @Override
    public String toString() {
        return "S";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof ShrublandCell)) {
            return false;
        }

        ShrublandCell cell = (ShrublandCell) obj;
        return this.getX() == cell.getX() && this.getY() == cell.getY();
    }

    @Override
    public int hashCode() {
        return this.getX() * 31 + this.getY();
    }
}
