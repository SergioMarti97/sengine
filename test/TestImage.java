import engine.AbstractGame;
import engine.GameContainer;
import engine.gfx.HexColors;
import engine.gfx.Renderer;
import engine.gfx.images.Image;

/**
 * This class is a test for the image methods
 *
 * @class TestImage
 * @author Sergio Martí Torregrosa
 * @date 26/09/2020
 */
public class TestImage extends AbstractGame {

    private int sample;

    private Image image;

    private TestImage(String title) {
        super(title);
    }

    @Override
    public void initialize(GameContainer gc) {
        image = new Image(null);
        sample = image.getSample(0.99999875f, 0.00000125f);

        image = new Image("/NotFindImage.png");
        sample = image.getSample(0.99999875f, 0.00000125f);
    }

    @Override
    public void update(GameContainer gc, float dt) {

    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawImage(image,
                gc.getInput().getMouseX() - image.getW() / 2,
                gc.getInput().getMouseY() - image.getH() / 2);
        r.drawText(String.format("Sample: %d", sample), 10, 10, HexColors.WHITE);
    }

    public static void main(String[] args) {
        GameContainer gc = new GameContainer(new TestImage("Test Image"));
        gc.start();
    }

}
