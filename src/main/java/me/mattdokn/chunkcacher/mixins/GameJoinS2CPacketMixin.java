package me.mattdokn.chunkcacher.mixins;

import me.mattdokn.chunkcacher.ChunkCacher;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameJoinS2CPacket.class)
public class GameJoinS2CPacketMixin {
    @Inject(method = "viewDistance", at = @At("RETURN"), cancellable = true)
    public void viewDistance(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(ChunkCacher.viewDistance);
    }
}
