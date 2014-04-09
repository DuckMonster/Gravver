package com.emilstrom.gravver.game.entity;

import com.emilstrom.gravver.game.Map;
import com.emilstrom.gravver.game.effect.RingEffect;
import com.emilstrom.gravver.helper.Vertex;

/**
 * Created by Emil on 2014-04-02.
 */
public class Actor extends Entity {
	RingEffect collisionEffect;

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
		collisionEffect = new RingEffect(position.plus(forceDir.times(size)), (ke + ke2) * 0.3f, (ke + ke2) * 0.05f);
	}

	public void logic() {
		if (collisionEffect != null) collisionEffect.logic();

		//Cap velocity
		if (velocity.getLength() > maxVelocity) {
			velocity = Vertex.normalize(velocity).times(maxVelocity);
		}
	}

	public void draw() {
		if (collisionEffect != null) collisionEffect.draw();
	}
}
