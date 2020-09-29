import engine.AbstractGame;
import engine.GameContainer;
import engine.gfx.Renderer;

/**
 * This is an example of a application made with this engine.
 *
 * @class: Example
 * @autor: Sergio Martí Torregrosa
 * @date: 2020-07-20
 */
public class Example extends AbstractGame {

    /**
     * Class constructor
     * @param title the program title. It will be shown at title bar
     */
    public Example(String title) {
        super(title);
    }

    /**
     * Calcula un valor Hexadecimal correspondiente a un color
     * dentro de un rango expecificado.
     * @param max maximum color value
     * @param min minimum color value
     * @return returns an hexadecimal code of a random color between the specified color as a parameters
     */
    private int getRandomColorHexBetweenRange(int max, int min) {
        return (int) ((Math.random() * ((max - min) + 1)) + min);
    }

    /**
     * The initialization method
     * @param gc the object <class>GameContainer</class> which manages the <class>AbstractGame</class>
     */
    @Override
    public void initialize(GameContainer gc) {

    }

    /**
     * The actualization method
     * @param gc the object <class>GameContainer</class> which manages the <class>AbstractGame</class>
     * @param dt the elapsed time between two frames
     */
    @Override
    public void update(GameContainer gc, float dt) {

    }

    /**
     * The draw or rendering method
     * @param gc the object <class>GameContainer</class> which manages the <class>AbstractGame</class>
     * @param r  the object <class>Render</class> which contains all render methods
     */
    @Override
    public void render(GameContainer gc, Renderer r) {
        for ( int x = 0; x < gc.getWidth(); x++ ) {
            for ( int y = 0; y < gc.getHeight(); y++ ) {
                r.setPixel(x, y, getRandomColorHexBetweenRange(0xffffffff, 0xff000000));
            }
        }
    }

    public static void main(String[] args) {
        GameContainer gc = new GameContainer(new Example("Example"));
        gc.setWidth(540);
        gc.setHeight(360);
        gc.setScale(2.0f);
        gc.start();
    }

}
