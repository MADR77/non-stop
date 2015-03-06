import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import com.gamc.webs.entity.mob.Player;
import com.gamc.webs.graphics.Screen;
import com.gamc.webs.input.KeyInput;
import com.gamc.webs.input.Mouse;
import com.gamc.webs.level.Level;
import com.gamc.webs.sound.SoundManager;
import com.gamc.webs.sound.Clips;
import com.gamc.webs.util.MenuSelection;
import com.gamc.webs.util.PauseSelection;
import com.gamc.webs.util.PauseSelection;
import com.gamc.webs.util.TextDrawer;
import com.gamc.webs.util.MenuSelection;

public class NonStop extends Canvas implements Runnable{
    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 300;
    private static final int HEIGHT = WIDTH / 16 * 9;
    private static final int SCALE = 3;
    private static final String TITLE = "NonStop";
    private static final int UPDATES_PER_SECOND = 60;
    private static final int FRAMES_PER_SECOND = 120;
    private static NonStop game;
    public ScreenState state;
    private Thread thread;
    private JFrame frame;
    private KeyInput key;
    private Level level;
    private Level menuLevel;
    private Player player;
    public boolean running = false;
    private Screen screen;
    //public SoundManager mainMenuMusic = new SoundManager(SoundType.MUSIC_THEME);
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    public static enum ScreenState {
        MAIN_MENU, 
        OPTIONS, 
        LEVEL, 
        PAUSED, 
        CREDITS;
    }
    
    public static void main(String[] args) {
        game = new Webs();
        game.frame.setResizable(true);
        game.frame.setTitle(Webs.TITLE);
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();
    }

    public NonStop() {
        Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
        //setPreferredSize(size);

        game = this;
        screen = new Screen(WIDTH, HEIGHT);
        frame = new JFrame();
        level = Level.spawnLevel;
        menuLevel = Level.menuLevel;
        key = new KeyInput();
        player = new Player(120, 120, key,
            JOptionPane.showInputDialog("What is your username?"));
        player.init(level);
        state = ScreenState.MAIN_MENU;

        //addKeyListener(key);
        Mouse mouse = new Mouse();
        //addMouseListener(mouse);
        //addMouseMotionListener(mouse);
        //mainMenuMusic.loop();
    }

    public static void main(String[] args) {
        game = new NonStop();
        game.frame.setResizable(true);
        game.frame.setTitle(NonStop.TITLE);
        //game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();
    }

    public static NonStop getGame() {
        return game;
    }

    public JFrame getFrame() {
        return frame;
    }

    public int getWindowWidth() {
        return getGame().getWidth();
    }

    public int getWindowHeight() {
        return getGame().getHeight();
    }

    public void setScreen(Screen screen) {
        this.screen.clear();
        this.screen = screen;
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / UPDATES_PER_SECOND;
        final double nsFPS = 1000000000.0 / FRAMES_PER_SECOND;
        double delta = 0;
        double deltaFPS = 0;
        int frames = 0;
        int updates = 0;
        requestFocus();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            deltaFPS += (now - lastTime) / nsFPS;
            lastTime = now;
            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }
            while (deltaFPS >= 1) {
                render();
                frames++;
                deltaFPS--;
            }
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frame.setTitle(TITLE + "  |  Updates: " + updates
                    + "  |  FPS: " + frames);
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    public void update() {
        key.update();
        if (state == ScreenState.LEVEL) {
            player.update();
            level.update();
        }
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        if (state == ScreenState.MAIN_MENU) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            g.setColor(new Color(0x4BB0E6));
            g.fillRect(0, 0, getWindowWidth(), getWindowHeight());
            int xScroll = (player.x - screen.width / 2) + 8;
            int yScroll = (player.y - screen.height / 2) + 8;
            menuLevel.render(xScroll, yScroll, screen);
            for (int i = 0; i < pixels.length; i++) {
                pixels[i] = screen.pixels[i];
            }
            TextDrawer.drawCenteredString("The Fallen Empire", new Font("Plantagenet Cherokee", Font.PLAIN, 60), new Color(255, 50, 50), getWindowWidth(), getWindowHeight(), g, -150);
            TextDrawer.drawCenteredString("Singleplayer", new Font("Arial", Font.BOLD, MenuSelection.selection == SelectedMenuOption.SINGLEPLAYER ? 40 : 30), Color.WHITE, getWindowWidth(), getWindowHeight(), g, -75);
            TextDrawer.drawCenteredString("Multiplayer", new Font("Arial", Font.BOLD, MenuSelection.selection == SelectedMenuOption.MULTIPLAYER ? 40 : 30),Color.WHITE, getWindowWidth(), getWindowHeight(), g, 0);
            TextDrawer.drawCenteredString("Options", new Font("Arial", Font.BOLD, MenuSelection.selection == SelectedMenuOption.OPTIONS ? 40 : 30), Color.WHITE, getWindowWidth(), getWindowHeight(), g, 75);
            TextDrawer.drawCenteredString("Quit", new Font("Arial", Font.BOLD, MenuSelection.selection == SelectedMenuOption.QUIT ? 40 : 30), Color.WHITE, getWindowWidth(), getWindowHeight(), g, 150);
        } else if (state == ScreenState.LEVEL) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            int xScroll = (player.x - screen.width / 2) + 8;
            int yScroll = (player.y - screen.height / 2) + 8;
            level.render(xScroll, yScroll, screen);
            player.render(screen);
            for (int i = 0; i < pixels.length; i++) {
                pixels[i] = screen.pixels[i];
            }
        } else if (state == ScreenState.PAUSED) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            int xScroll = (player.x - screen.width / 2) + 8;
            int yScroll = (player.y - screen.height / 2) + 8;
            level.render(xScroll, yScroll, screen);
            for (int i = 0; i < pixels.length; i++) {
                pixels[i] = screen.pixels[i];
            }
            TextDrawer.drawCenteredString("The Fallen Empire", new Font("Plantagenet Cherokee", Font.PLAIN, 60), new Color(255, 50, 50), getWindowWidth(), getWindowHeight(), g, -150);
            TextDrawer.drawCenteredString("Game Paused", new Font("Arial", Font.BOLD, 50), new Color(0x30D1C6), getWindowWidth(), getWindowHeight(), g, -75);
            TextDrawer.drawCenteredString("Back To Game", new Font("Arial", Font.BOLD, PauseSelection.selection == PausedMenuOption.BACK_TO_GAME ? 40 : 30),Color.WHITE, getWindowWidth(), getWindowHeight(), g, 0);
            TextDrawer.drawCenteredString("Main Menu", new Font("Arial", Font.BOLD, PauseSelection.selection == PausedMenuOption.MAIN_MENU ? 40 : 30), Color.WHITE, getWindowWidth(), getWindowHeight(), g, 75);
        }
        g.dispose();
        bs.show();
        screen.clear();
    }
}