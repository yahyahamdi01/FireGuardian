package uparis.diamanthamdi.backend.model.cell.type;

import uparis.diamanthamdi.backend.model.cell.Celltype;

public class MangroveCell extends Cell {

    public MangroveCell(int x, int y) {
        super(x, y);

        setType(Celltype.MANGROVE);
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

        if (!(obj instanceof MangroveCell)) {
            return false;
        }

        MangroveCell cell = (MangroveCell) obj;
        return this.getX() == cell.getX() && this.getY() == cell.getY();
    }

    @Override
    public int hashCode() {
        return this.getX() * 31 + this.getY();
    }
}
