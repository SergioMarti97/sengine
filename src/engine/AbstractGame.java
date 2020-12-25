package engine;

import engine.gfx.Painter;
import engine.gfx.Renderer;

/**
 * The AbstracGame class is the class from which all possible programs that are
 * handled by <class>GameContainer</class> inherits.
 *
 * This is copied from the 2D Java Game Engine from the channel Majoolwip
 * GitHub: https://github.com/Majoolwip
 * Youtube channel: https://www.youtube.com/channel/UCYdJWlQWeuhDZicBbxM0-mg
 * Youtube video where he explains the Renderer class: "2D Java Game Engine #5 : Abstract Game"
 * https://www.youtube.com/watch?v=bNq1UxL2cmE&list=PL7dwpoQd3a8j6C9p5LqHzYFSkii6iWPZF&index=5
 *
 * @class: AbstractGame
 * @autor: Sergio Martí Torregrosa
 * @date: 2020-07-06
 */
public abstract class AbstractGame {

    /**
     * The title of the program or application
     */
    private String title;

    /**
     * Constructor
     * @param title The title that the program will have in the title bar of the window
     */
    public AbstractGame(String title) {
        this.title = title;
    }

    /**
     * The method of initialization of the program. Where the objects to be
     * used are instantiated, global variables are assigned, etc ...
     * @param gc The <class>GameContainer</class> object that handles the <class>AbstractGame</class> program.
     */
    public abstract void initialize(GameContainer gc);

    /**
     * The method of updating the program. This part of the code is where user input is handled
     * with the <class>Input</class> and global objects or variables are updated.
     * @param gc The <class>GameContainer</class> object that handles the <class>AbstractGame</class> program.
     * @param dt It is the elapsed time variable, the equivalent of fElapsedTime.
     */
    public abstract void update(GameContainer gc, float dt);

    /**
     * The method of drawing (rendering). This part of the code is where all the
     * graphics that the programs will show are drawn.
     * @param gc The <class>GameContainer</class> object that handles the <class>AbstractGame</class> program.
     * @param p The <class>Painter</class> object which can be a Renderer, Renderer2D, Renderer3D or a PipeLine.
     */
    public void render(GameContainer gc, Painter p) {

    }

    public void render(GameContainer gc, Renderer r) {

    }

    /**
     * The getter for the <field>title</field>
     */
    public String getTitle() {
        return title;
    }

    /**
     * The setter for the <field>title</field>
     * @param title the new title for the Game
     */
    public void setTitle(String title) {
        this.title = title;
    }

}
