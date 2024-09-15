package uparis.diamanthamdi.backend.model.cell.type;

import uparis.diamanthamdi.backend.model.cell.Celltype;

public class GrasslandCell extends Cell {

    public GrasslandCell(int x, int y) {
        super(x, y);

        setType(Celltype.GRASSLAND);
    }
    
    @Override
    public String toString() {
        return "G";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof GrasslandCell)) {
            return false;
        }

        GrasslandCell cell = (GrasslandCell) obj;
        return this.getX() == cell.getX() && this.getY() == cell.getY();
    }

    @Override
    public int hashCode() {
        return this.getX() * 31 + this.getY();
    }
}
