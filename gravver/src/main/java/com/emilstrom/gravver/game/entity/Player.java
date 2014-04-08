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
	static final float idleRotationSpeed = 10f, gravityRotationSpeed = 360f, movementSpeed = 9f, maxGravity = 20f, gravityAdd = 17f;
	public static final float gravityRange = 6f;


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
			currentGravity = Math.min(currentGravity + gravityAdd * Game.updateTime, maxGravity);
		} else {
			currentGravity = Math.max(currentGravity - gravityAdd * 3 * Game.updateTime, 0f);
		}

		//Rotation
		rotation += (idleRotationSpeed + gravityRotationSpeed * (currentGravity / maxGravity)) * Game.updateTime;
	}

	public void draw() {
		playerSprite.setColor(Color.WHITE);
		playerSprite.draw(position, new Vertex(1, 1), rotation);

		controller.draw();
	}
}
