package ma.util.datagen.blockstate.multipart;

import com.google.gson.JsonObject;

public class ListPredicate implements ModelPredicate {
    private final JsonObject list = new JsonObject();

    public ListPredicate add(String key, String ... options){
        list.addProperty(key, getOptionString(options));
        return this;
    }

    private String getOptionString(String[] options) {
        StringBuilder sb = new StringBuilder();
        switch (options.length) {
            case 0 -> {}
            case 1 -> sb.append(options[0]);
            default -> {
                int i;
                for (i = 0; i < options.length - 1; i++) {
                    sb.append(options[i]).append('|');
                }
                sb.append(options[i]);
            }
        }
        return sb.toString();
    }

    @Override
    public JsonObject toJson() {
        return list;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
