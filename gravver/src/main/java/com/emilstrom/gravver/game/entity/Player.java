package com.emilstrom.gravver.game.entity;

import com.emilstrom.gravver.game.Art;
import com.emilstrom.gravver.game.Game;
import com.emilstrom.gravver.game.Map;
import com.emilstrom.gravver.game.PlayerController;
import com.emilstrom.gravver.helper.Color;
import com.emilstrom.gravver.helper.Sprite;
import com.emilstrom.gravver.helper.Vertex;

/**
 * Created by Emil on 2014-04-02.
 */
public class Player extends Actor {
	static final float idleRotationSpeed = 30f, gravityRotationSpeed = 360f, movementSpeed = 5f, acceleration = 8f, gravityAdd = 1.8f;
	public static final float gravityRange = 6f, gravityFactor = 9f;


	PlayerController controller;

	Sprite playerSprite;
	float rotation, currentGravity = 0f;

	public Player(Map m) {
		super(m);
		playerSprite = new Sprite(Art.player);
		controller = new PlayerController();

		size = 1f;
		maxVelocity = 8f;
	}

	public void logic() {
		super.logic();

		controller.logic();

		//Movement
		Vertex veloIn = controller.getVelocityInput();

		if (veloIn.x > 0) {
			if (velocity.x < veloIn.x*movementSpeed) velocity.x += acceleration * Game.updateTime;
			//else velocity.x -= velocity.x * 1.7 * Game.updateTime;
		}
		if (veloIn.x < 0) {
			if (velocity.x > veloIn.x*movementSpeed) velocity.x -= acceleration * Game.updateTime;
			//else velocity.x -= velocity.x * Game.updateTime;
		}

		if (veloIn.y > 0) {
			if (velocity.y < veloIn.y*movementSpeed) velocity.y += acceleration * Game.updateTime;
			//else velocity.y -= velocity.y * 1.7 * Game.updateTime;
		}
		if (veloIn.y < 0) {
			if (velocity.y > veloIn.y*movementSpeed) velocity.y -= acceleration * Game.updateTime;
			//else velocity.y -= velocity.y * Game.updateTime;
		}

		position.add(velocity.times(Game.updateTime));

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
		super.draw();

		playerSprite.setColor(Color.WHITE);
		playerSprite.draw(position, new Vertex(1, 1), rotation);

		controller.draw();
	}
}
