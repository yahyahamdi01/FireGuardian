package uparis.diamanthamdi.backend.model.cell.type;

import uparis.diamanthamdi.backend.model.cell.Celltype;

public class BareVegetationCell extends Cell{

    public BareVegetationCell(int x, int y) {
        super(x, y);

        setType(Celltype.BAREVEGETATION);
    }
    
    @Override
    public String toString() {
        return "B";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof BareVegetationCell)) {
            return false;
        }

        BareVegetationCell cell = (BareVegetationCell) obj;
        return this.getX() == cell.getX() && this.getY() == cell.getY();
    }

    @Override
    public int hashCode() {
        return this.getX() * 31 + this.getY();
    }
}
