package me.mattdokn.chunkcacher.mixins;

import me.mattdokn.chunkcacher.ChunkCacher;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.ChunkLoadDistanceS2CPacket;
import net.minecraft.network.packet.s2c.play.UnloadChunkS2CPacket;
import net.minecraft.util.math.ChunkPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Redirect(method = "onChunkLoadDistance", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/packet/s2c/play/ChunkLoadDistanceS2CPacket;getDistance()I"))
    public int onChunkLoadDistance(ChunkLoadDistanceS2CPacket instance) {
        return ChunkCacher.viewDistance;
    }

    @Inject(method = "onUnloadChunk", at = @At("HEAD"), cancellable = true)
    private void onUnloadChunk(UnloadChunkS2CPacket packet, CallbackInfo info) {
        if (MinecraftClient.getInstance().player.getChunkPos().getChebyshevDistance(new ChunkPos(packet.getX(), packet.getZ())) <= ChunkCacher.viewDistance) {
            info.cancel();
        }
    }
}
