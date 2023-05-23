package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class FruitFightMain extends Game {
	public static final int SCR_WIDTH = 1280, SCR_HEIGHT = 720;
	public static final int GROUND = 156;

	SpriteBatch batch;
	OrthographicCamera camera;
	Vector3 touch;
	ScreenMainMenu screenMainMenu;
	ScreenGame screenGame;
	ScreenOptions screenOptions;
	ScreenAbout screenAbout;
	BitmapFont font, fontLarge;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
		touch = new Vector3();
		generateFont();
		screenMainMenu = new ScreenMainMenu(this);
		screenGame = new ScreenGame(this);
		screenOptions = new ScreenOptions(this);
		screenAbout = new ScreenAbout(this);
		setScreen(screenMainMenu);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
	void generateFont(){
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("pixelFont.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 35;
		parameter.color = new Color(249f/255f, 177f/255f, 69f/255f, 1);
		parameter.borderColor =  new Color(40f/255f, 17f/255f, 5f/255f, 1);
		parameter.borderWidth = 2;
		parameter.characters = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
		font = generator.generateFont(parameter);
		parameter.size = 55;
		fontLarge = generator.generateFont(parameter);
		generator.dispose();
	}
}
