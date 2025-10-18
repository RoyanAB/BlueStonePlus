package cn.royan.bluestoneplus.mixin;

import net.minecraft.client.entity.particle.Particle;
import net.minecraft.client.entity.particle.RedstoneParticle;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(RedstoneParticle.class)
public abstract class RedstoneParticleMixin extends Particle {
	protected RedstoneParticleMixin(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Inject(method = "<init>(Lnet/minecraft/world/World;DDDFFFF)V", at = @At("RETURN"))
	public void blue(World world, double x, double y, double z, float scaleFactor, float r, float g, float b, CallbackInfo ci){
		Color color = new Color(2, 230, 234);

		this.red = ((color.getRGB() >> 16) & 0xFF) / 255.0f;
		this.green = ((color.getRGB() >> 8) & 0xFF) / 255.0f;
		this.blue = (color.getRGB() & 0xFF) / 255.0f;
	}
}
