package com.emilstrom.gravver.game.entity;

import com.emilstrom.gravver.game.Game;
import com.emilstrom.gravver.game.Map;
import com.emilstrom.gravver.game.effect.CollisionEffect;
import com.emilstrom.gravver.helper.Vertex;

/**
 * Created by Emil on 2014-04-02.
 */
public class Actor extends Entity {
	Vertex velocity = new Vertex(0,0);
	float size = 1f;

	float maxVelocity = 4f;

	public Actor(Map m) {
		super(m);
	}

	public float getKineticEnergy() { return velocity.getLength() + size; }
	public boolean getCollision(Vertex v, float s) {
		return Vertex.getLength(position, v) <= (size + s)/2;
	}

	public void collideWith(Actor a) {
		float ke = getKineticEnergy(),
				ke2 = a.getKineticEnergy();

		//Force towards me
		Vertex forceDir = Vertex.getDirectionVertex(a.position, position);
		velocity.add(forceDir.times(ke2 / size));

		//And the other guy
		forceDir.reverse();
		a.velocity.add(forceDir.times(ke / a.size));

		//Collision effect
		map.addEffect(new CollisionEffect(position.plus(forceDir.times(size)), (ke + ke2) * 0.3f, forceDir.getDirection(), (ke + ke2) * 0.05f));
	}

	public void logic() {
		//Cap velocity
		if (velocity.getLength() > maxVelocity) {
			velocity = Vertex.normalize(velocity).times(maxVelocity);
		}

		//Bounce
		if (position.x+velocity.x* Game.updateTime < -Game.currentGame.gameWidth/2 || position.x+velocity.x*Game.updateTime > Game.currentGame.gameWidth/2)
			velocity.x *= -0.6f;

		//Collision
		Actor a = map.getCollision(this, position.plus(velocity.times(Game.updateTime)), size);
		if (a != null) collideWith(a);
	}

	public void draw() {
	}
}
