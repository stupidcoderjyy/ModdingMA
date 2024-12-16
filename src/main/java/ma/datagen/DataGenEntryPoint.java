package ma.datagen;

import ma.init.Mod;
import ma.init.registry.AbstractDef;
import ma.util.datagen.DataProviders;
import ma.util.datagen.ModDataProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class DataGenEntryPoint implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        DataProviders.init();
        buildData();
        ModDataProvider.registerProviders(generator.createPack());
    }

    private void buildData() {
        Mod.initElements();
        AbstractDef.DEFINITIONS.forEach(AbstractDef::genData);
    }
}
