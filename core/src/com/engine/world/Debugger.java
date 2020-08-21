package com.engine.world;

import java.util.Random;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.OrderedMap;
import com.engine.ui.DialogBuilder;

public class Debugger {

	private final String title = "Physics Simulation State";

	private DialogBuilder debugDialog;

	private static Color[] color = { Color.RED, Color.BLUE, Color.GOLD, Color.GREEN, Color.WHITE, Color.SKY,
			Color.YELLOW };

	private static Random randomGen = new Random();

	protected Debugger() {

		this.debugDialog = new DialogBuilder(title);

	}

	protected Debugger(float posX, float posY) {

		this.debugDialog = new DialogBuilder(title, posX, posY);

	}

	public void getAllFromBody2D(boolean randomColor, boolean ingoreStatic) {

		for (String key : Body2D.bodies.keySet()) {

			Vector<Body> bodies = Body2D.bodies.get(key);
			int colorChoice = 4;

			if (randomColor) {

				colorChoice = randomGen.nextInt(color.length);
			}

			for (int i = 0; i < bodies.size(); i++) {

				if (ingoreStatic && bodies.get(i).getType() == BodyType.StaticBody) {

					continue;
				}

				boolean setName = false;

				if (bodies.get(i).getType() != BodyType.StaticBody) {
					setName = true;
				}

				OrderedMap<String, String> info = this.getBodyState(bodies.get(i));
				info.put("Name", key);

				this.debugDialog.tableFromOrderedMap(info, color[colorChoice], setName);
			}
		}
	}

	private OrderedMap<String, String> getBodyState(Body body) { // HashMap<String, Vector<Body>>

		OrderedMap<String, String> info = new OrderedMap<>();

		if (body.getUserData() != null) {

			String data = null;

			if (body.getUserData() instanceof Float) {

				data = Float.toString((float) body.getUserData());
			}

			else if (body.getUserData() instanceof Integer) {

				data = Integer.toString((int) body.getUserData());
			}

			else {

				data = body.getUserData().toString();
			}

			info.put("User Data :", data);
		}

		info.put("Body Type", body.getType().toString());

		if (body.getType() == BodyType.DynamicBody || body.getType() == BodyType.KinematicBody) {

			info.put("Position", body.getPosition().scl(World2D.GU).toString());
			info.put("Orientation", body.getTransform().getOrientation().toString());

			if (body.getLinearVelocity().x > 0 || body.getLinearVelocity().y > 0) {

				info.put("Linear Velocity", body.getLinearVelocity().scl(World2D.GU).toString());
			}

			if (body.getAngularVelocity() > 0) {

				info.put("Angular Velocity", Float.toString(body.getAngularVelocity() * World2D.GU));
			}

		}

		if (body.getType() == BodyType.DynamicBody) {

			info.put("Local Center of Mass", body.getLocalCenter().scl(World2D.GU).toString());
			info.put("Gravity Scale", Float.toString(body.getGravityScale()));
		}

		return info;
	}

	public void act() {

		float deltaTime = Gdx.graphics.getDeltaTime();

		this.debugDialog.getStage().act(deltaTime);
	}

	public void render() {

		this.debugDialog.renderDialog();

	}

	public void clear() {

		this.debugDialog.dialog.clear();

	}

	public void reset() {

		this.debugDialog.dialog.reset();
	}

	public void dispose() {

		this.debugDialog.dispose();
	}

}
