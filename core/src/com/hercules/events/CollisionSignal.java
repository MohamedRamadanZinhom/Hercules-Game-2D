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

			String[] data_1 = ((String) fa.getUserData()).split("-");
			String[] data_2 = ((String) fb.getUserData()).split("-");

			String typeId_1 = data_1[0];
			String typeId_2 = data_2[0];

			if (fa.getUserData().equals("Ground") && (typeId_2.equals("player") || typeId_2.equals("demon"))
					&& data_2.length == 1) {

				CharacterStatus.statusRepo.get(typeId_2).setOnGround(true);

			}

			// data.length > 1: data[1] = weaponName
			if (data_2.length > 1 && fa.getUserData().equals("Bounds")
					&& (typeId_2.equals("player") || typeId_2.equals("demon"))) {

				CharacterStatus.statusRepo.get(typeId_1).setOnBound(true);

			}

			// data.length > 1: data[1] = weaponName
			// true if : (player ---- hit ----> enemy | enemy ---- hit ----> player)
			if (data_1.length > 1 && (typeId_1.equals("player") || typeId_1.equals("demon"))) {

				CharacterStatus.statusRepo.get(typeId_1).setHit(true);

			}

			// data.length > 1: data[1] = weaponName
			// true if : (player ---- hit ----> enemy | enemy ---- hit ----> player)
			if (data_2.length > 1 && (typeId_2.equals("player") || typeId_2.equals("demon"))) {

				CharacterStatus.statusRepo.get(typeId_2).setHit(true);

			}
		}

	}

	@Override
	public void endContact(Contact contact) {

		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();

		if (fa.getUserData() != null && fb.getUserData() != null && fa.getUserData() instanceof String
				&& fb.getUserData() instanceof String) {

			String[] data_1 = ((String) fa.getUserData()).split("-");
			String[] data_2 = ((String) fb.getUserData()).split("-");

			String typeId_1 = data_1[0];
			String typeId_2 = data_2[0];

			if (fa.getUserData().equals("Ground") && (typeId_2.equals("player") || typeId_2.equals("demon"))
					&& data_2.length == 1) {

				CharacterStatus.statusRepo.get(typeId_2).setOnGround(false);

			}

			// data.length > 1: data[1] = weaponName
			if (data_2.length > 1 && fa.getUserData().equals("Bounds")
					&& (typeId_2.equals("player") || typeId_2.equals("demon"))) {

				CharacterStatus.statusRepo.get(typeId_1).setOnBound(false);

			}

			// data.length > 1: data[1] = weaponName
			// true if : (player ---- hit ----> enemy | enemy ---- hit ----> player)
			if (data_1.length > 1 && (typeId_1.equals("player") || typeId_1.equals("demon"))) {

				CharacterStatus.statusRepo.get(typeId_1).setHit(false);

			}

			// data.length > 1: data[1] = weaponName
			// true if : (player ---- hit ----> enemy | enemy ---- hit ----> player)
			if (data_2.length > 1 && (typeId_2.equals("player") || typeId_2.equals("demon"))) {

				CharacterStatus.statusRepo.get(typeId_2).setHit(false);

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
