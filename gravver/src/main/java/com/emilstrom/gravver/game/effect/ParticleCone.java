package com.emilstrom.gravver.game.effect;

import com.emilstrom.gravver.game.Art;
import com.emilstrom.gravver.game.Game;
import com.emilstrom.gravver.helper.Color;
import com.emilstrom.gravver.helper.GameMath;
import com.emilstrom.gravver.helper.Sprite;
import com.emilstrom.gravver.helper.Timer;
import com.emilstrom.gravver.helper.Vertex;

/**
 * Created by Emil on 2014-04-09.
 */
public class ParticleCone extends Effect {
	static Sprite particleSprite = new Sprite(Art.blank);

	class Particle {
		Vertex position, velocity;
		Timer alphaTimer;

		public Particle(Vertex pos, float dir, float intensity) {
			position = new Vertex(pos);
			velocity = Vertex.getDirectionVertex(dir).times((float)GameMath.getRndDouble(7.0f, 15.0f) * intensity);

			alphaTimer = new Timer((float)GameMath.getRndDouble(1.4f, 2.5f) * intensity, false);
		}

		public void logic() {
			if (alphaTimer.isDone()) return;

			position.add(velocity.times(Game.updateTime));
			velocity.subtract(velocity.times(0.2f * Game.updateTime));
			alphaTimer.logic();
		}

		public void draw() {
			if (alphaTimer.isDone()) return;

			particleSprite.setColor(new Color(1f, 1f, 1f, 1f-alphaTimer.percentageDone()));
			particleSprite.draw(position, new Vertex(0.07f, 0.07f), 0);
		}
	}


	Particle particleList[];

	public ParticleCone(Vertex pos, float dir, float spread, float intens) {
		createParticles(pos, dir, spread, intens);
	}

	public void createParticles(Vertex position, float direction, float spread, float intensity) {
		particleList = new Particle[(int)(20f * intensity)];
		for(int i=0; i<particleList.length; i++)
			particleList[i] = new Particle(position, direction + (float)GameMath.getRndDouble(-spread/2, spread/2), intensity);
	}

	public void logic() {
		for(Particle p : particleList) p.logic();
	}

	public void draw() {
		for(Particle p : particleList) p.draw();
	}
}
