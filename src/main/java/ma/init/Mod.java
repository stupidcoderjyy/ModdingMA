package ma.init;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Mod implements ClientModInitializer, DedicatedServerModInitializer {
    public static final String MOD_ID = "ma";
    public static final boolean ENV_DATA_GEN = System.getProperty("fabric-api.datagen") != null;

    public Mod() {
        initElements();
    }

    @Override
    public void onInitializeClient() {
        ModRegistry.commonInit();
        ModRegistry.serverInit();
        ModRegistry.clientInit();
        if (!ENV_DATA_GEN) {
            ModRegistry.close();
        }
    }

    @Override
    public void onInitializeServer() {
        ModRegistry.commonInit();
        ModRegistry.serverInit();
        if (!ENV_DATA_GEN) {
            ModRegistry.close();
        }
    }

    public static Identifier modLoc(String path) {
        return new Identifier(MOD_ID, path);
    }

    public static Identifier vanillaLoc(String path) {
        return new Identifier(path);
    }

    public static Identifier expandLoc(String prefix, Identifier loc) {
        String path = loc.getPath();
        if (path.indexOf('/') > 0) {
            return loc;
        }
        return new Identifier(loc.getNamespace(), prefix + "/" + path);
    }

    public static Logger getLogger() {
        return LogManager.getLogger(MOD_ID);
    }

    public static void initElements() {
        ModItemGroups.init();
        ModItems.init();
        ModBlocks.init();
        ModBlockEntities.init();
    }
}
