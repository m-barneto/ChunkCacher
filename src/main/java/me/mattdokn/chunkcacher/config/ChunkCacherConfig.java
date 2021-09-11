package me.mattdokn.chunkcacher.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.minecraft.text.TranslatableText;

public class ChunkCacherConfig implements ModMenuApi {
    private static ConfigBuilder builder() {
        ConfigBuilder builder = ConfigBuilder.create()
                .setTitle(new TranslatableText("category.chunkcacher"))
                .setEditable(true)
                .setSavingRunnable(ModConfig::writeJson);

        ConfigCategory general = builder.getOrCreateCategory(new TranslatableText("chunkcacher.config.general"));

        general.addEntry(builder.entryBuilder()
                .startIntField(new TranslatableText("chunkcacher.config.unloaddistance"), ModConfig.INSTANCE.unloadDistance)
                .setDefaultValue(32)
                .setSaveConsumer(val -> ModConfig.INSTANCE.unloadDistance = val)
                .build());

        return builder;
    }
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> builder().setParentScreen(parent).build();
    }
}
