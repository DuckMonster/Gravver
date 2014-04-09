package com.emilstrom.gravver.game.entity;

import android.util.Log;

import com.emilstrom.gravver.Gravver;
import com.emilstrom.gravver.game.Art;
import com.emilstrom.gravver.game.Game;
import com.emilstrom.gravver.game.Map;
import com.emilstrom.gravver.helper.Color;
import com.emilstrom.gravver.helper.GameMath;
import com.emilstrom.gravver.helper.Sprite;
import com.emilstrom.gravver.helper.Tileset;
import com.emilstrom.gravver.helper.Vertex;

/**
 * Created by Emil on 2014-04-08.
 */
public class Debree extends Actor {
	Player player;

	float rotation, rotationSpeed, mass;

	Tileset debreeTileset;
	int debreeTile;

	public Debree(Map m, Vertex pos, Player p) {
		super(m);

		player = p;

		position = pos;

		debreeTileset = new Tileset(Art.debreeSet);
		debreeTile = GameMath.getRndInt(0, 4);

		rotationSpeed = (float)GameMath.getRndDouble(-270f, 270f);
		velocity = new Vertex((float)GameMath.getRndDouble(-3f, 3f), -2f);

		mass = (float)GameMath.getRndDouble(1.2, 2.0);
		size = mass;
	}

	public void logic() {
		super.logic();

		rotation += rotationSpeed * Game.updateTime;

		//Gravity
		Vertex dir = Vertex.getDirectionVertex(position, player.position);
		float dis = Vertex.getLength(position, player.position);

		if (dis < Player.gravityRange) {
			float distanceFactor = (float)Math.pow(Math.E, -(dis/Player.gravityRange) * 1.5f);

			Vertex force = dir.times(player.currentGravity * Player.gravityFactor).times(distanceFactor).times(1 / mass);
			velocity.add(force.times(Game.updateTime));
		}

		position.add(velocity.times(Game.updateTime));


		//Bounce
		if (position.x+velocity.x*Game.updateTime < -Game.currentGame.gameWidth/2 || position.x+velocity.x*Game.updateTime > Game.currentGame.gameWidth/2)
			velocity.x *= -0.6f;

		//Collision
		Actor a = map.getCollision(this, position.plus(velocity.times(Game.updateTime)), size);
		if (a != null) collideWith(a);
	}

	public void draw() {
		super.draw();

		debreeTileset.setColor(Color.WHITE);
		debreeTileset.draw(debreeTile, 0, position, new Vertex(size, size), rotation);
	}
}
