/** @author Z. Mohamed Osama */

package com.hercules.events;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.hercules.init.Demon;
import com.hercules.init.Player;

public class CollisionSignal implements ContactListener {

	@Override
	public void beginContact(Contact contact) {

		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();

		if (fb.getUserData() != null && fb.getUserData().equals("player")) {

			if (fa.getUserData() != null && fa.getUserData().equals("Ground")) {

				Player.onGround = true;

				System.out.println("Player : On The Ground");
			}

			else {
				// do something
			}
		}

		if (fb.getUserData() != null && fb.getUserData().equals("enemy")) {

			if (fa.getUserData() != null && fa.getUserData().equals("Ground")) {

				Demon.onGround = true;

				System.out.println("Enemy: On The Ground");
			}

			else {
				// do something
			}
		}

		if (fb.getUserData() != null && fb.getUserData().equals("sword")) {

			if (fa.getUserData() != null && fa.getUserData().equals("enemy")) {

				Player.hit = true;

				System.out.println("Player : Hit");
			}

			else {

				// do something
			}
		}

		if (fb.getUserData() != null && (fb.getUserData().equals("player") || fb.getUserData().equals("sword"))) {

			if (fa.getUserData() != null && fa.getUserData().equals("Bounds")) {

				Player.onBound = true;

				System.out.println("Player : Bound");
			}

			else {

				// do something
			}
		}

		if (fb.getUserData() != null && (fb.getUserData().equals("enemy") || fb.getUserData().equals("enemy-weapon"))) {

			if (fa.getUserData() != null && fa.getUserData().equals("Bounds")) {

				Demon.onBound = true;
			}

			else {

				// do something
			}
		}
	}

	@Override
	public void endContact(Contact contact) {

		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		if (fb.getUserData() != null && fb.getUserData().equals("player")) {

			if (fa.getUserData() != null && fa.getUserData().equals("Ground")) {

				Player.onGround = false;

			}

			else {
				// do something
			}
		}

		if (fb.getUserData() != null && fb.getUserData().equals("enemy")) {

			if (fa.getUserData() != null && fa.getUserData().equals("Ground")) {

				Demon.onGround = false;

			}

			else {
				// do something
			}
		}

		if (fb.getUserData() != null && fb.getUserData().equals("sword")) {

			if (fa.getUserData() != null && fa.getUserData().equals("enemy")) {

				Player.hit = false;

			}

			else {

				// do something
			}
		}

		if (fb.getUserData() != null && (fb.getUserData().equals("player") || fb.getUserData().equals("sword"))) {

			if (fa.getUserData() != null && fa.getUserData().equals("Bounds")) {

				Player.onBound = false;
			}

			else {

				// do something
			}
		}

		if (fb.getUserData() != null && (fb.getUserData().equals("enemy") || fb.getUserData().equals("enemy-weapon"))) {

			if (fa.getUserData() != null && fa.getUserData().equals("Bounds")) {

				Demon.onBound = false;
			}

			else {

				// do something
			}
		}
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
	}

}
