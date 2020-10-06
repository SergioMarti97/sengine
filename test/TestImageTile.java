import engine.AbstractGame;
import engine.GameContainer;
import engine.gfx.Renderer;
import engine.gfx.images.ImageTile;

/**
 * This class is a test of the method from the class
 * ImageTile, "getImage" passing by parameter the index
 * of the tile inside the image sheet
 *
 * @class TestImageTile
 * @author Sergio Martí Torregrosa
 * @date 01/10/2020
 */
public class TestImageTile extends AbstractGame {

    private ImageTile imageTile = new ImageTile("/imageTile.png", 16, 16);

    private TestImageTile(String title) {
        super(title);
    }

    @Override
    public void initialize(GameContainer gc) {

    }

    @Override
    public void update(GameContainer gc, float dt) {

    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawImage(imageTile.getTileImage(10), 10, 10);
    }

    public static void main(String[] args) {
        GameContainer gc = new GameContainer(new TestImageTile("Testing image tile"));
        gc.start();
    }

}
