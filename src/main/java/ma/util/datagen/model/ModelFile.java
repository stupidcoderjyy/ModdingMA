package ma.util.datagen.model;

import net.minecraft.util.Identifier;

public class ModelFile {
    protected final Identifier location;

    public ModelFile(Identifier location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return location.toString();
    }
}

