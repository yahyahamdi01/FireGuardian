package uparis.diamanthamdi.backend.model.cell.type;

import uparis.diamanthamdi.backend.model.cell.Celltype;

public class SnowCell extends Cell {

    public SnowCell(int x, int y) {
        super(x, y);

        setType(Celltype.SNOW);
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

        if (!(obj instanceof SnowCell)) {
            return false;
        }

        SnowCell cell = (SnowCell) obj;
        return this.getX() == cell.getX() && this.getY() == cell.getY();
    }

    @Override
    public int hashCode() {
        return this.getX() * 31 + this.getY();
    }
}
