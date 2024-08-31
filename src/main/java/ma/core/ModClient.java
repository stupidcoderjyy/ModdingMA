package ma.core;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ModClient extends Mod implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
    }
}
