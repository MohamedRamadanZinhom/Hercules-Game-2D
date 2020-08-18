package com.hercules.events;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class CollisionSignal implements ContactListener {

	@Override
	public void beginContact(Contact contact) {

		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();

		System.out.println("beginContact :" + fa.getUserData() + ", " + fb.getUserData());
//		if (fa.getUserData() != null && fa.getUserData() == "player") {
//
//			if (fb.getUserData() != null && fb.getUserData() == "Ground_Mask") {
//
//				System.out.println("Player : On The Ground");
//			}
//
//			else {
//
//				System.out.println("Player : Begin Contact");
//			}
//		}
	}

	@Override
	public void endContact(Contact contact) {

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
