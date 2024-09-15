package uparis.diamanthamdi.backend.model.cell.type;

import uparis.diamanthamdi.backend.model.cell.Celltype;

public class CroplandCell extends Cell {

    public CroplandCell(int x, int y) {
        super(x, y);

        setType(Celltype.CROPLAND);
    }
    
    @Override
    public String toString() {
        return "C";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof CroplandCell)) {
            return false;
        }

        CroplandCell cell = (CroplandCell) obj;
        return this.getX() == cell.getX() && this.getY() == cell.getY();
    }

    @Override
    public int hashCode() {
        return this.getX() * 31 + this.getY();
    }
}
