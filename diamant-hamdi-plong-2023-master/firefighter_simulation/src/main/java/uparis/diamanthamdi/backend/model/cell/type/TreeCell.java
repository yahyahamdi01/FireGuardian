package uparis.diamanthamdi.backend.model.cell.type;

import uparis.diamanthamdi.backend.model.cell.Celltype;

public class TreeCell extends Cell {

    public TreeCell(int x, int y) {
        super(x, y);

        setType(Celltype.TREE);
    }
    
    @Override
    public String toString() {
        return "T";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof TreeCell)) {
            return false;
        }

        TreeCell cell = (TreeCell) obj;
        return this.getX() == cell.getX() && this.getY() == cell.getY();
    }

    @Override
    public int hashCode() {
        return this.getX() * 31 + this.getY();
    }
}
