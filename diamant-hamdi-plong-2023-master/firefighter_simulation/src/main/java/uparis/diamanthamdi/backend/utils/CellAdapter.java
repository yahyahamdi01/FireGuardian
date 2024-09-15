package uparis.diamanthamdi.backend.utils;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import uparis.diamanthamdi.backend.model.cell.Celltype;
import uparis.diamanthamdi.backend.model.cell.factory.CellFactory;
import uparis.diamanthamdi.backend.model.cell.type.Cell;
import uparis.diamanthamdi.backend.model.cell.type.UrbanCell;
import uparis.diamanthamdi.backend.model.firestation.Firestation;

/**
 * Adapter for serializing and deserializing Cell objects.
 */
public class CellAdapter implements JsonSerializer<Cell>, JsonDeserializer<Cell> {
    /**
     * Serializes a Cell object to a JSON object.
     *
     * @param src     the Cell object to serialize
     * @param typeOfSrc the type of the source object
     * @param context the serialization context
     * @return the serialized JSON object
     */
    @Override
    public JsonElement serialize(Cell src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", src.getCellType().toString());
        jsonObject.addProperty("x", src.getX());
        jsonObject.addProperty("y", src.getY());

        // Check if the Cell object is an instance of Urban
        if (src instanceof UrbanCell) {
            UrbanCell urbanCell = (UrbanCell) src;
            jsonObject.addProperty("firestation", urbanCell.hasFirestation());
        }

        return jsonObject;
    }

    /**
     * Deserializes a JSON object to a Cell object.
     *
     * @param json the JSON object to deserialize
     * @param typeOfT the type of the target object
     * @param context the deserialization context
     * @return the deserialized Cell object
     */
    @Override
    public Cell deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        int x = jsonObject.get("x").getAsInt();
        int y = jsonObject.get("y").getAsInt();

        Cell cell = CellFactory.createCell(x, y, Celltype.fromString(type));
        if (cell instanceof UrbanCell) {
            UrbanCell urbanCell = (UrbanCell) cell;
            boolean hasFirestation = jsonObject.get("firestation").getAsBoolean();
            if (hasFirestation) {
                urbanCell.setFirestation(new Firestation(x, y));
            }
        }

        return cell;
    }
}
