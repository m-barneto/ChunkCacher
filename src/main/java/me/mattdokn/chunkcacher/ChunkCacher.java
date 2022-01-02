package me.mattdokn.chunkcacher;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

@Environment(EnvType.CLIENT)
public class ChunkCacher implements ClientModInitializer {
    private long ticker = 0L;
    public static int viewDistance = -1;
    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(mc -> {
            if (mc == null || mc.options == null || mc.world == null || mc.world.getChunkManager() == null) {
                return;
            }
            ticker++;
            if (ticker % 10 == 0) {
                // Check options for new view distance
                if (viewDistance == -1 || mc.options.viewDistance != viewDistance) {
                    // View distance changed
                    viewDistance = mc.options.viewDistance;
                    // Do something idk
                    mc.world.getChunkManager().updateLoadDistance(viewDistance);
                }
            }
        });
    }
}
