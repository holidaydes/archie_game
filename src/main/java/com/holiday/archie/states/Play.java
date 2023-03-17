package com.holiday.archie.states;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.holiday.archie.core.Archie;
import com.holiday.archie.elements.*;
import com.holiday.archie.entities.*;
import com.holiday.archie.handlers.ArchieBackgroundAnimation;
import com.holiday.archie.handlers.ArchieControllerProcessor;
import com.holiday.archie.handlers.ArchieBoundedCamera;
import com.holiday.archie.handlers.ArchieConstantVariables;
import com.holiday.archie.handlers.ArchieContactListener;
import com.holiday.archie.handlers.ArchieInputProcessor;
import com.holiday.archie.managers.AudioManager;

import static com.holiday.archie.elements.PathVariables.*;

public class Play implements Screen {

    private final Archie game;
    private boolean debugModeOn;
    public boolean cheatModeOn;
    private boolean invincibleCheat;

    public enum State {Running, Paused, Quit}

    public State state = State.Running;
    private final Level level;

    private final PlayerBody selectedPlayerBody;
    private final PlayerSword selectedPlayerSword;
    private final PlayerShield selectedPlayerShield;
    private int collectedCoins;
    public int lifeBar;

    public Body sword;
    public Body shield;

    private final int playerSwordDamage;
    private int enemyIdentity;

    //public Array<Body> enemySword;
    private final Random random;
    private long lastEnemySwordTime;
    private long enemySwordTime;

    //box2d variables
    private final World world;
    private final BodyDef bodyDef;
    private final FixtureDef fixtureDef;

    //tiled map variables
    private final TiledMap map;
    private final OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private final float tileSize;
    private final TiledMapTileLayer layer;

    //cameras
    private final Box2DDebugRenderer b2dr;
    private final ArchieBoundedCamera camera;
    private final ArchieBoundedCamera b2camera;

    //controllers
    private final int[] keyboard;
    private final int[] controller;

    //movement variables
    private final ArchieContactListener acl;
    public boolean leftMove;
    public boolean rightMove;
    public boolean jump;
    public boolean runMove;

    //objects arrays
    Array<Enemy> enemies;
    Array<ItemEntity> coins = new Array<>();
    Array<ItemEntity> medicKits = new Array<>();
    Array<ItemEntity> traps = new Array<>();
    Array<EnemySword> enemySwords;
    //texture
    private Player player;
    private final TextureRegion playerSwordRegion;
    private final TextureRegion playerSwordRegionMirrored;

    private final TextureRegion playerShieldRegion;
    private final TextureRegion playerShieldRegionMirrored;

    private boolean isPlayerSword;
    private boolean isPlayerShield;

    private final Texture lifeBarIcon;
    private final Texture coinBarIcon;

    private final ArchieBackgroundAnimation[] background;

    //sounds and music
    private final Music sunnyDay;
    private final Sound cheatSound;
    private final Sound backSound;
    private final Sound attackSound;
    private final Sound jumpSound;
    private final Sound attackByPlayerSound;
    private final Sound attackByEnemySound;
    private final Sound enemyDeathSound;
    private final Sound coinSound;
    private final Sound medicKitSound;

    //cheat for fun
    public char charSequence;
    private String cheatCode;

    public Play(Archie game, int levelId) {
        this.game = game;
        level = Level.getById(levelId);
        debugModeOn = false;
        charSequence = ' ';
        cheatCode = "";
        invincibleCheat = false;

        selectedPlayerBody = PlayerBody.getById(game.pref.getEquippedBody());
        selectedPlayerSword = PlayerSword.getById(game.pref.getEquippedSword());
        selectedPlayerShield = PlayerShield.getById(game.pref.getEquippedShield());
        //initializing sounds
        sunnyDay = AudioManager.MUSIC_IN_GAME.loadMusic();
        cheatSound = AudioManager.SOUND_HARD_KICK.loadSound();
        backSound = AudioManager.SOUND_MENU_BACK.loadSound();
        attackSound = AudioManager.SOUND_SWORD.loadSound();
        jumpSound = AudioManager.PLAYER_SOUND_JUMP.loadSound();
        attackByPlayerSound = AudioManager.PLAYER_SOUND_PUNCH.loadSound();
        attackByEnemySound = AudioManager.ENEMY_SOUND_PUNCH.loadSound();
        enemyDeathSound = AudioManager.ENEMY_SOUND_DEATH.loadSound();
        coinSound = AudioManager.ITEM_SOUND_COIN.loadSound();
        medicKitSound = AudioManager.ITEM_SOUND_MEDIC.loadSound();
        sunnyDay.setLooping(true);
        //get keyboard and controller
        keyboard = new int[8];
        controller = new int[8];
        //create box2d world
        world = new World(new Vector2(0, -9), true);

        //create tiled map
        map = new TmxMapLoader().load(level.mapPath);
        orthogonalTiledMapRenderer = new OrthogonalTiledMapRenderer(map);

        layer = (TiledMapTileLayer) map.getLayers().get("ground");
        tileSize = layer.getTileWidth();
        //set cameras
        camera = new ArchieBoundedCamera();
        camera.setToOrtho(false, Archie.WIDTH, Archie.HEIGHT);
        camera.setBounds(0, layer.getWidth() * tileSize, 0, layer.getHeight() * tileSize);
        b2camera = new ArchieBoundedCamera();
        b2camera.setToOrtho(false, Archie.WIDTH / ArchieConstantVariables.PPM, Archie.HEIGHT / ArchieConstantVariables.PPM);
        b2camera.setBounds(0, (layer.getWidth() * tileSize) / ArchieConstantVariables.PPM, 0, (layer.getHeight() * tileSize) / ArchieConstantVariables.PPM);

        random = new Random();
        //b2dr only for debugging
        b2dr = new Box2DDebugRenderer();
        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();
        //Initializing input processors
        ArchieInputProcessor aip = new ArchieInputProcessor(this, getKeyboardMapping());
        ArchieControllerProcessor acp = new ArchieControllerProcessor(this, getControllerMapping());
        Gdx.input.setInputProcessor(aip);
        //create objects
        createPlayer();
        createTiles();
        createItems(ItemType.COIN);
        createItems(ItemType.MEDIC_KIT);
        createItems(ItemType.TRAP);
        createEnemy();
        //set contact listener
        acl = new ArchieContactListener(selectedPlayerShield, enemyIdentity);
        world.setContactListener(acl);
        //player health
        lifeBarIcon = new Texture(Gdx.files.internal(HUD_LIFE_BAR_PATH));
        coinBarIcon = new Texture(Gdx.files.internal(HUD_COIN_BAR_PATH));
        lifeBar = selectedPlayerBody.health;

        playerSwordRegion = new TextureRegion(new Texture(Gdx.files.internal(PLAYER_SWORD_TEXTURE_PATH)), getTexturesStartX(0), 0, 32, 7);
        playerSwordRegionMirrored = new TextureRegion(new Texture(Gdx.files.internal(PLAYER_SWORD_TEXTURE_PATH)), getTexturesStartX(0), 0, 32, 7);
        playerSwordRegionMirrored.flip(true, false);

        playerShieldRegion = new TextureRegion(new Texture(Gdx.files.internal(PLAYER_SHIELD_TEXTURE_PATH)), getTexturesStartX(1), 0, 32, 32);
        playerShieldRegionMirrored = new TextureRegion(new Texture(Gdx.files.internal(PLAYER_SHIELD_TEXTURE_PATH)), getTexturesStartX(1), 0, 32, 32);
        playerShieldRegionMirrored.flip(true, false);

        playerSwordDamage = selectedPlayerSword.damage;
        isPlayerSword = false;
        isPlayerShield = false;

        TextureRegion sky;
        if (levelId == 0 || levelId == 1 || levelId == 4) {
            sky = new TextureRegion(new Texture(Gdx.files.internal("levels/levelTextures/background.png")), 0, 1800, 1024, 600);
        } else {
            sky = new TextureRegion(new Texture(Gdx.files.internal("levels/levelTextures/background.png")), 0, 1200, 1024, 600);
        }
        TextureRegion clouds = new TextureRegion(new Texture(Gdx.files.internal("levels/levelTextures/background.png")), 0, 600, 1024, 600);
        TextureRegion forest = new TextureRegion(new Texture(Gdx.files.internal("levels/levelTextures/background.png")), 0, 0, 1024, 600);
        background = new ArchieBackgroundAnimation[3];
        background[0] = new ArchieBackgroundAnimation(sky, camera, 0f);
        background[1] = new ArchieBackgroundAnimation(clouds, camera, 0.5f);
        background[2] = new ArchieBackgroundAnimation(forest, camera, 0.2f);

        enemySwords = new Array<>();
    }

    @Override
    public void dispose() {
        sunnyDay.dispose();
        cheatSound.dispose();
        backSound.dispose();
        attackSound.dispose();
        jumpSound.dispose();
        attackByPlayerSound.dispose();
        attackByEnemySound.dispose();
        enemyDeathSound.dispose();
        coinSound.dispose();
        medicKitSound.dispose();

        lifeBarIcon.dispose();
        coinBarIcon.dispose();
        coins.clear();
        enemies.clear();
        traps.clear();
        enemySwords.clear();
        //world.dispose();
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        state = State.Paused;
    }

    public void render(float dt) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //camera.setPosition(player.getBody().getPosition().x * ArchieConstantVariables.PPM + Archie.WIDTH / 32, player.getBody().getPosition().y * ArchieConstantVariables.PPM + Archie.HEIGHT / 64);
        camera.setPosition(player.getBody().getPosition().x * ArchieConstantVariables.PPM + Archie.WIDTH / 32f, Archie.HEIGHT / 64f);
        camera.update();
        if (!debugModeOn) {
            for (ArchieBackgroundAnimation archieBackgroundAnimation : background) {
                archieBackgroundAnimation.render(game.batch);
            }
        }

        orthogonalTiledMapRenderer.setView(camera);
        if (!debugModeOn) {
            orthogonalTiledMapRenderer.render();
        }

        b2camera.setPosition(player.getBody().getPosition().x + Archie.WIDTH / 32f / ArchieConstantVariables.PPM, player.getBody().getPosition().y + Archie.HEIGHT / 64f / ArchieConstantVariables.PPM);
        b2camera.update();

        if (debugModeOn) {
            b2dr.render(world, b2camera.combined);
        }

        if (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) && Gdx.input.isKeyPressed(Keys.D)) {
            debugModeOn = true;
        }
        if (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) && Gdx.input.isKeyPressed(Keys.N)) {
            debugModeOn = false;
        }
        if (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) && Gdx.input.isKeyPressed(Keys.C)) {
            cheatModeOn = true;
            charSequence = ' ';
            cheatCode = "";
        }

        switch (state) {
            case Running:
                update(dt);
                break;
            case Paused:
                break;
            case Quit:
                dispose();
                game.setScreen(new MainMenuScreen(game));
                //game.dispose();
                break;
        }
        if (!debugModeOn) {
            for (int i = 0; i < coins.size; i++) {
                coins.get(i).render(orthogonalTiledMapRenderer);
            }
            for (int i = 0; i < traps.size; i++) {
                traps.get(i).render(orthogonalTiledMapRenderer);
            }
            for (int i = 0; i < medicKits.size; i++) {
                medicKits.get(i).render(orthogonalTiledMapRenderer);
            }
            for (Enemy enemy : enemies) {
                enemy.render(orthogonalTiledMapRenderer);
            }
            for (int i = 0; i < enemySwords.size; i++) {
                enemySwords.get(i).render(orthogonalTiledMapRenderer);
            }
            player.render(orthogonalTiledMapRenderer);
            if (isPlayerSword) {
                orthogonalTiledMapRenderer.getBatch().begin();
                if (player.getArrow()) {
                    orthogonalTiledMapRenderer.getBatch().draw(playerSwordRegion, (sword.getPosition().x * ArchieConstantVariables.PPM) + 10f, (sword.getPosition().y * ArchieConstantVariables.PPM) - 7f, 32f, 7f);
                } else {
                    orthogonalTiledMapRenderer.getBatch().draw(playerSwordRegionMirrored, (sword.getPosition().x * ArchieConstantVariables.PPM) - 42f, (sword.getPosition().y * ArchieConstantVariables.PPM) - 7f, 32f, 7f);
                }
                orthogonalTiledMapRenderer.getBatch().end();
            }
            if (isPlayerShield) {
                orthogonalTiledMapRenderer.getBatch().begin();
                if (player.getArrow()) {
                    orthogonalTiledMapRenderer.getBatch().draw(playerShieldRegion, (shield.getPosition().x * ArchieConstantVariables.PPM), (shield.getPosition().y * ArchieConstantVariables.PPM) - 16f, 32f, 32f);
                } else {
                    orthogonalTiledMapRenderer.getBatch().draw(playerShieldRegionMirrored, (shield.getPosition().x * ArchieConstantVariables.PPM) - 32f, (shield.getPosition().y * ArchieConstantVariables.PPM) - 16f, 32f, 32f);
                }
                orthogonalTiledMapRenderer.getBatch().end();
            }
        }

        game.batch.begin();
        hud();
        if (state == State.Paused) {
            game.font72.setColor(Color.WHITE);
            game.font72.draw(game.batch, "PAUSED", Archie.WIDTH - 300, Archie.HEIGHT - 25);
        }
        game.batch.end();

        orthogonalTiledMapRenderer.getBatch().begin();
        for (Enemy enemy : enemies) {
            game.font36.setColor(Color.WHITE);
            game.font36.draw(orthogonalTiledMapRenderer.getBatch(), "" + enemy.getHealth(), (enemy.getBody().getPosition().x * ArchieConstantVariables.PPM) + enemy.getBodyDimension() / 2f, (enemy.getBody().getPosition().y * ArchieConstantVariables.PPM) + enemy.getBodyDimension());
        }
        orthogonalTiledMapRenderer.getBatch().end();

        if (!invincibleCheat) {
            int healthBuffer = acl.getHealth();
            if (healthBuffer < 0) {
                attackByEnemySound.play();
            }
            lifeBar += healthBuffer;
        }
        collectedCoins = acl.getCoins();

        playerStateChange();
        enemyStateChange();
        itemStateChange();
        cheatHappen();
    }

    public void update(float dt) {
        updateMotion();
        world.step(dt, 6, 2);
        camera.update();
    }

    @Override
    public void resize(int arg0, int arg1) {
        // TODO Auto-generated method stub
    }

    @Override
    public void resume() {
        state = State.Running;
    }

    @Override
    public void show() {
        sunnyDay.play();
    }

    public void updateMotion() {
        //player movement
        if (acl.playerAllowToMove()) {
            if (leftMove) {
                player.setArrow(false);
                player.setMove(true);
                player.getBody().setLinearVelocity(-2f, 0f);
            }
            if (rightMove) {
                player.setArrow(true);
                player.setMove(true);
                player.getBody().setLinearVelocity(2f, 0f);
            }
            if (jump) {
                jumpSound.play();
                player.getBody().setLinearVelocity(0f, 5f);
            }
            if (leftMove && jump) {
                player.setArrow(false);
                player.setMove(true);
                jumpSound.play();
                player.getBody().setLinearVelocity(-2f, 5f);
            }
            if (rightMove && jump) {
                player.setArrow(true);
                player.setMove(true);
                jumpSound.play();
                player.getBody().setLinearVelocity(2f, 5f);
            }
            if (runMove && leftMove) {
                player.setArrow(false);
                player.setMove(true);
                player.getBody().setLinearVelocity(-2f * selectedPlayerBody.stamina, 0f);
            }
            if (runMove && rightMove) {
                player.setArrow(true);
                player.setMove(true);
                player.getBody().setLinearVelocity(2f * selectedPlayerBody.stamina, 0f);
            }
            if (!leftMove && !rightMove) {
                player.setMove(false);
            }
        }
        extendedAi();
    }

    private void createPlayer() {
        MapLayer mapLayer = map.getLayers().get("player");
        if (mapLayer == null) return;

        for (MapObject mapObjects : mapLayer.getObjects()) {

            float x = (float) mapObjects.getProperties().get("x") / ArchieConstantVariables.PPM;
            float y = (float) mapObjects.getProperties().get("y") / ArchieConstantVariables.PPM;
            bodyDef.type = BodyType.DynamicBody;
            bodyDef.position.set(x, y);
            bodyDef.fixedRotation = true;
            Body playerBody = world.createBody(bodyDef);

            PolygonShape shape = new PolygonShape();
            shape.setAsBox(32f / 2 / ArchieConstantVariables.PPM, 64f / 2 / ArchieConstantVariables.PPM);
            fixtureDef.shape = shape;
            fixtureDef.friction = 0.5f;
            fixtureDef.restitution = 0;
            fixtureDef.filter.categoryBits = ArchieConstantVariables.BIT_PLAYER;
            fixtureDef.filter.maskBits = ArchieConstantVariables.BIT_GROUND | ArchieConstantVariables.BIT_COIN | ArchieConstantVariables.BIT_TRAP | ArchieConstantVariables.BIT_MEDIC_KIT | ArchieConstantVariables.BIT_ENEMY | ArchieConstantVariables.BIT_ENEMY_SWORD;
            playerBody.createFixture(fixtureDef).setUserData("player");
            //create foot
            shape.setAsBox(16f / ArchieConstantVariables.PPM, 0.1f / ArchieConstantVariables.PPM, new Vector2(0, -32 / ArchieConstantVariables.PPM), 0);
            fixtureDef.shape = shape;
            fixtureDef.filter.categoryBits = ArchieConstantVariables.BIT_PLAYER;
            fixtureDef.filter.maskBits = ArchieConstantVariables.BIT_GROUND | ArchieConstantVariables.BIT_ENEMY;
            playerBody.createFixture(fixtureDef).setUserData("foot");

            MassData md = new MassData();
            md.mass = selectedPlayerBody.mass + selectedPlayerSword.mass + selectedPlayerShield.mass;
            playerBody.setMassData(md);

            player = new Player(playerBody, game.pref.getEquippedBody());
            shape.dispose();
        }
    }

    private void createEnemy() {
        enemies = new Array<>();
        EnemyBody enemyType;
        enemyIdentity = 1;
        String enemyUserData;

        MapLayer mlEnemies = map.getLayers().get("enemy");
        if (mlEnemies == null) return;
        for (MapObject moEnemies : mlEnemies.getObjects()) {
            BodyDef cdef = new BodyDef();
            cdef.type = BodyType.DynamicBody;
            float x = (float) moEnemies.getProperties().get("x") / ArchieConstantVariables.PPM;
            float y = (float) moEnemies.getProperties().get("y") * 2 / ArchieConstantVariables.PPM;
            cdef.position.set(x, y);
            Body body = world.createBody(cdef);
            FixtureDef cfdef = new FixtureDef();
            PolygonShape shape = new PolygonShape();
            cfdef.friction = 0.5f;
            cfdef.restitution = 0.025f;
            cfdef.filter.categoryBits = ArchieConstantVariables.BIT_ENEMY;
            cfdef.filter.maskBits = ArchieConstantVariables.BIT_PLAYER | ArchieConstantVariables.BIT_SWORD | ArchieConstantVariables.BIT_SHIELD | ArchieConstantVariables.BIT_GROUND;
            MassData md = new MassData();
            switch (Integer.parseInt(moEnemies.getProperties().get("enemyType", String.class))) {
                case 0:
                    enemyType = EnemyBody.GHOST;
                    shape.setAsBox(16 / ArchieConstantVariables.PPM, 16 / ArchieConstantVariables.PPM);
                    cfdef.shape = shape;
                    enemyUserData = "enemy0" + "i" + enemyIdentity;
                    body.createFixture(cfdef).setUserData(enemyUserData);
                    //create foot
                    shape.setAsBox(14f / ArchieConstantVariables.PPM, 0.1f / ArchieConstantVariables.PPM, new Vector2(0, -17 / ArchieConstantVariables.PPM), 0);
                    cfdef.shape = shape;
                    cfdef.restitution = 0.01f;
                    cfdef.filter.categoryBits = ArchieConstantVariables.BIT_ENEMY;
                    cfdef.filter.maskBits = ArchieConstantVariables.BIT_GROUND;
                    body.createFixture(cfdef).setUserData("footenemy" + enemyIdentity);

                    md.mass = 1f;
                    break;
                case 1:
                    enemyType = EnemyBody.IRON;
                    shape.setAsBox(32 / ArchieConstantVariables.PPM, 32 / ArchieConstantVariables.PPM);
                    cfdef.shape = shape;
                    enemyUserData = "enemy1" + "i" + enemyIdentity;
                    body.createFixture(cfdef).setUserData(enemyUserData);
                    //create foot
                    shape.setAsBox(30f / ArchieConstantVariables.PPM, 0.1f / ArchieConstantVariables.PPM, new Vector2(0, -33 / ArchieConstantVariables.PPM), 0);
                    cfdef.shape = shape;
                    cfdef.restitution = 0.01f;
                    cfdef.filter.categoryBits = ArchieConstantVariables.BIT_ENEMY;
                    cfdef.filter.maskBits = ArchieConstantVariables.BIT_GROUND;
                    body.createFixture(cfdef).setUserData("footenemy" + enemyIdentity);
                    //set sensor
                    shape.setAsBox(128f / ArchieConstantVariables.PPM, 28 / ArchieConstantVariables.PPM, new Vector2(0, 0), 0);
                    cfdef.shape = shape;
                    cfdef.isSensor = true;
                    cfdef.filter.categoryBits = ArchieConstantVariables.BIT_ENEMY;
                    cfdef.filter.maskBits = ArchieConstantVariables.BIT_PLAYER;
                    body.createFixture(cfdef).setUserData("sensor" + enemyIdentity);

                    md.mass = 2f;
                    break;
                case 2:
                    enemyType = EnemyBody.GOLD;
                    shape.setAsBox(32 / ArchieConstantVariables.PPM, 32 / ArchieConstantVariables.PPM);
                    cfdef.shape = shape;
                    enemyUserData = "enemy2" + "i" + enemyIdentity;
                    body.createFixture(cfdef).setUserData(enemyUserData);
                    //create foot
                    shape.setAsBox(30f / ArchieConstantVariables.PPM, 0.1f / ArchieConstantVariables.PPM, new Vector2(0, -33 / ArchieConstantVariables.PPM), 0);
                    cfdef.shape = shape;
                    cfdef.restitution = 0.01f;
                    cfdef.filter.categoryBits = ArchieConstantVariables.BIT_ENEMY;
                    cfdef.filter.maskBits = ArchieConstantVariables.BIT_GROUND;
                    body.createFixture(cfdef).setUserData("footenemy" + enemyIdentity);
                    //set sensor
                    shape.setAsBox(128f / ArchieConstantVariables.PPM, 28 / ArchieConstantVariables.PPM, new Vector2(0, 0), 0);
                    cfdef.shape = shape;
                    cfdef.isSensor = true;
                    cfdef.filter.categoryBits = ArchieConstantVariables.BIT_ENEMY;
                    cfdef.filter.maskBits = ArchieConstantVariables.BIT_PLAYER;
                    body.createFixture(cfdef).setUserData("sensor" + enemyIdentity);

                    md.mass = 3f;
                    break;
                case 3:
                    enemyType = EnemyBody.DIAMOND;
                    shape.setAsBox(32 / ArchieConstantVariables.PPM, 32 / ArchieConstantVariables.PPM);
                    cfdef.shape = shape;
                    enemyUserData = "enemy3" + "i" + enemyIdentity;
                    body.createFixture(cfdef).setUserData(enemyUserData);
                    //create foot
                    shape.setAsBox(30f / ArchieConstantVariables.PPM, 0.1f / ArchieConstantVariables.PPM, new Vector2(0, -33 / ArchieConstantVariables.PPM), 0);
                    cfdef.shape = shape;
                    cfdef.restitution = 0.01f;
                    cfdef.filter.categoryBits = ArchieConstantVariables.BIT_ENEMY;
                    cfdef.filter.maskBits = ArchieConstantVariables.BIT_GROUND;
                    body.createFixture(cfdef).setUserData("footenemy" + enemyIdentity);
                    //set sensor
                    shape.setAsBox(128f / ArchieConstantVariables.PPM, 28 / ArchieConstantVariables.PPM, new Vector2(0, 0), 0);
                    cfdef.shape = shape;
                    cfdef.isSensor = true;
                    cfdef.filter.categoryBits = ArchieConstantVariables.BIT_ENEMY;
                    cfdef.filter.maskBits = ArchieConstantVariables.BIT_PLAYER;
                    body.createFixture(cfdef).setUserData("sensor" + enemyIdentity);

                    md.mass = 4f;
                    break;
                case 4:
                    enemyType = EnemyBody.BIG_ONE;
                    shape.setAsBox(64 / ArchieConstantVariables.PPM, 64 / ArchieConstantVariables.PPM);
                    cfdef.shape = shape;
                    enemyUserData = "enemy4" + "i" + enemyIdentity;
                    body.createFixture(cfdef).setUserData(enemyUserData);
                    //create foot
                    shape.setAsBox(62f / ArchieConstantVariables.PPM, 0.1f / ArchieConstantVariables.PPM, new Vector2(0, -65 / ArchieConstantVariables.PPM), 0);
                    cfdef.shape = shape;
                    cfdef.restitution = 0.01f;
                    cfdef.filter.categoryBits = ArchieConstantVariables.BIT_ENEMY;
                    cfdef.filter.maskBits = ArchieConstantVariables.BIT_GROUND;
                    body.createFixture(cfdef).setUserData("footenemy" + enemyIdentity);
                    //set sensor
                    shape.setAsBox(200f / ArchieConstantVariables.PPM, 60 / ArchieConstantVariables.PPM, new Vector2(0, 0), 0);
                    cfdef.shape = shape;
                    cfdef.isSensor = true;
                    cfdef.filter.categoryBits = ArchieConstantVariables.BIT_ENEMY;
                    cfdef.filter.maskBits = ArchieConstantVariables.BIT_PLAYER;
                    body.createFixture(cfdef).setUserData("sensor" + enemyIdentity);

                    md.mass = 5f;
                    break;
                default:
                    enemyType = EnemyBody.GHOST;
            }
            body.setMassData(md);
            Enemy enemy = new Enemy(body, enemyType, enemyIdentity, x, y);
            enemies.add(enemy);
            shape.dispose();
            enemyIdentity++;
        }
    }

    private void createItems(ItemType itemType) {
        int identity = 1;

        MapLayer mapLayer = map.getLayers().get(itemType.layerName);
        if (mapLayer == null) return;
        for (MapObject mapObjects : mapLayer.getObjects()) {

            BodyDef cdef = new BodyDef();
            cdef.type = BodyType.StaticBody;
            float x = (float) mapObjects.getProperties().get("x") / ArchieConstantVariables.PPM;
            float y = (float) mapObjects.getProperties().get("y") / ArchieConstantVariables.PPM;
            cdef.position.set(x, y);
            Body body = world.createBody(cdef);
            FixtureDef cfdef = new FixtureDef();
            switch (itemType) {
                case COIN:
                    CircleShape cshape = new CircleShape();
                    cshape.setRadius(16 / ArchieConstantVariables.PPM);
                    cfdef.shape = cshape;
                    cfdef.isSensor = true;
                    cfdef.filter.categoryBits = ArchieConstantVariables.BIT_COIN;
                    cfdef.filter.maskBits = ArchieConstantVariables.BIT_PLAYER;
                    body.createFixture(cfdef).setUserData(itemType.layerName + identity);
                    ItemEntity coin = new ItemEntity(body, identity, itemType);
                    coins.add(coin);
                    cshape.dispose();
                    identity++;
                    break;
                case MEDIC_KIT:
                    PolygonShape rshape2 = new PolygonShape();
                    rshape2.setAsBox(16 / ArchieConstantVariables.PPM, 16 / ArchieConstantVariables.PPM);
                    cfdef.shape = rshape2;
                    cfdef.isSensor = true;
                    cfdef.filter.categoryBits = ArchieConstantVariables.BIT_MEDIC_KIT;
                    cfdef.filter.maskBits = ArchieConstantVariables.BIT_PLAYER;
                    body.createFixture(cfdef).setUserData(itemType.layerName + identity);
                    ItemEntity medicKit = new ItemEntity(body, identity, itemType);
                    medicKits.add(medicKit);
                    rshape2.dispose();
                    identity++;
                    break;
                case TRAP:
                    PolygonShape rshape = new PolygonShape();
                    rshape.setAsBox(16 / ArchieConstantVariables.PPM, 16 / ArchieConstantVariables.PPM);
                    cfdef.shape = rshape;
                    cfdef.restitution = 1f;
                    cfdef.filter.categoryBits = ArchieConstantVariables.BIT_TRAP;
                    cfdef.filter.maskBits = ArchieConstantVariables.BIT_PLAYER;
                    body.createFixture(cfdef).setUserData(itemType.layerName);
                    ItemEntity trap = new ItemEntity(body, itemType);
                    traps.add(trap);
                    rshape.dispose();
                    break;
            }

        }
    }

    private void createTiles() {
        for (int row = 0; row < layer.getHeight(); row++) {
            for (int col = 0; col < layer.getWidth(); col++) {
                Cell cell = layer.getCell(col, row);
                if (cell == null) continue;
                if (cell.getTile() == null) continue;

                bodyDef.type = BodyType.StaticBody;
                bodyDef.position.set((col + 0.5f) * tileSize / ArchieConstantVariables.PPM, (row + 0.5f) * tileSize / ArchieConstantVariables.PPM);

                ChainShape cs = new ChainShape();
                Vector2[] v = new Vector2[5];
                v[0] = new Vector2(-tileSize / 2 / ArchieConstantVariables.PPM, tileSize / 2 / ArchieConstantVariables.PPM);
                v[1] = new Vector2(tileSize / 2 / ArchieConstantVariables.PPM, tileSize / 2 / ArchieConstantVariables.PPM);
                v[2] = new Vector2(tileSize / 2 / ArchieConstantVariables.PPM, -tileSize / 2 / ArchieConstantVariables.PPM);
                v[3] = new Vector2(-tileSize / 2 / ArchieConstantVariables.PPM, -tileSize / 2 / ArchieConstantVariables.PPM);
                v[4] = new Vector2(-tileSize / 2 / ArchieConstantVariables.PPM, tileSize / 2 / ArchieConstantVariables.PPM);
                cs.createChain(v);
                fixtureDef.shape = cs;
                fixtureDef.friction = 0.5f;
                fixtureDef.restitution = 0f;
                fixtureDef.filter.categoryBits = ArchieConstantVariables.BIT_GROUND;
                fixtureDef.filter.maskBits = ArchieConstantVariables.BIT_PLAYER | ArchieConstantVariables.BIT_ENEMY;
                world.createBody(bodyDef).createFixture(fixtureDef).setUserData("ground");
            }
        }
    }

    private void playerStateChange() {
        if (lifeBar < 0 || lifeBar == 0) {
            test("died");
            player.setAlive(false);
        }
        if (player.getBody().getPosition().y < 0) {
            test("falled");
            player.setAlive(false);
        }
        if (player.getBody().getPosition().x > layer.getWidth() * 32f / ArchieConstantVariables.PPM - 32 / ArchieConstantVariables.PPM) {
            test("end");
            dispose();
            if (level == Level.THE_END) {
                game.pref.setCoin(collectedCoins);
                game.setScreen(new TheEndScreen(game, true));
            } else {
                game.pref.setCoin(collectedCoins);
                game.pref.setLevelEnabled(nextMap(level.id));
                game.setScreen(new StartScreen(game, nextMap(level.id)));
            }
        }

        if (!player.isAlive()) {
            dispose();
            game.setScreen(new Play(game, level.id));
        }

    }

    private void enemyStateChange() {
        int buffer = acl.getDamagedByPlayer();
        if (buffer > 0) {
            attackByPlayerSound.play();
            for (Enemy enemy : enemies) {
                if (enemy.getIdentity() == buffer) {
                    enemy.setHealth(-playerSwordDamage);
                }
            }
        }

        if (enemies.size != 0) {
            for (int i = 0; i < enemies.size; i++) {
                if (enemies.get(i).getHealth() <= 0) {
                    enemies.get(i).setAlive(false);
                }
                if (enemies.get(i).getBody().getPosition().y < 0) {
                    test("enemy falled");
                    enemies.get(i).setAlive(false);
                }
                if (!enemies.get(i).isAlive()) {
                    enemyDeathSound.play();
                    world.destroyBody(enemies.get(i).getBody());
                    enemies.removeIndex(i);
                }
            }
        }

    }

    private void itemStateChange() {
        int coinBuffer = acl.getCoinIdentity();
        if (coinBuffer > 0) {
            for (ItemEntity coin : coins) {
                if (coin.getIdentity() == coinBuffer) {
                    coin.setAlive(false);
                }
            }
        }

        if (coins.size != 0) {
            for (int i = 0; i < coins.size; i++) {
                if (coins.get(i).isDead()) {
                    coinSound.play();
                    world.destroyBody(coins.get(i).getBody());
                    coins.removeIndex(i);
                }
            }
        }

        int medicKitBuffer = acl.getMedicKitIdentity();
        if (medicKitBuffer > 0) {
            for (ItemEntity medicKit : medicKits) {
                if (medicKit.getIdentity() == medicKitBuffer) {
                    medicKit.setAlive(false);
                }
            }
        }

        if (medicKits.size != 0) {
            for (int i = 0; i < medicKits.size; i++) {
                if (medicKits.get(i).isDead()) {
                    medicKitSound.play();
                    world.destroyBody(medicKits.get(i).getBody());
                    medicKits.removeIndex(i);
                }
            }
        }
    }

    private int nextMap(int mapNumber) {
        if (Level.getById(mapNumber) == null) {
            mapNumber = 0;
        } else {
            mapNumber++;
        }
        return mapNumber;
    }

    public void makeSword(boolean alive) {
        bodyDef.type = BodyType.DynamicBody;
        if (alive && !isPlayerShield) {
            bodyDef.position.set(player.getBody().getPosition());
            sword = world.createBody(bodyDef);
            PolygonShape shape = new PolygonShape();
            if (player.getArrow()) {
                shape.setAsBox(16f / ArchieConstantVariables.PPM, 3.5f / ArchieConstantVariables.PPM, new Vector2(28 / ArchieConstantVariables.PPM, -4 / ArchieConstantVariables.PPM), 0);
            } else {
                shape.setAsBox(16f / ArchieConstantVariables.PPM, 3.5f / ArchieConstantVariables.PPM, new Vector2(-28 / ArchieConstantVariables.PPM, -4 / ArchieConstantVariables.PPM), 0);
            }
            fixtureDef.shape = shape;
            fixtureDef.isSensor = false;
            fixtureDef.filter.categoryBits = ArchieConstantVariables.BIT_SWORD;
            fixtureDef.filter.maskBits = ArchieConstantVariables.BIT_ENEMY;
            sword.createFixture(fixtureDef).setUserData("sword");
            shape.dispose();
            WeldJointDef djd = new WeldJointDef();
            djd.bodyA = player.getBody();
            djd.bodyB = sword;
            world.createJoint(djd);
            isPlayerSword = true;
            attackSound.play();
        } else {
            isPlayerSword = false;
            if (sword != null) {
                world.destroyBody(sword);
            }
        }
    }

    public void makeEnemySword(boolean alive, Body enemyBody, int enemyId, int type, boolean arrow) {
        if (alive) {
            Body body;
            bodyDef.type = BodyType.DynamicBody;
            bodyDef.position.set(enemyBody.getPosition());
            body = world.createBody(bodyDef);
            PolygonShape shape = new PolygonShape();
            if (type == 4) {
                if (arrow) {
                    shape.setAsBox(10 / ArchieConstantVariables.PPM, 64 / ArchieConstantVariables.PPM, new Vector2(64 / ArchieConstantVariables.PPM, 0), 0);
                } else {
                    shape.setAsBox(10 / ArchieConstantVariables.PPM, 64 / ArchieConstantVariables.PPM, new Vector2(-64 / ArchieConstantVariables.PPM, 0), 0);
                }
            } else {
                if (arrow) {
                    shape.setAsBox(10 / ArchieConstantVariables.PPM, 5 / ArchieConstantVariables.PPM, new Vector2(32 / ArchieConstantVariables.PPM, 0), 0);
                } else {
                    shape.setAsBox(10 / ArchieConstantVariables.PPM, 5 / ArchieConstantVariables.PPM, new Vector2(-32 / ArchieConstantVariables.PPM, 0), 0);
                }
            }
            fixtureDef.shape = shape;
            fixtureDef.isSensor = true;
            fixtureDef.filter.categoryBits = ArchieConstantVariables.BIT_ENEMY_SWORD;
            fixtureDef.filter.maskBits = ArchieConstantVariables.BIT_PLAYER | ArchieConstantVariables.BIT_SHIELD;
            body.createFixture(fixtureDef).setUserData("swordByEnemy" + type);
            shape.dispose();

            WeldJointDef djd = new WeldJointDef();
            djd.bodyA = enemyBody;
            djd.bodyB = body;
            world.createJoint(djd);
            EnemySword enemySword = new EnemySword(body, enemyId, EnemySwordBody.getById(type), arrow);
            enemySwords.add(enemySword);
            lastEnemySwordTime = TimeUtils.nanoTime();
            attackSound.play();
        } else {
            for (int i = 0; i < enemySwords.size; i++) {
                if (enemySwords.get(i).getIdentity() == enemyId) {
                    world.destroyBody(enemySwords.get(i).getBody());
                    enemySwords.removeIndex(i);
                }
            }
        }
    }

    public void makeShield(boolean alive) {
        bodyDef.type = BodyType.DynamicBody;
        if (alive && !isPlayerSword) {
            bodyDef.position.set(player.getBody().getPosition());
            shield = world.createBody(bodyDef);
            PolygonShape shape = new PolygonShape();
            if (player.getArrow()) {
                shape.setAsBox(16 / ArchieConstantVariables.PPM, 16 / ArchieConstantVariables.PPM, new Vector2(16 / ArchieConstantVariables.PPM, 0), 0);
            } else {
                shape.setAsBox(16 / ArchieConstantVariables.PPM, 16 / ArchieConstantVariables.PPM, new Vector2(-16 / ArchieConstantVariables.PPM, 0), 0);
            }
            fixtureDef.shape = shape;
            fixtureDef.isSensor = false;
            fixtureDef.filter.categoryBits = ArchieConstantVariables.BIT_SHIELD;
            fixtureDef.filter.maskBits = ArchieConstantVariables.BIT_ENEMY_SWORD | ArchieConstantVariables.BIT_ENEMY;
            shield.createFixture(fixtureDef).setUserData("shield");
            shape.dispose();
            WeldJointDef djd = new WeldJointDef();
            djd.bodyA = player.getBody();
            djd.bodyB = shield;
            world.createJoint(djd);
            isPlayerShield = true;
            attackSound.play();
        } else {
            isPlayerShield = false;
            if (shield != null) {
                world.destroyBody(shield);
            }
        }
    }

    private void hud() {
        if (debugModeOn) {
            game.font36.setColor(Color.WHITE);
            game.font36.draw(game.batch, "fps: " + Gdx.graphics.getFramesPerSecond() + "\nplayer position: x: " + player.getBody().getPosition().x + " y: " + player.getBody().getPosition().y + "\nmap dimensions: width: " + layer.getWidth() + " height: " + layer.getHeight() + "\ntile size: " + tileSize + "\ncoins: " + acl.getCoins() + "\nhealth: " + lifeBar, 10, Archie.HEIGHT - 10);
        }
        if (!debugModeOn) {
            if (lifeBar >= 80) {
                game.font36.setColor(Color.GREEN);
            }
            if (lifeBar < 80 && lifeBar >= 40) {
                game.font36.setColor(Color.YELLOW);
            }
            if (lifeBar < 40) {
                game.font36.setColor(Color.RED);
            }
            game.batch.draw(lifeBarIcon, 25, Archie.HEIGHT - 50);
            game.font36.draw(game.batch, "" + lifeBar, 75, Archie.HEIGHT - 10);
            game.batch.draw(coinBarIcon, 125, Archie.HEIGHT - 50);
            game.font36.setColor(Color.YELLOW);
            game.font36.draw(game.batch, "" + acl.getCoins(), 175, Archie.HEIGHT - 10);
        }
    }

    private int[] getKeyboardMapping() {
        keyboard[0] = game.controls.getLeftButton();
        keyboard[1] = game.controls.getRightButton();
        keyboard[2] = game.controls.getJumpButton();
        keyboard[3] = game.controls.getRunButton();
        keyboard[4] = game.controls.getSwordButton();
        keyboard[5] = game.controls.getShieldButton();
        keyboard[6] = game.controls.getPauseButton();
        keyboard[7] = game.controls.getExitButton();
        return keyboard;
    }

    private int[] getControllerMapping() {
        controller[0] = game.controls.getCLeftButton();
        controller[1] = game.controls.getCRightButton();
        controller[2] = game.controls.getCJumpButton();
        controller[3] = game.controls.getCRunButton();
        controller[4] = game.controls.getCSwordButton();
        controller[5] = game.controls.getCShieldButton();
        controller[6] = game.controls.getCPauseButton();
        controller[7] = game.controls.getCExitButton();
        return controller;
    }

    //AI for enemy
    private void extendedAi() {
        int sensorBufferIdentity = acl.getSensorIdentity();
        boolean sensorBuffer = acl.getSensorData();
        int stopBitIdentity = acl.getStopBitIdentity();
        boolean stopBit = acl.getStopBit();
        int blockedEnemy = acl.getEnemyBlockedIdentity();
        boolean blocked = acl.enemyBlocked();

        for (Enemy enemy : enemies) {
            if (enemy.getIdentity() == sensorBufferIdentity) {
                if (sensorBuffer) {
                    enemy.setSensorOff(true);
                }
                if (!sensorBuffer) {
                    enemy.setSensorOff(false);
                }
            }

            if (enemy.getIdentity() == stopBitIdentity) {
                if (stopBit) {
                    enemy.setStopBit(true);
                }
                if (!stopBit) {
                    enemy.setStopBit(false);
                }
            }

            if (enemy.getIdentity() == blockedEnemy) {
                if (blocked) {
                    enemy.setBlocked(true);
                }
                if (!blocked) {
                    enemy.setBlocked(false);
                }
            }
        }

        for (Enemy enemy : enemies) {
            if (acl.enemyAllowToMove(enemy.getIdentity())) {
                if (!enemy.isSensorOn()) {
                    if (enemy.getBody().getPosition().x > (enemy.getStarterX() + 1.5)) {
                        enemy.setArrow(false);
                    }
                    if (enemy.getBody().getPosition().x < (enemy.getStarterX() - 1.5)) {
                        enemy.setArrow(true);
                    }
                    if (enemy.isBlocked()) {
                        if (enemy.getArrow()) {
                            enemy.setArrow(false);
                            enemy.setBlocked(false);
                        }
                        if (!enemy.getArrow()) {
                            enemy.setArrow(true);
                            enemy.setBlocked(false);
                        }
                    }
                    if (enemy.getArrow()) {
                        enemy.getBody().setLinearVelocity(enemy.getSpeedX(), 0f);
                    } else {
                        enemy.getBody().setLinearVelocity(-enemy.getSpeedX(), 0f);
                    }
                    if (enemy.isSword()) {
                        makeEnemySword(false, enemy.getBody(), enemy.getIdentity(), enemy.getSkin().id, enemy.getArrow());
                        enemy.setSword(false);
                    }
                } else {
                    if (!enemy.getStopBit()) {
                        if (enemy.getBody().getPosition().x < player.getBody().getPosition().x) {
                            enemy.setArrow(true);
                            enemy.getBody().setLinearVelocity(enemy.getSpeedX() * 2, 0f);
                        }
                        if (enemy.getBody().getPosition().x > player.getBody().getPosition().x) {
                            enemy.setArrow(false);
                            enemy.getBody().setLinearVelocity(-enemy.getSpeedX() * 2, 0f);
                        }
                    }
                    if (!enemy.isSword()) {
                        if (TimeUtils.nanoTime() - lastEnemySwordTime > enemySwordTime) {
                            makeEnemySword(true, enemy.getBody(), enemy.getIdentity(), enemy.getSkin().id, enemy.getArrow());
                            enemy.setSword(true);
                            switch (random.nextInt(2)) {
                                case 0:
                                    enemySwordTime = 1000000000;
                                    break;
                                case 1:
                                    enemySwordTime = 2000000000;
                                    break;
                            }
                        }
                    }
                    if (enemy.isSword()) {
                        if (TimeUtils.nanoTime() - lastEnemySwordTime > 500000000) {
                            makeEnemySword(false, enemy.getBody(), enemy.getIdentity(), enemy.getSkin().id, enemy.getArrow());
                            enemy.setSword(false);
                        }
                    }
                }
            }


        }

    }

    private int getTexturesStartX(int type) {
        int startX = 0;
        int value;
        if (type == 0) {
            value = selectedPlayerSword.id;
        } else {
            value = selectedPlayerShield.id;
        }
        switch (value) {
            case 0:
                startX = 0;
                break;
            case 1:
                startX = 32;
                break;
            case 2:
                startX = 64;
                break;
            case 3:
                startX = 96;
                break;
            case 4:
                startX = 128;
                break;
        }
        return startX;
    }

    public void test(String sentence) {
        System.out.println(sentence);
    }

    //there happen the cheats
    private void cheatHappen() {
        if (charSequence != ' ') {
            cheatCode = cheatCode + charSequence;
        }
        charSequence = ' ';
        if (cheatCode.equals("bigmillion")) {
            test("lol... if i have big million coins...");
            cheatSound.play();
            game.pref.setCoin(1000000);
            cheatCode = "";
            cheatModeOn = false;
        }
        if (cheatCode.equals("getallstuff")) {
            test("Get all stuff for free!");
            cheatSound.play();
            for (int i = 1; i < 5; i++) {
                game.pref.setEnabledSkin(i);
                game.pref.setEnabledSword(i);
                game.pref.setEnabledShield(i);
            }
            cheatCode = "";
            cheatModeOn = false;
        }
        if (cheatCode.equals("youshallnotpass")) {
            test("You shall not pass!");
            cheatSound.play();
            for (int i = 1; i < 5; i++) {
                game.pref.setLevelEnabled(i);
            }
            cheatCode = "";
            cheatModeOn = false;
        }
        if (cheatCode.equals("ignoreme")) {
            test("Ignore me, i am invincible!");
            cheatSound.play();
            cheatCode = "";
            invincibleCheat = true;
        }
    }
}
