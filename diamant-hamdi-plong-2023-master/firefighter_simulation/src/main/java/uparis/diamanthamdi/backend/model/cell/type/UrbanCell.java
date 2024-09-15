package uparis.diamanthamdi.backend.model.cell.type;

import uparis.diamanthamdi.backend.model.cell.Celltype;
import uparis.diamanthamdi.backend.model.firestation.Firestation;

public class UrbanCell extends Cell {
    private Firestation firestation;

    public UrbanCell(int x, int y) {
        super(x, y);

        setType(Celltype.URBAN);
    }

    public void setFirestation(final Firestation firestation) {
        this.firestation = firestation;
    }

    public Firestation getFirestation() {
        return this.firestation;
    }

    public boolean hasFirestation() {
        return this.firestation != null;
    }
    
    @Override
    public String toString() {
        return "U";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof UrbanCell)) {
            return false;
        }

        UrbanCell cell = (UrbanCell) obj;
        return this.getX() == cell.getX() 
            && this.getY() == cell.getY()
            && this.hasFirestation() == cell.hasFirestation();
    }

    @Override
    public int hashCode() {
        return this.getX() * 31 + this.getY();
    }
}
