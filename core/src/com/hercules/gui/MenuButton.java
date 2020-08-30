package com.hercules.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MenuButton {

	private final TextButton button;

	public MenuButton(String name, Skin skin, Stage stage, float X, float Y) {

		button = new TextButton(name, skin, "default");

		button.setWidth(150);
		button.setHeight(50);

		button.setX(X);
		button.setY(Y);

		stage.addActor(button);
	}

	public void setButtonWidth(float width) {
		button.setWidth(width);
	}

	public void setButtonHeight(float Height) {
		button.setWidth(Height);
	}

	public TextButton getButton() {
		return this.button;
	}

	public boolean isOverX(float thresholdX) {

		if (Gdx.input.getX() > button.getX() + thresholdX
				&& Gdx.input.getX() < button.getX() + thresholdX + button.getWidth()) {

			return true;
		}

		else {

			return false;
		}
	}

	public boolean isOverY(float thresholdY) {

		if (Gdx.input.getY() > button.getY() + thresholdY
				&& Gdx.input.getY() < button.getY() + thresholdY + button.getHeight()) {

			return true;
		}

		else {

			return false;
		}
	}

	public boolean isOver(float thresholdX, float thresholdY) {

		return isOverX(thresholdX) && isOverY(thresholdY);
	}

	public static boolean isLeftClick() {

		return Gdx.input.isButtonPressed(Buttons.LEFT);
	}
}
