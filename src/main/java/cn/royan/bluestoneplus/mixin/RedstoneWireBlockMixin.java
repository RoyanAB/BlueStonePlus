package cn.royan.bluestoneplus.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.property.IntegerProperty;
import net.minecraft.entity.particle.ParticleType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(RedstoneWireBlock.class)
public class RedstoneWireBlockMixin {
	/**
	 * @author AB
	 * @reason blue
	 */
	@Overwrite
	@Environment(EnvType.CLIENT)
	public static int getColorForPower(int power) {
		float f = power / 15.0f;

		int r0 = 20;
		int g0 = 49;
		int b0 = 49;

		int r1 = 2;
		int g1 = 230;
		int b1 = 234;

		int r = (int) (r0 + f * (r1 - r0));
		int g = (int) (g0 + f * (g1 - g0));
		int b = (int) (b0 + f * (b1 - b0));

		r = Math.max(0, Math.min(255, r));
		g = Math.max(0, Math.min(255, g));
		b = Math.max(0, Math.min(255, b));

		return -16777216 | (r << 16) | (g << 8) | b;
	}
}
