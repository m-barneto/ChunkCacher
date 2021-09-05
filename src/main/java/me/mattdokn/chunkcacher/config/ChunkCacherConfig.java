package me.mattdokn.chunkcacher.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.minecraft.text.TranslatableText;

public class ChunkCacherConfig implements ModMenuApi {
    private static ConfigBuilder builder() {
        System.out.println("Building config menu");
        ConfigBuilder builder = ConfigBuilder.create()
                .setTitle(new TranslatableText("category.chunkcacher"))
                .setEditable(true)
                .setSavingRunnable(ModConfig::writeJson);

        System.out.println("Building general category");
        ConfigCategory general = builder.getOrCreateCategory(new TranslatableText("chunkcacher.config.general"));

        System.out.println("Building unload distance config entry");
        general.addEntry(builder.entryBuilder()
                .startIntField(new TranslatableText("chunkcacher.config.unloaddistance"), ModConfig.INSTANCE.unloadDistance)
                .setDefaultValue(32)
                .setSaveConsumer(val -> ModConfig.INSTANCE.unloadDistance = val)
                .build());

        return builder;
    }
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {

        System.out.println("Calling builder");
        return parent -> builder().setParentScreen(parent).build();
    }
}
