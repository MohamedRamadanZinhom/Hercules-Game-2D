package com.hercules.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MenuButtons {


    private final TextButton button;


    public MenuButtons(String name, Skin skin, Stage stage ,float X,float Y){

        button=new TextButton(name,skin,"default");
        button.setWidth(150);
        button.setHeight(50);
        button.setX(X);
        button.setY(Y);
       stage.addActor(button);
        Gdx.input.setInputProcessor(stage);

    }
    public void SetButtonWidth(float width){
        button.setWidth(width);
    }
    public void SetButtonHeight(float Height){
        button.setWidth(Height);
    }


    public TextButton getButton(){
        return this.button;
    }

}
