package ma.datagen;

import ma.datagen.lang.Language;
import ma.datagen.models.ItemModels;
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
        ItemModels.build();
        Language.build();
    }
}
