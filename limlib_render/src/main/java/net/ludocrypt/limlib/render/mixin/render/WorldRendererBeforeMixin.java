package net.ludocrypt.limlib.render.mixin.render;

import org.quiltmc.loader.api.QuiltLoader;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.ludocrypt.limlib.render.access.IrisClientAccess;
import net.ludocrypt.limlib.render.access.WorldRendererAccess;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;

@Mixin(value = WorldRenderer.class, priority = 950)
public abstract class WorldRendererBeforeMixin implements WorldRendererAccess {

	@Shadow
	@Final
	private MinecraftClient client;

	@Inject(method = "Lnet/minecraft/client/render/WorldRenderer;render(Lnet/minecraft/client/util/math/MatrixStack;FJZLnet/minecraft/client/render/Camera;Lnet/minecraft/client/render/GameRenderer;Lnet/minecraft/client/render/LightmapTextureManager;Lnet/minecraft/util/math/Matrix4f;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiler/Profiler;swap(Ljava/lang/String;)V", ordinal = 10, shift = At.Shift.BEFORE))
	private void limlib$render$drawLayer(MatrixStack matrices, float tickDelta, long limitTime, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightmapTextureManager lightmapTextureManager, Matrix4f positionMatrix, CallbackInfo ci) {
		if (QuiltLoader.isModLoaded("iris")) {
			if (((IrisClientAccess) client).areShadersInUse()) {
				return;
			}
		}

		this.render(matrices, positionMatrix, tickDelta, camera);
	}

	@Inject(method = "Lnet/minecraft/client/render/WorldRenderer;render(Lnet/minecraft/client/util/math/MatrixStack;FJZLnet/minecraft/client/render/Camera;Lnet/minecraft/client/render/GameRenderer;Lnet/minecraft/client/render/LightmapTextureManager;Lnet/minecraft/util/math/Matrix4f;)V", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;clear(IZ)V", ordinal = 0, shift = At.Shift.AFTER))
	private void limlib$render$clear(MatrixStack matrices, float tickDelta, long limitTime, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightmapTextureManager lightmapTextureManager, Matrix4f positionMatrix, CallbackInfo ci) {
		if (QuiltLoader.isModLoaded("iris")) {
			if (((IrisClientAccess) client).areShadersInUse()) {
				return;
			}
		}

		this.renderSkyboxes(matrices, positionMatrix, tickDelta);
	}

}