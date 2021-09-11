package me.mattdokn.chunkcacher;

import me.mattdokn.chunkcacher.config.ModConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.packet.s2c.play.UnloadChunkS2CPacket;
import net.minecraft.server.world.ChunkTicketManager;

@Environment(EnvType.CLIENT)
public class ChunkCacher implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModConfig.init();
    }
}
