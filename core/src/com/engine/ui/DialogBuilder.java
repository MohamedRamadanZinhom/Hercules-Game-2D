package com.engine.ui;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.OrderedMap;

public class DialogBuilder {

	private final String defaultFormat = "./UI/ui-skin/uiskin.json";

	private Skin skin;
	private Stage stage;

	public Dialog dialog;

	private int countActor;

	/**
	 * @param title : String - Dialog Title
	 */
	public DialogBuilder(String title) {

		this.skin = new Skin(Gdx.files.internal(this.defaultFormat));

		this.stage = new Stage();

		Gdx.input.setInputProcessor(this.stage);

		this.dialog = new Dialog(title, skin);
		this.dialog.show(this.stage);

		this.countActor = 0;
	}

	public DialogBuilder(String title, float posX, float posY) {

		this.skin = new Skin(Gdx.files.internal(this.defaultFormat));

		this.stage = new Stage();

		this.stage.getViewport().getCamera().position.x = posX;
		this.stage.getViewport().getCamera().position.y = posY;

		Gdx.input.setInputProcessor(this.stage);

		this.dialog = new Dialog(title, skin);
		this.dialog.show(this.stage);

		this.countActor = 0;
	}

	/**
	 * @param title    : String - Dialog Title
	 * @param skinFile : String - Path of *.json (Skin) - {@link #defaultFormat}
	 */
	public DialogBuilder(String title, String skinFile) {

		this.skin = new Skin(Gdx.files.internal(skinFile));

		this.stage = new Stage();

		Gdx.input.setInputProcessor(stage);

		this.dialog = new Dialog(title, skin);
		this.dialog.show(this.stage);

		this.countActor = 0;

	}

	public Table getTable() {

		return new Table(this.skin);
	}

	/**
	 * @param orderedMap: OrderedMap<String, String>
	 * @param color       : String - color name from (skinFile) -
	 *                    {@link #DialogBuilder(String, String)}
	 */
	public void tableFromOrderedMap(OrderedMap<String, String> orderedMap, Color color, boolean setName) {

		Table table = new Table(this.skin);

		if (setName) {

			table.setName(Integer.toString(this.countActor));
			this.countActor += 1;
		}

		for (String key : orderedMap.keys()) {

			String value = orderedMap.get(key);

			Label label = new Label(key + "  :  " + value, this.skin);
			label.setColor(color);

			table.add(label);
			table.row();

			table.setColor(color);
		}

		table.add("------------------------");
		table.row();

		this.dialog.add(table);
		this.dialog.show(this.stage);
	}

	/**
	 * @param orderedMap: OrderedMap<String, String>
	 * @param color       : Color
	 */
	public void tableFromOrderedMap(OrderedMap<String, String> orderedMap, String color, boolean setName) {

		Table table = new Table(this.skin);

		if (setName) {

			table.setName(Integer.toString(this.countActor));
			this.countActor += 1;
		}

		for (String key : orderedMap.keys()) {

			String value = orderedMap.get(key);

			Label label = new Label(key + ":" + value, this.skin);
			label.setColor(skin.getColor(color));

			table.add(label);
			table.row();

		}

		this.dialog.add(table);
		this.dialog.show(this.stage);
	}

	/**
	 * @param orderedMap: OrderedMap<String, String>
	 */
	public void tableFromOrderedMap(OrderedMap<String, Vector<String>> orderedMap) {

		for (String key : orderedMap.keys()) {

			Vector<String> values = orderedMap.get(key);

			Table table = new Table(this.skin);

			for (int i = 0; i < values.size(); i++) {

				Label label = new Label(key + ":" + values.get(i), this.skin);

				table.add(label);
				table.row();

			}

			this.dialog.add(table);
			this.dialog.show(this.stage);
		}

	}

	public Stage getStage() {

		return this.stage;
	}

	public void renderDialog() {

		this.stage.draw();
	}

	public void dispose() {

		this.stage.dispose();
	}

}
