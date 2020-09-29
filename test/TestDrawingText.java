import engine.AbstractGame;
import engine.GameContainer;
import engine.gfx.Renderer;

public class TestDrawingText extends AbstractGame {

    private StringBuilder stringBuilder;

    private TestDrawingText(String title) {
        super(title);
    }

    @Override
    public void initialize(GameContainer gc) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("XD ".repeat(100));
    }

    @Override
    public void update(GameContainer gc, float dt) {

    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawText(stringBuilder.toString(), 10, 10, 0xff88ffff);
    }

    public static void main(String[] args) {
        GameContainer gc = new GameContainer(new TestDrawingText("Test drawing text"));
        gc.start();
    }

}
