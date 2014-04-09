package com.emilstrom.gravver.game.effect;

import com.emilstrom.gravver.helper.Vertex;

/**
 * Created by Emil on 2014-04-09.
 */
public class CollisionEffect extends Effect {
	RingEffect ringEffect;
	ParticleCone particleEffect[] = new ParticleCone[2];

	public CollisionEffect(Vertex pos, float size, float dir, float intensity) {
		ringEffect = new RingEffect(pos, size, intensity);
		particleEffect[0] = new ParticleCone(pos, dir, 45f, intensity);
		particleEffect[1] = new ParticleCone(pos, dir+180f, 45f, intensity);
	}

	public void logic() {
		ringEffect.logic();
		particleEffect[0].logic();
		particleEffect[1].logic();
	}

	public void draw() {
		ringEffect.draw();
		particleEffect[0].draw();
		particleEffect[1].draw();
	}
}