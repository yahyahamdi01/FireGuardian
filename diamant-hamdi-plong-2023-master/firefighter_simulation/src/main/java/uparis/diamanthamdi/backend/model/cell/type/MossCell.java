package uparis.diamanthamdi.backend.model.cell.type;

import uparis.diamanthamdi.backend.model.cell.Celltype;

public class MossCell extends Cell {

    public MossCell(int x, int y) {
        super(x, y);

        setType(Celltype.MOSS);
    }
    
    @Override
    public String toString() {
        return "M";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof MossCell)) {
            return false;
        }

        MossCell cell = (MossCell) obj;
        return this.getX() == cell.getX() && this.getY() == cell.getY();
    }

    @Override
    public int hashCode() {
        return this.getX() * 31 + this.getY();
    }
}
