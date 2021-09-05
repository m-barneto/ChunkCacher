package me.mattdokn.chunkcacher.mixins;

import me.mattdokn.chunkcacher.config.ModConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.UnloadChunkS2CPacket;
import net.minecraft.util.math.ChunkPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Inject(method = "onUnloadChunk", at = @At("HEAD"), cancellable = true)
    private void onUnloadChunk(UnloadChunkS2CPacket packet, CallbackInfo info) {
        if (MinecraftClient.getInstance().player.getChunkPos().getChebyshevDistance(new ChunkPos(packet.getX(), packet.getZ())) <= ModConfig.INSTANCE.unloadDistance) {
            info.cancel();
        }
    }
}
