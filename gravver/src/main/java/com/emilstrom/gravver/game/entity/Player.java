package com.emilstrom.gravver.game.entity;

import com.emilstrom.gravver.game.Art;
import com.emilstrom.gravver.game.Game;
import com.emilstrom.gravver.game.PlayerController;
import com.emilstrom.gravver.helper.Color;
import com.emilstrom.gravver.helper.Sprite;
import com.emilstrom.gravver.helper.Vertex;

/**
 * Created by Emil on 2014-04-02.
 */
public class Player extends Actor {
	static final float idleRotationSpeed = 10f, gravityRotationSpeed = 360f, movementSpeed = 9f, gravityAdd = 1.8f;
	public static final float gravityRange = 6f, gravityFactor = 15f;


	PlayerController controller;

	Sprite playerSprite;
	float rotation, currentGravity = 0f;

	public Player() {
		super();
		playerSprite = new Sprite(Art.blank);
		controller = new PlayerController();
	}

	public void logic() {
		controller.logic();

		//Movement
		position.add(controller.getVelocityInput().times(movementSpeed * Game.updateTime));

		//Gravity
		if (controller.getGravityInput()) {
			currentGravity = Math.min(currentGravity + gravityAdd * Game.updateTime, 1f);
		} else {
			currentGravity = Math.max(currentGravity - gravityAdd * Game.updateTime, 0f);
		}

		//Rotation
		rotation += (idleRotationSpeed + gravityRotationSpeed * currentGravity) * Game.updateTime;
	}

	public void draw() {
		playerSprite.setColor(Color.WHITE);
		playerSprite.draw(position, new Vertex(1, 1), rotation);

		controller.draw();
	}
}
