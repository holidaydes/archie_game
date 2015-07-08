package com.holiday.archie.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.holiday.archie.core.Archie;
import com.holiday.archie.handlers.ArchieConstantVariables;

public class StartScreen implements Screen{

	private final Archie game;
	
	private Table table;
	private Table table2;
	private Skin skin;
	private ShapeRenderer shapeRenderer;
	
	private Sound backSound;
	private Sound selectSound;
	private Sound validateSound;
	private Music mainMusic;
	
	private int equipedSkin;
	private int equipedSword;
	private int equipedShield;
	
	private int itemSkin;
	private int itemSword;
	private int itemShield;
	
	private int health;
	private int attack;
	private int defense;
	private float stamina;
	private int massSkin;
	private int massSword;
	private int massShield;
	private int priceSkin;
	private int priceSword;
	private int priceShield;
	
	private Texture[] face;
	private Texture[] sword;
	private Texture[] shield;
	
	private TextButton play;
	private TextButton back;
	
	private ImageButton skinPrev;
	private ImageButton skinNext;
	private ImageButton swordPrev;
	private ImageButton swordNext;
	private ImageButton shieldPrev;
	private ImageButton shieldNext;
	
	private TextButton buySkin;
	private TextButton buySword;
	private TextButton buyShield;
	
	private TextButton equipSkin;
	private TextButton equipSword;
	private TextButton equipShield;
	
	private int mapNumber;
	
	public StartScreen(Archie game, int level){
		this.game = game;
		mapNumber = level;

		skin = new Skin(Gdx.files.internal("res/skins/uiskin.json"), new TextureAtlas(Gdx.files.internal("res/skins/uiskin.atlas")));
		table = new Table();
		table2 = new Table();
		backSound = Gdx.audio.newSound(Gdx.files.internal(ArchieConstantVariables.soundPath[1]));
		selectSound = Gdx.audio.newSound(Gdx.files.internal(ArchieConstantVariables.soundPath[2]));
		validateSound = Gdx.audio.newSound(Gdx.files.internal(ArchieConstantVariables.soundPath[0]));
		mainMusic = Gdx.audio.newMusic(Gdx.files.internal(ArchieConstantVariables.musicPath[0]));
		mainMusic.setLooping(true);
		
		play = new TextButton("Ready", skin);
		back = new TextButton("Main menu", skin);
		
		skinPrev = new ImageButton(skin.newDrawable("leftarrow"));
		skinNext = new ImageButton(skin.newDrawable("rightarrow"));
		swordPrev = new ImageButton(skin.newDrawable("leftarrow"));
		swordNext = new ImageButton(skin.newDrawable("rightarrow"));
		shieldPrev = new ImageButton(skin.newDrawable("leftarrow"));
		shieldNext = new ImageButton(skin.newDrawable("rightarrow"));
		
		buySkin = new TextButton("Buy", skin);
		buySword = new TextButton("Buy", skin);
		buyShield = new TextButton("Buy", skin);
		
		equipSkin = new TextButton("Equip", skin);
		equipSword = new TextButton("Equip", skin);
		equipShield = new TextButton("Equip", skin);
		
		face = new Texture[5];
		sword = new Texture[5];
		shield = new Texture[5];
		shapeRenderer = new ShapeRenderer();
		for(int i = 0; i < 5; i++){
			face[i] = new Texture(Gdx.files.internal(ArchieConstantVariables.facePath[i]));
			sword[i] = new Texture(Gdx.files.internal(ArchieConstantVariables.swordPath[i]));
			shield[i] = new Texture(Gdx.files.internal(ArchieConstantVariables.shieldPath[i]));
		}
		equipedSkin = game.pref.getEquipedSkin();
		equipedSword = game.pref.getEquipedSword();
		equipedShield = game.pref.getEquipedShield();
		
		itemSkin = equipedSkin;
		itemSword = equipedSword;
		itemShield = equipedShield;
		
		game.font36.setColor(Color.BLACK);
		game.font72.setColor(Color.BLACK);
	}

	@Override
	public void dispose() {
		for(Texture skin : face){
			skin.dispose();
		}
		for(Texture skin : sword){
			skin.dispose();
		}
		for(Texture skin : shield){
			skin.dispose();
		}
		game.stage.clear();
		table.clear();
		table2.clear();
		shapeRenderer.dispose();
		skin.dispose();
		backSound.dispose();
		selectSound.dispose();
		validateSound.dispose();
		mainMusic.dispose();
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float arg0) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		game.batch.draw(face[itemSkin], 25, 350);
		game.batch.draw(sword[itemSword], 250, 350);
		game.batch.draw(shield[itemShield], 475, 350);
		game.font36.draw(game.batch,"armour yourself from " + game.pref.getCoins() + " coins", 280, Archie.HEIGHT);
		game.font72.setColor(Color.BLACK);
		game.font72.drawMultiLine(game.batch, ArchieConstantVariables.levelName[mapNumber], 800, Archie.HEIGHT - 20);
		game.font36.drawMultiLine(game.batch, ArchieConstantVariables.tellATale[mapNumber],  800, Archie.HEIGHT - 170);
		game.font36.drawMultiLine(game.batch,"- mass: " + massSkin + "\n- stamina: " + stamina + "\n- health: " + health, 50, 350);
		game.font36.drawMultiLine(game.batch,"- mass: " + massSword + "\n- attack: " + attack, 275, 350);
		game.font36.drawMultiLine(game.batch,"- mass: " + massShield + "\n- defense: " + defense, 500, 350);
		game.batch.end();
		update();
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(new Color(0,0,0,0));
		shapeRenderer.rect(25, 350, 200, 200);
		shapeRenderer.rect(250, 350, 200, 200);
		shapeRenderer.rect(475, 350, 200, 200);
		shapeRenderer.line(760, 600, 760, 0);
		shapeRenderer.end();
		game.stage.act(arg0);
		game.stage.draw();
		
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		try{
			mainMusic.play();
		} catch(Exception e){
			e.getMessage();
		}
		
        skinPrev.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	selectSound.play();
            	if(itemSkin!=0){
            		itemSkin--;
            	}
            }
        });
        
        skinNext.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	selectSound.play();
            	if(itemSkin!=4){
            		itemSkin++;
            	}
            }
        });
        
        swordPrev.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	selectSound.play();
            	if(itemSword!=0){
            		itemSword--;
            	}
            }
        });
        
        swordNext.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	selectSound.play();
            	if(itemSword!=4){
            		itemSword++;
            	}
            }
        });
        
        shieldPrev.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	selectSound.play();
            	if(itemShield!=0){
            		itemShield--;
            	}
            }
        });
        
        shieldNext.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	selectSound.play();
            	if(itemShield!=4){
            		itemShield++;
            	}
            }
        });
        
        equipSkin.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	selectSound.play();
            	equipedSkin = itemSkin;
            }
        });
        
        equipSword.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	selectSound.play();
            	equipedSword = itemSword;
            }
        });
        
        equipShield.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	selectSound.play();
            	equipedShield = itemShield;
            }
        });
        
        buySkin.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	selectSound.play();
            	if(game.pref.getCoins() >= priceSkin){
            		game.pref.setEnabledSkin(itemSkin);
            		game.pref.setCoin(-priceSkin);
            	}
            }
        });
        
        buySword.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	selectSound.play();
            	if(game.pref.getCoins() >= priceSword){
            		game.pref.setEnabledSword(itemSword);
            		game.pref.setCoin(-priceSword);
            	}
            }
        });
        
        buyShield.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	selectSound.play();
            	if(game.pref.getCoins() >= priceShield){
            		game.pref.setEnabledShield(itemShield);
            		game.pref.setCoin(-priceShield);
            	}
            }
        });
        
        play.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	validateSound.play();
               	try {
    					Thread.sleep(790);
    			} catch (InterruptedException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    			}
            	game.pref.setEquipedSkin(equipedSkin);
            	game.pref.setEquipedSword(equipedSword);
            	game.pref.setEquipedShield(equipedShield);
                game.setScreen(new Play(game, mapNumber));
            }
        });
        
        back.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	backSound.play();
            	try {
					Thread.sleep(470);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	game.setScreen(new MainMenuScreen(game));
            }
        });
        
        table.top().left().padLeft(25).padTop(350);
        
        table.row().padTop(10);
        table.add(skinPrev).size(100,50);
        table.add(skinNext).size(100,50).padRight(25);
        table.add(swordPrev).size(100,50);
        table.add(swordNext).size(100,50).padRight(25);
        table.add(shieldPrev).size(100,50);
        table.add(shieldNext).size(100,50);
        table.row().padTop(50);
        table.add(equipSkin).size(100,50);
        table.add(buySkin).size(100,50).padRight(25);    
        table.add(equipSword).size(100,50);
        table.add(buySword).size(100,50).padRight(25);
        table.add(equipShield).size(100,50);
        table.add(buyShield).size(100,50);
        
        table.setFillParent(true);
        //table.setDebug(true);
        
        //table2
        table2.bottom().left();
        table2.row().padBottom(25);
        table2.add(back).size(150,60).left().padLeft(25);
        table2.add(play).size(150,60).expandX().right().padRight(25);

        table2.setFillParent(true);
        //table2.setDebug(true);
        
        game.stage.addActor(table);
        game.stage.addActor(table2);

        Gdx.input.setInputProcessor(game.stage);
	}
	
	public void update(){
		getSkinDetails(itemSkin);
		getSwordDetails(itemSword);
		getShieldDetails(itemShield);
		
		if(itemSkin == 0){
			skinPrev.setVisible(false);
		} else {
			skinPrev.setVisible(true);
		}
		if(itemSword == 0){
			swordPrev.setVisible(false);
		} else {
			swordPrev.setVisible(true);
		}
		if(itemShield == 0){
			shieldPrev.setVisible(false);
		} else {
			shieldPrev.setVisible(true);
		}
		
		if(itemSkin == 4){
			skinNext.setVisible(false);
		} else {
			skinNext.setVisible(true);
		}
		if(itemSword == 4){
			swordNext.setVisible(false);
		} else {
			swordNext.setVisible(true);
		}
		if(itemShield == 4){
			shieldNext.setVisible(false);
		} else {
			shieldNext.setVisible(true);
		}
		
		game.batch.begin();
		game.font36.draw(game.batch, getItemName(itemSkin), 30, 380);
		game.font36.draw(game.batch, getItemName(itemSword), 255, 380);
		game.font36.draw(game.batch, getItemName(itemShield), 480, 380);
		if(game.pref.getEnabledSkin(itemSkin)){
			equipSkin.setVisible(true);
			buySkin.setVisible(false);
		} else {
			equipSkin.setVisible(false);
			if(game.pref.getCoins() < priceSkin){
				buySkin.setVisible(false);
			} else {
				buySkin.setVisible(true);
			}
			game.font72.draw(game.batch, priceSkin + " coins", 25, 200);
		}
		
		if(game.pref.getEnabledSword(itemSword)){
			equipSword.setVisible(true);
			buySword.setVisible(false);
		} else {
			equipSword.setVisible(false);
			if(game.pref.getCoins() < priceSword){
				buySword.setVisible(false);
			} else {
				buySword.setVisible(true);
			}
			game.font72.draw(game.batch, priceSword + " coins", 250, 200);
		}
		
		if(game.pref.getEnabledShield(itemShield)){
			equipShield.setVisible(true);
			buyShield.setVisible(false);
		} else {
			equipShield.setVisible(false);
			if(game.pref.getCoins() < priceShield){
				buyShield.setVisible(false);
			} else {
				buyShield.setVisible(true);
			}
			game.font72.draw(game.batch, priceShield + " coins", 475, 200);
		}
		

		game.font36.drawMultiLine(game.batch, "equiped items:\n- skin: " + getItemName(equipedSkin) + "\n- sword: " + getItemName(equipedSword) + "\n- shield: " + getItemName(equipedShield), 800 , 300);
		game.batch.end();
	}
	
	public void getSkinDetails(int number){
		massSkin = ArchieConstantVariables.playerBodyMass[number];
		health = ArchieConstantVariables.playerHealth[number];
		stamina = ArchieConstantVariables.playerStamina[number];
		priceSkin = ArchieConstantVariables.skinPrice[number];
	}
	
	public void getSwordDetails(int number){
		massSword = ArchieConstantVariables.playerSwordMass[number];
		attack = ArchieConstantVariables.playerDamage[number];
		priceSword = ArchieConstantVariables.swordPrice[number];
	}
	
	public void getShieldDetails(int number){
		massShield = ArchieConstantVariables.playerShieldMass[number];
		defense = ArchieConstantVariables.playerDefense[number];
		priceShield = ArchieConstantVariables.shieldPrice[number];
	}
	
	public String getItemName(int number){
		return ArchieConstantVariables.equipmentName[number];
	}
}
