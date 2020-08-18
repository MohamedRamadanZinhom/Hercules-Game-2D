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

			if (fa.getUserData() != null && fa.getUserData().equals("DynamicGround")) {

				Player.OnGround = true;

				System.out.println("Player : On The Ground");
			}

			else {

				System.out.println("Player : Begin Contact :" + fa.getUserData());
			}
		}
	}

	@Override
	public void endContact(Contact contact) {
		System.out.println("End :");
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub

	}

}
