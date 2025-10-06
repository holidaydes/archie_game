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
import com.holiday.archie.elements.*;
import com.holiday.archie.managers.AudioManager;

import java.util.LinkedHashMap;

public class StartScreen implements Screen {

    private final Archie game;

    private final Table table;
    private final Table table2;
    private final Skin skin;
    private final ShapeRenderer shapeRenderer;

    private final Sound backSound;
    private final Sound selectSound;
    private final Sound validateSound;
    private final Music mainMusic;

    private PlayerBody equippedSkin;
    private PlayerSword equippedSword;
    private PlayerShield equippedShield;

    private PlayerBody itemSkin;
    private PlayerSword itemSword;
    private PlayerShield itemShield;

    private int health;
    private int damage;
    private int defense;
    private float stamina;
    private float massSkin;
    private float massSword;
    private float massShield;
    private int priceSkin;
    private int priceSword;
    private int priceShield;

    private final Texture[] face;
    private final Texture[] sword;
    private final Texture[] shield;

    private final TextButton play;
    private final TextButton back;

    private final ImageButton skinPrev;
    private final ImageButton skinNext;
    private final ImageButton swordPrev;
    private final ImageButton swordNext;
    private final ImageButton shieldPrev;
    private final ImageButton shieldNext;

    private final TextButton buySkin;
    private final TextButton buySword;
    private final TextButton buyShield;

    private final TextButton equipSkin;
    private final TextButton equipSword;
    private final TextButton equipShield;

    private final int mapNumber;

    private final LinkedHashMap<PlayerBody, Texture> playerBodyMap = new LinkedHashMap<>();
    private final LinkedHashMap<PlayerSword, Texture> playerSwordMap = new LinkedHashMap<>();
    private final LinkedHashMap<PlayerShield, Texture> playerShieldMap = new LinkedHashMap<>();

    public StartScreen(Archie game, int level) {
        this.game = game;
        mapNumber = level;

        skin = new Skin(Gdx.files.internal("skins/uiskin.json"), new TextureAtlas(Gdx.files.internal("skins/uiskin.atlas")));
        table = new Table();
        table2 = new Table();
        backSound = AudioManager.SOUND_MENU_BACK.loadSound();
        selectSound = AudioManager.SOUND_MENU_SELECT.loadSound();
        validateSound = AudioManager.SOUND_MENU_VALIDATE.loadSound();
        mainMusic = AudioManager.MUSIC_MAIN_MENU.loadMusic();
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

        for (PlayerBody body : PlayerBody.values()) {
            playerBodyMap.put(body, new Texture(Gdx.files.internal(body.texturePath)));
        }
        for (PlayerSword sword : PlayerSword.values()) {
            playerSwordMap.put(sword, new Texture(Gdx.files.internal(sword.texturePath)));
        }
        for (PlayerShield shield : PlayerShield.values()) {
            playerShieldMap.put(shield, new Texture(Gdx.files.internal(shield.texturePath)));
        }

        equippedSkin = PlayerBody.getById(game.pref.getEquippedBody());
        equippedSword = PlayerSword.getById(game.pref.getEquippedSword());
        equippedShield = PlayerShield.getById(game.pref.getEquippedShield());

        itemSkin = equippedSkin;
        itemSword = equippedSword;
        itemShield = equippedShield;

        game.font36.setColor(Color.BLACK);
        game.font72.setColor(Color.BLACK);
    }

    @Override
    public void dispose() {
        for (Texture skin : face) {
            if (skin != null) {
                skin.dispose();
            }
        }
        for (Texture skin : sword) {
            if (skin != null) {
                skin.dispose();
            }
        }
        for (Texture skin : shield) {
            if (skin != null) {
                skin.dispose();
            }
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
        game.batch.draw(playerBodyMap.get(itemSkin), 25, 350);
        game.batch.draw(playerSwordMap.get(itemSword), 250, 350);
        game.batch.draw(playerShieldMap.get(itemShield), 475, 350);
        game.font36.draw(game.batch, "armour yourself from " + game.pref.getCoins() + " coins", 280, Archie.HEIGHT);
        game.font72.setColor(Color.BLACK);
        Level level = Level.getById(mapNumber);
        game.font72.draw(game.batch, level.name, 800, Archie.HEIGHT - 20);
        game.font36.draw(game.batch, level.description, 800, Archie.HEIGHT - 170);
        game.font36.draw(game.batch, "- mass: " + massSkin + "\n- stamina: " + stamina + "\n- health: " + health, 50, 350);
        game.font36.draw(game.batch, "- mass: " + massSword + "\n- damage: " + damage, 275, 350);
        game.font36.draw(game.batch, "- mass: " + massShield + "\n- defense: " + defense, 500, 350);
        game.batch.end();
        update();
        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.setColor(new Color(0, 0, 0, 0));
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
        mainMusic.play();

        skinPrev.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectSound.play();
                if (itemSkin != PlayerBody.WOOD) {
                    itemSkin = PlayerBody.getById(itemSkin.id - 1);
                }
            }
        });

        skinNext.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectSound.play();
                if (itemSkin != PlayerBody.SKETCH) {
                    itemSkin = PlayerBody.getById(itemSkin.id + 1);
                }
            }
        });

        swordPrev.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectSound.play();
                if (itemSword != PlayerSword.WOOD) {
                    itemSword = PlayerSword.getById(itemSword.id - 1);
                }
            }
        });

        swordNext.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectSound.play();
                if (itemSword != PlayerSword.SKETCH) {
                    itemSword = PlayerSword.getById(itemSword.id + 1);
                }
            }
        });

        shieldPrev.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectSound.play();
                if (itemShield != PlayerShield.WOOD) {
                    itemShield = PlayerShield.getById(itemShield.id - 1);
                }
            }
        });

        shieldNext.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectSound.play();
                if (itemShield != PlayerShield.SKETCH) {
                    itemShield = PlayerShield.getById(itemShield.id + 1);
                }
            }
        });

        equipSkin.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectSound.play();
                equippedSkin = itemSkin;
            }
        });

        equipSword.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectSound.play();
                equippedSword = itemSword;
            }
        });

        equipShield.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectSound.play();
                equippedShield = itemShield;
            }
        });

        buySkin.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectSound.play();
                if (game.pref.getCoins() >= priceSkin) {
                    game.pref.setEnabledSkin(itemSkin.id);
                    game.pref.setCoin(-priceSkin);
                }
            }
        });

        buySword.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectSound.play();
                if (game.pref.getCoins() >= priceSword) {
                    game.pref.setEnabledSword(itemSword.id);
                    game.pref.setCoin(-priceSword);
                }
            }
        });

        buyShield.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectSound.play();
                if (game.pref.getCoins() >= priceShield) {
                    game.pref.setEnabledShield(itemShield.id);
                    game.pref.setCoin(-priceShield);
                }
            }
        });

        play.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                validateSound.play();
                try {
                    Thread.sleep(790);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                game.pref.setEquippedBody(equippedSkin.id);
                game.pref.setEquippedSword(equippedSword.id);
                game.pref.setEquippedShield(equippedShield.id);
                game.setScreen(new Play(game, mapNumber));
            }
        });

        back.addListener(new ClickListener() {
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
        table.add(skinPrev).size(100, 50);
        table.add(skinNext).size(100, 50).padRight(25);
        table.add(swordPrev).size(100, 50);
        table.add(swordNext).size(100, 50).padRight(25);
        table.add(shieldPrev).size(100, 50);
        table.add(shieldNext).size(100, 50);
        table.row().padTop(50);
        table.add(equipSkin).size(100, 50);
        table.add(buySkin).size(100, 50).padRight(25);
        table.add(equipSword).size(100, 50);
        table.add(buySword).size(100, 50).padRight(25);
        table.add(equipShield).size(100, 50);
        table.add(buyShield).size(100, 50);

        table.setFillParent(true);
        //table.setDebug(true);

        //table2
        table2.bottom().left();
        table2.row().padBottom(25);
        table2.add(back).size(150, 60).left().padLeft(25);
        table2.add(play).size(150, 60).expandX().right().padRight(25);

        table2.setFillParent(true);
        //table2.setDebug(true);

        game.stage.addActor(table);
        game.stage.addActor(table2);

        Gdx.input.setInputProcessor(game.stage);
    }

    public void update() {
        getSkinDetails(itemSkin);
        getSwordDetails(itemSword);
        getShieldDetails(itemShield);

        if (itemSkin == PlayerBody.WOOD) {
            skinPrev.setVisible(false);
        } else {
            skinPrev.setVisible(true);
        }
        if (itemSword == PlayerSword.WOOD) {
            swordPrev.setVisible(false);
        } else {
            swordPrev.setVisible(true);
        }
        if (itemShield == PlayerShield.WOOD) {
            shieldPrev.setVisible(false);
        } else {
            shieldPrev.setVisible(true);
        }

        if (itemSkin == PlayerBody.SKETCH) {
            skinNext.setVisible(false);
        } else {
            skinNext.setVisible(true);
        }
        if (itemSword == PlayerSword.SKETCH) {
            swordNext.setVisible(false);
        } else {
            swordNext.setVisible(true);
        }
        if (itemShield == PlayerShield.SKETCH) {
            shieldNext.setVisible(false);
        } else {
            shieldNext.setVisible(true);
        }

        game.batch.begin();
        game.font36.draw(game.batch, itemSkin.name, 30, 380);
        game.font36.draw(game.batch, itemSword.name, 255, 380);
        game.font36.draw(game.batch, itemShield.name, 480, 380);
        if (game.pref.getEnabledSkin(itemSkin.id)) {
            equipSkin.setVisible(true);
            buySkin.setVisible(false);
        } else {
            equipSkin.setVisible(false);
            if (game.pref.getCoins() < priceSkin) {
                buySkin.setVisible(false);
            } else {
                buySkin.setVisible(true);
            }
            game.font72.draw(game.batch, priceSkin + " coins", 25, 200);
        }

        if (game.pref.getEnabledSword(itemSword.id)) {
            equipSword.setVisible(true);
            buySword.setVisible(false);
        } else {
            equipSword.setVisible(false);
            if (game.pref.getCoins() < priceSword) {
                buySword.setVisible(false);
            } else {
                buySword.setVisible(true);
            }
            game.font72.draw(game.batch, priceSword + " coins", 250, 200);
        }

        if (game.pref.getEnabledShield(itemShield.id)) {
            equipShield.setVisible(true);
            buyShield.setVisible(false);
        } else {
            equipShield.setVisible(false);
            if (game.pref.getCoins() < priceShield) {
                buyShield.setVisible(false);
            } else {
                buyShield.setVisible(true);
            }
            game.font72.draw(game.batch, priceShield + " coins", 475, 200);
        }


        game.font36.draw(game.batch, "equipped items:\n- skin: " + equippedSkin.name + "\n- sword: " + equippedSword.name + "\n- shield: " + equippedShield.name, 800, 300);
        game.batch.end();
    }

    public void getSkinDetails(PlayerBody body) {
        massSkin = body.mass;
        health = body.health;
        stamina = body.stamina;
        priceSkin = body.price;
    }

    public void getSwordDetails(PlayerSword sword) {
        massSword = sword.mass;
        damage = sword.damage;
        priceSword = sword.price;
    }

    public void getShieldDetails(PlayerShield shield) {
        massShield = shield.mass;
        defense = shield.defense;
        priceShield = shield.price;
    }
}
