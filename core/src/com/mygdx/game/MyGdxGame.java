package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.screens.HenrySandboxScreen;
import com.mygdx.game.screens.MainMenuScreen;

public class MyGdxGame extends Game implements ApplicationListener {

//	Skin skin;
//	Stage stage;
//	Texture texture1;
//	Texture texture2;
//	Label fpsLabel;

	public SpriteBatch batch;
	public BitmapFont font;

	@Override
	public void create () {

		batch = new SpriteBatch();
		//Use LibGDX's default Arial font.
		font = new BitmapFont();
		this.setScreen(new MainMenuScreen(this));


//		skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
//		texture1 = new Texture(Gdx.files.internal("badlogicsmall.jpg"));
//		texture2 = new Texture(Gdx.files.internal("badlogic.jpg"));
//		TextureRegion image = new TextureRegion(texture1);
//		TextureRegion imageFlipped = new TextureRegion(image);
//		imageFlipped.flip(true, true);
//		TextureRegion image2 = new TextureRegion(texture2);
//		// stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false, new PolygonSpriteBatch());
//		stage = new Stage(new ScreenViewport());
//		Gdx.input.setInputProcessor(stage);
//
//		// Group.debug = true;
//
//		ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle(skin.get(Button.ButtonStyle.class));
//		style.imageUp = new TextureRegionDrawable(image);
//		style.imageDown = new TextureRegionDrawable(imageFlipped);
//		ImageButton iconButton = new ImageButton(style);
//
//		Button buttonMulti = new TextButton("Henry's\nSandbox", skin, "toggle");
//		Button imgButton = new Button(new Image(image), skin);
//		Button imgToggleButton = new Button(new Image(image), skin, "toggle");
//
//		Label myLabel = new Label("this is some text.", skin);
//		myLabel.setWrap(true);
//
//		// my own table
//		Table table = new Table();
//
//		table.row();
//		table.add(imgButton).expandX();
//		table.add(buttonMulti).pad(10);
//
//		table.row();
//		table.add(imgToggleButton).width(200);
//		table.add(iconButton).expandX();
//
//		table.setFillParent(true);
//		stage.addActor(table);
//
//		final MyGdxGame backInstance = this;
//		iconButton.addListener(new ChangeListener() {
//			public void changed (ChangeEvent event, Actor actor) {
//				setScreen(new HenrySandboxScreen(backInstance));
//			}
//		});

//		Table t = new Table();
//		t.row();
//		t.add(myLabel);
//
//		t.layout();

//		final CheckBox checkBox = new CheckBox(" Continuous rendering", skin);
//		checkBox.setChecked(true);
//		final Slider slider = new Slider(0, 10, 1, false, skin);
//		slider.setAnimateDuration(0.3f);
//		TextField textfield = new TextField("", skin);
//		textfield.setMessageText("Click here!");
//		final SelectBox dropdown = new SelectBox(skin);
//		dropdown.addListener(new ChangeListener() {
//			public void changed (ChangeEvent event, Actor actor) {
//				System.out.println(dropdown.getSelected());
//			}
//		});
//		dropdown.setItems("Android1", "Windows1", "Linux1", "OSX1", "Android2", "Windows2", "Linux2", "OSX2", "Android3",
//				"Windows3", "Linux3", "OSX3", "Android4", "Windows4", "Linux4", "OSX4", "Android5", "Windows5", "Linux5", "OSX5",
//				"Android6", "Windows6", "Linux6", "OSX6", "Android7", "Windows7", "Linux7", "OSX7");
//		dropdown.setSelected("Linux6");
//		Image imageActor = new Image(image2);
//		ScrollPane scrollPane = new ScrollPane(imageActor);
//		List list = new List(skin);
//		list.setItems(listEntries);
//		list.getSelection().setMultiple(true);
//		list.getSelection().setRequired(false);
//		// list.getSelection().setToggle(true);
//		ScrollPane scrollPane2 = new ScrollPane(list, skin);
//		scrollPane2.setFlickScroll(false);
//		SplitPane splitPane = new SplitPane(scrollPane, scrollPane2, false, skin, "default-horizontal");
//		fpsLabel = new Label("fps:", skin);
//
//		// configures an example of a TextField in password mode.
//		final Label passwordLabel = new Label("Textfield in password mode: ", skin);
//		final TextField passwordTextField = new TextField("", skin);
//		passwordTextField.setMessageText("password");
//		passwordTextField.setPasswordCharacter('*');
//		passwordTextField.setPasswordMode(true);
//
//		// window.debug();
//		Window window = new Window("Dialog", skin);
//		window.getButtonTable().add(new TextButton("X", skin)).height(window.getPadTop());
//		window.setPosition(0, 0);
//		window.defaults().spaceBottom(10);
//		window.row().fill().expandX();
//		window.add(iconButton);
//		window.add(buttonMulti);
//		window.add(imgButton);
//		window.add(imgToggleButton);
//		window.row();
//		window.add(checkBox);
//		window.add(slider).minWidth(100).fillX().colspan(3);
//		window.row();
//		window.add(dropdown);
//		window.add(textfield).minWidth(100).expandX().fillX().colspan(3);
//		window.row();
//		window.add(splitPane).fill().expand().colspan(4).maxHeight(200);
//		window.row();
//		window.add(passwordLabel).colspan(2);
//		window.add(passwordTextField).minWidth(100).expandX().fillX().colspan(2);
//		window.row();
//		window.add(fpsLabel).colspan(4);
//		window.pack();
//
//		// stage.addActor(new Button("Behind Window", skin));
//		stage.addActor(window);
//
//		textfield.setTextFieldListener(new TextField.TextFieldListener() {
//			public void keyTyped (TextField textField, char key) {
//				if (key == '\n') textField.getOnscreenKeyboard().show(false);
//			}
//		});
//
//		slider.addListener(new ChangeListener() {
//			public void changed (ChangeEvent event, Actor actor) {
//				Gdx.app.log("UITest", "slider: " + slider.getValue());
//			}
//		});
//
//		iconButton.addListener(new ChangeListener() {
//			public void changed (ChangeEvent event, Actor actor) {
//
//				new Dialog("Some Dialog", skin, "dialog") {
//					protected void result (Object object) {
//						System.out.println("Chosen: " + object);
//					}
//				}.text("Are you enjoying this demo?").button("Yes", true).button("No", false).key(Input.Keys.ENTER, true)
//						.key(Input.Keys.ESCAPE, false).show(stage);
//
//				Gdx.app.log("MyTag", "my informative message");
//				Gdx.app.error("MyTag", "my error message");
//				Gdx.app.debug("MyTag", "my debug message");
//			}
//		});
//
//		checkBox.addListener(new ChangeListener() {
//			public void changed (ChangeEvent event, Actor actor) {
//				Gdx.graphics.setContinuousRendering(checkBox.isChecked());
//			}
//		});


		// stage debug
		//stage.setDebugTableUnderMouse(true);
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void resize (int width, int height) {

	}

	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}



	public void resume () {
	}


	public void pause () {
	}


}
