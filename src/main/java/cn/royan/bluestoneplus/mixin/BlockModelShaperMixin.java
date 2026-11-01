package cn.royan.bluestoneplus.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.TripwireBlock;
import net.minecraft.client.render.block.BlockModelShaper;
import net.minecraft.client.resource.model.BlockModelProvider;
import net.minecraft.client.resource.model.VariantBlockModelProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Environment(EnvType.CLIENT)
@Mixin(BlockModelShaper.class)
public abstract class BlockModelShaperMixin {
	@Shadow
	public abstract void register(Block block, BlockModelProvider provider);

	@WrapOperation(
		method = "init",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/BlockModelShaper;register(Lnet/minecraft/block/Block;Lnet/minecraft/client/resource/model/BlockModelProvider;)V", ordinal = 15)
	)
	private void tripwirefix$restorePoweredAndDisarmed(BlockModelShaper instance, Block block, BlockModelProvider provider, Operation<Void> original) {
		this.register(Blocks.TRIPWIRE, (new VariantBlockModelProvider.Builder()).setUnusedProperties(TripwireBlock.DISARMED).build());
	}
}
