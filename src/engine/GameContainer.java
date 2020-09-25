package engine;

import engine.gfx.Renderer;

import java.awt.event.KeyEvent;

/**
 * The <class>GameContainer</class> is the class that manages
 * all the programs. It mainly contains the code for the program loop
 * It is responsible for create the window <class>Window</class>, create the input
 * controller of keyboard and mouse <class>Input</class> and the object that handles
 * to draw on screen <class>Renderer</class>.
 *
 * This is copied from the 2D Java Game Engine from the channel Majoolwip
 * GitHub: https://github.com/Majoolwip
 * Youtube channel: https://www.youtube.com/channel/UCYdJWlQWeuhDZicBbxM0-mg
 * Youtube video where he explains the GameContainer class: "2D Java Game Engine #1 : Game Loop"
 * https://www.youtube.com/watch?v=4iPEjFUZNsw&list=PL7dwpoQd3a8j6C9p5LqHzYFSkii6iWPZF&index=1
 *
 * @class: GameContainer
 * @autor: Sergio Martí Torregrosa
 * @date: 2020-07-06
 */
public class GameContainer implements Runnable {

    /**
     * The needed constant for updating the screen
     */
    private final double UPDATE_CAP = 1.0 / 60.0;

    /**
     * This is the name of the version
     */
    private final String NAME_VERSION = "0.0.01";

    /**
     * The window of the program
     */
    private Window window;

    /**
     * The renderer object which contains all drawing methods
     */
    private Renderer renderer;

    /**
     * The game what is managed by the GameContainer
     */
    private AbstractGame game;

    /**
     * The input class which manages the user input
     */
    private Input input;

    /**
     * The title of the program. It will be shown in the main bar of the
     * windows
     */
    private String title;

    /**
     * The width of the screen. By default, 1080 pixels
     */
    private int width = 1080;

    /**
     * The height of the screen. By default, 720 pixels
     */
    private int height = 720;

    /**
     * The scale of the pixels, by default this is one
     */
    private float scale = 1.0f;

    /**
     * The time spent in do the frame
     */
    private double frameTime = 0;

    /**
     * The number of frames in each loop
     */
    private int frames = 0;

    /**
     * The frames per second
     */
    private int fps;

    /**
     * The running flag, if the program is running
     */
    private boolean running = false;

    /**
     * The flag for not overload a lot the CPU
     */
    private boolean isCappedTo60fps = false;

    /**
     * Flag for show some information in screen (the fps)
     */
    private boolean isShowingFpsInConsole = true;

    /**
     * Flag for show some information in screen (mouse position)
     */
    private boolean isShowingInformation = false;

    /**
     * Constructor
     * @param game It is the program, application or game to be managed / controlled
     */
    public GameContainer(AbstractGame game) {
        this.game = game;
        title = game.getTitle();
    }

    /**
     * This method shows interesting information on the screen such as fps, or the position of the mouse on the screen
     */
    private void showInformation() {
        renderer.drawText("FPS:" + fps, 0, 0, 0xffffffff );
        renderer.drawText("Mouse X: " + getInput().getMouseX() + " Y: " + getInput().getMouseY(), 0, 25, 0xffffffff);
    }

    /**
     * It is the method which is called to run the program. In this method
     * instantiate the fields of:
     * - <class> Window </class>: the program window.
     * - <class> Renderer </class>: the renderer, or the class that has all the rendering methods.
     * - <class> input </class>: the controller of the program's inputs. Clicks, keys, etc ...
     * This is also where the <method> initialize </method> of <class> AbstractGame </class> is called,
     * for which all the objects that the program, game or program has are also instantiated.
     * application.
     * Lastly, <method> run </method> of the <field> thread </field> field is also called
     */
    public void start() {
        window = new Window(this);
        renderer = new Renderer(this);
        input = new Input(this);
        Thread thread = new Thread(this);
        game.initialize(this);
        running = true;
        thread.run();
    }

    /**
     * This is the method to be called if you want to stop the program
     */
    public void stop() {
        running = false;
    }

    /**
     * This method inherits from <interface>Runnable</interface>. Runs
     * in the <class>GameContainer</class> thread. This is where
     * is the program loop, where the "frames" are calculated by
     * second and the elapsed time between update and update.
     * Also, this is where the <method>update</method> and
     * <method>render</method> of <class>AbstractGame</class>. Is the part
     * most important of this class.
     * @see Runnable
     */
    @Override
    public void run() {
        boolean render;

        double firstTime;
        double lastTime = System.nanoTime() / 1000000000.0;
        double passedTime;
        double unprocessedTime = 0;

        while ( running ) {
            render = !isCappedTo60fps; // render = isCappedTo60fps ? false : true

            firstTime = System.nanoTime() / 1000000000.0;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;

            unprocessedTime += passedTime;
            frameTime += passedTime;

            while ( unprocessedTime >= UPDATE_CAP ) {
                unprocessedTime -= UPDATE_CAP;
                render = true;

                game.update(this, (float)UPDATE_CAP);
                input.update();

                if ( input.isKeyUp(KeyEvent.VK_CONTROL) ) {
                    isShowingInformation = !isShowingInformation;
                }

                if ( frameTime >= 1.0 ) {
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                    if ( isShowingFpsInConsole ) {
                        System.out.println("FPS: " + fps);
                    }
                }
            }

            if ( render ) {
                renderer.clear();
                game.render(this, this.renderer);
                renderer.process();
                if ( isShowingInformation ) {
                    showInformation();
                }
                window.update();
                frames++;
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        dispose();
    }

    private void dispose() {

    }

    public Window getWindow() {
        return window;
    }

    public Input getInput() {
        return input;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getScale() {
        return scale;
    }

    public String getTitle() {
        return title + " - v." + NAME_VERSION + " - fps: " + fps + " " + width + "x" + height;
    }

    public double getUPDATE_CAP() {
        return UPDATE_CAP;
    }

    public boolean isShowingInformation() {
        return isShowingInformation;
    }

    public boolean isCappedTo60fps() {
        return isCappedTo60fps;
    }

    public boolean isShowingFpsInConsole() {
        return isShowingFpsInConsole;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCappedTo60fps(boolean cappedTo60fps) {
        isCappedTo60fps = cappedTo60fps;
    }

    public void setShowingInformation(boolean showingInformation) {
        isShowingInformation = showingInformation;
    }

}
