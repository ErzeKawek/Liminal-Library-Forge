package net.ludocrypt.limlib.api;

import java.util.Optional;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.ludocrypt.limlib.api.render.LiminalBaseEffects;
import net.ludocrypt.limlib.api.render.LiminalShaderApplier;
import net.ludocrypt.limlib.api.render.LiminalSkyRenderer;
import net.ludocrypt.limlib.api.sound.ReverbSettings;
import net.minecraft.sound.MusicSound;

public class LiminalEffects {

	public static final Codec<LiminalEffects> CODEC = RecordCodecBuilder.create((instance) -> {
		return instance.group(LiminalBaseEffects.CODEC.optionalFieldOf("dimension_effects").stable().forGetter((liminalEffects) -> {
			return liminalEffects.getEffects();
		}), LiminalShaderApplier.CODEC.optionalFieldOf("shader").stable().forGetter((liminalEffects) -> {
			return liminalEffects.getShader();
		}), LiminalSkyRenderer.CODEC.optionalFieldOf("sky").stable().forGetter((liminalEffects) -> {
			return liminalEffects.getSky();
		}), MusicSound.CODEC.optionalFieldOf("music").stable().forGetter((liminalEffects) -> {
			return liminalEffects.getMusic();
		}), ReverbSettings.CODEC.optionalFieldOf("reverb").stable().forGetter((liminalEffects) -> {
			return liminalEffects.getReverb();
		})).apply(instance, instance.stable(LiminalEffects::new));
	});

	private Optional<LiminalBaseEffects> effects;
	private Optional<LiminalShaderApplier> shader;
	private Optional<LiminalSkyRenderer> skyRenderer;
	private Optional<MusicSound> music;
	private Optional<ReverbSettings> reverb;

	public LiminalEffects() {
		this(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
	}

	public LiminalEffects(Optional<LiminalBaseEffects> effects, Optional<LiminalShaderApplier> shader, Optional<LiminalSkyRenderer> skyRenderer, Optional<MusicSound> music, Optional<ReverbSettings> reverb) {
		this.effects = effects;
		this.shader = shader;
		this.skyRenderer = skyRenderer;
		this.music = music;
		this.reverb = reverb;
	}

	public void setEffects(Optional<LiminalBaseEffects> effects) {
		this.effects = effects;
	}

	public void setMusic(Optional<MusicSound> music) {
		this.music = music;
	}

	public void setReverb(Optional<ReverbSettings> reverb) {
		this.reverb = reverb;
	}

	public void setShader(Optional<LiminalShaderApplier> shader) {
		this.shader = shader;
	}

	public void setSky(Optional<LiminalSkyRenderer> sky) {
		this.skyRenderer = sky;
	}

	public Optional<LiminalBaseEffects> getEffects() {
		return effects;
	}

	public Optional<MusicSound> getMusic() {
		return music;
	}

	public Optional<ReverbSettings> getReverb() {
		return reverb;
	}

	public Optional<LiminalShaderApplier> getShader() {
		return shader;
	}

	public Optional<LiminalSkyRenderer> getSky() {
		return skyRenderer;
	}

}