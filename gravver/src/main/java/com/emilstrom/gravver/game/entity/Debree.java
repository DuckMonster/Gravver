package com.emilstrom.gravver.game.entity;

import android.util.Log;

import com.emilstrom.gravver.Gravver;
import com.emilstrom.gravver.game.Art;
import com.emilstrom.gravver.game.Game;
import com.emilstrom.gravver.helper.Color;
import com.emilstrom.gravver.helper.GameMath;
import com.emilstrom.gravver.helper.Sprite;
import com.emilstrom.gravver.helper.Vertex;

/**
 * Created by Emil on 2014-04-08.
 */
public class Debree extends Actor {
	Player player;
	Vertex speed;

	float rotation, rotationSpeed;

	Sprite debreeSprite;

	public Debree(Vertex pos, Player p) {
		player = p;

		position = pos;

		debreeSprite = new Sprite(Art.blank);

		rotationSpeed = (float)GameMath.getRndDouble(-270f, 270f);
		speed = new Vertex((float)GameMath.getRndDouble(-3f, 3f), -3f);
	}

	public void logic() {
		rotation += rotationSpeed * Game.updateTime;

		//Gravity
		Vertex dir = Vertex.getDirectionVertex(position, player.position);
		float dis = Vertex.getLength(position, player.position);

		if (dis < Player.gravityRange) {
			float distanceFactor = (float)Math.pow(Math.E, -(dis/Player.gravityRange) * 1.5f);

			Vertex force = dir.times(player.currentGravity * Player.gravityFactor).times(distanceFactor);
			speed.add(force.times(Game.updateTime));
		}

		position.add(speed.times(Game.updateTime));


		//Bounce
		if (position.x+speed.x*Game.updateTime < -Game.currentGame.gameWidth/2 || position.x+speed.x*Game.updateTime > Game.currentGame.gameWidth/2)
			speed.x *= -1;
	}

	public void draw() {
		debreeSprite.setColor(Color.WHITE);
		debreeSprite.draw(position, new Vertex(1,1), rotation);
	}
}
