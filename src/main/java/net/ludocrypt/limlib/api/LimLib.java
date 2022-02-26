package net.ludocrypt.limlib.api;

import net.fabricmc.api.ModInitializer;
import net.ludocrypt.limlib.api.render.LiminalShader;
import net.ludocrypt.limlib.api.render.SkyHook;
import net.ludocrypt.limlib.api.sound.LiminalTravelSound;
import net.ludocrypt.limlib.impl.world.LiminalShaderRegistry;
import net.ludocrypt.limlib.impl.world.LiminalSkyRegistry;
import net.ludocrypt.limlib.impl.world.LiminalSoundRegistry;
import net.minecraft.util.Identifier;

public class LimLib implements ModInitializer {

	@Override
	public void onInitialize() {
		LiminalSkyRegistry.register(new Identifier("limlib", "regular_sky"), SkyHook.RegularSky.CODEC);
		LiminalSkyRegistry.register(new Identifier("limlib", "skybox_sky"), SkyHook.SkyboxSky.CODEC);
		LiminalShaderRegistry.register(new Identifier("limlib", "simple_shader"), LiminalShader.SimpleShader.CODEC);
		LiminalSoundRegistry.register(new Identifier("limlib", "simple_sound"), LiminalTravelSound.SimpleTravelSound.CODEC);
		LiminalSoundRegistry.register(new Identifier("limlib", "regex_sound"), LiminalTravelSound.RegexTravelSound.CODEC);
	}

}