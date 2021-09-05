package me.mattdokn.chunkcacher.mixins;

import me.mattdokn.chunkcacher.config.ModConfig;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameJoinS2CPacket.class)
public class GameJoinS2CPacketMixin {
    @Inject(method = "getViewDistance", at = @At("RETURN"), cancellable = true)
    private void getViewDistance(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(ModConfig.INSTANCE.unloadDistance);
    }
}
