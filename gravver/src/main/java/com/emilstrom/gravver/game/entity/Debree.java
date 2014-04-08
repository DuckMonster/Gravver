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
		speed = new Vertex((float)GameMath.getRndDouble(-5f, 5f), -6f);
	}

	public void logic() {
		rotation += rotationSpeed * Game.updateTime;

		//Gravity
		Vertex dir = Vertex.getDirectionVertex(position, player.position);
		float dis = Vertex.getLength(position, player.position);

		if (dis < Player.gravityRange) {
			Vertex force = dir.times(player.currentGravity).times(1f - (dis / Player.gravityRange));
			speed.add(force.times(Game.updateTime));
		}

		position.add(speed.times(Game.updateTime));


		//Bounce
		if (position.x+speed.x*Game.updateTime < -Game.currentGame.gameWidth/2 || position.x+speed.x*Game.updateTime > Game.currentGame.gameWidth/2)
			speed.x *= -1;

		if (position.y+speed.y*Game.updateTime < -Game.currentGame.gameHeight/2 || position.y+speed.y*Game.updateTime > Game.currentGame.gameHeight/2)
			speed.y *= -1;
	}

	public void draw() {
		debreeSprite.setColor(Color.WHITE);
		debreeSprite.draw(position, new Vertex(1,1), rotation);
	}
}
