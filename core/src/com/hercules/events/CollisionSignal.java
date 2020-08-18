package com.hercules.events;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
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

				System.out.println("Player : Begin Contact :" + fa.getUserData());
			}
		}

		else if (fb.getUserData() != null && fb.getUserData().equals("sword")) {

			if (fa.getUserData() != null && fa.getUserData().equals("enemy")) {

				Player.hit = true;

				System.out.println("Player : Hit");
			}

			else {

				System.out.println("Player : Begin Contact :" + fa.getUserData());
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

				System.out.println("Player : On The Ground");
			}

			else {

				System.out.println("Player : Begin Contact :" + fa.getUserData());
			}
		}

		else if (fb.getUserData() != null && fb.getUserData().equals("sword")) {

			if (fa.getUserData() != null && fa.getUserData().equals("enemy")) {

				Player.hit = false;

				System.out.println("Player : Hit");
			}

			else {

				System.out.println("Player : Begin Contact :" + fa.getUserData());
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
