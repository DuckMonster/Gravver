package com.emilstrom.gravver.game.effect;

import com.emilstrom.gravver.game.Art;
import com.emilstrom.gravver.helper.Color;
import com.emilstrom.gravver.helper.Sprite;
import com.emilstrom.gravver.helper.Timer;
import com.emilstrom.gravver.helper.Vertex;

/**
 * Created by Emil on 2014-04-09.
 */
public class RingEffect extends Effect {
	Sprite ringSprite;
	Vertex position;
	float size, intensity;

	Timer ringTimer;

	public RingEffect(Vertex position, float size, float intensity) {
		ringSprite = new Sprite(Art.ring);
		this.position = new Vertex(position);
		this.size = size;
		this.intensity = intensity;

		ringTimer = new Timer(intensity, false);
	}

	public void logic() {
		if (ringTimer.isDone()) return;

		ringTimer.logic();
	}

	public void draw() {
		if (ringTimer.isDone()) return;

		float diameter = size * (1f - (float)Math.pow(Math.E, -ringTimer.percentageDone() * 5f));

		ringSprite.setColor(new Color(1f, 1f, 1f, 1f-ringTimer.percentageDone()));
		ringSprite.draw(position, new Vertex(diameter, diameter), 0);
	}
}
