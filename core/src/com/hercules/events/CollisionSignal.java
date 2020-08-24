/** @author Z. Mohamed Osama */

package com.hercules.events;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.hercules.init.CharacterStatus;

public class CollisionSignal implements ContactListener {

	@Override
	public void beginContact(Contact contact) {

		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();

		if (fa.getUserData() != null && fb.getUserData() != null && fa.getUserData() instanceof String
				&& fb.getUserData() instanceof String) {

			String[] data = ((String) fb.getUserData()).split("-");

			String typeId = data[0];

			if (fa.getUserData().equals("Ground") && (typeId.equals("player") || typeId.equals("enemy"))
					&& data.length == 1) {

				CharacterStatus.statusRepo.get(typeId).setOnGround(true);

			}

			// data.length > 1: data[1] = weaponName
			if (data.length > 1 && fa.getUserData().equals("Bounds")
					&& (typeId.equals("player") || typeId.equals("enemy"))) {

				CharacterStatus.statusRepo.get(typeId).setOnBound(true);

			}

			// data.length > 1: data[1] = weaponName
			// true if : (player ---- hit ----> enemy | enemy ---- hit ----> player)
			if (data.length > 1 && fa.getUserData().equals(data[1])
					&& (typeId.equals("player") || typeId.equals("enemy"))
					&& CharacterStatus.statusRepo.get(typeId).getCurrentMode().equals("attack")) {

				CharacterStatus.statusRepo.get(typeId).setHit(true);
			}
		}

	}

	@Override
	public void endContact(Contact contact) {

		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();

		if (fb.getUserData() != null && fa.getUserData() != null && fa.getUserData() instanceof String
				&& fb.getUserData() instanceof String) {

			String[] data = ((String) fb.getUserData()).split("-");

			String typeId = data[0];

			if (fa.getUserData().equals("Ground") && (typeId.equals("player") || typeId.equals("enemy"))) {

				CharacterStatus.statusRepo.get(typeId).setOnGround(false);

			}

			// data.length > 1: data[1] = weaponName
			if (fa.getUserData().equals("Bounds") && (typeId.equals("player") || typeId.equals("enemy"))) {

				CharacterStatus.statusRepo.get(typeId).setOnBound(false);

			}

			if (data.length > 1 && fa.getUserData().equals(data[1])
					&& (typeId.equals("player") || typeId.equals("enemy"))) {

				CharacterStatus.statusRepo.get(typeId).setHit(false);
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
