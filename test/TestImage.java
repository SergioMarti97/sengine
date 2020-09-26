import engine.AbstractGame;
import engine.GameContainer;
import engine.gfx.Renderer;
import engine.gfx.images.Image;

public class TestImage extends AbstractGame {

    private Image image;

    private TestImage(String title) {
        super(title);
    }

    @Override
    public void initialize(GameContainer gc) {
        image = new Image(null);
    }

    @Override
    public void update(GameContainer gc, float dt) {

    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawImage(image, 0, 0);
    }

    public static void main(String[] args) {
        GameContainer gc = new GameContainer(new TestImage("Test Image"));
        gc.start();
    }

}
