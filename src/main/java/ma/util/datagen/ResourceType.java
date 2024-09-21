package ma.util.datagen;

import net.minecraft.data.DataOutput;

/**
 * 资源类型，用于规定生成路径
 */
public enum ResourceType {
    MODEL(DataOutput.OutputType.RESOURCE_PACK, ".json", "models"),
    BLOCK_STATE(DataOutput.OutputType.RESOURCE_PACK, ".json", "blockstates"),
    LANG(DataOutput.OutputType.RESOURCE_PACK, ".json", "lang"),
    RECIPE(DataOutput.OutputType.DATA_PACK, ".json", "recipes"),
    TAG(DataOutput.OutputType.DATA_PACK, ".json", "tags")
    ;
    public final DataOutput.OutputType type;
    public final String pathSuffix;
    public final String pathPrefix;

    ResourceType(DataOutput.OutputType type, String pathSuffix, String pathPrefix) {
        this.type = type;
        this.pathSuffix = pathSuffix;
        this.pathPrefix = pathPrefix;
    }
}
