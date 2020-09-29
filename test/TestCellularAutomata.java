import engine.AbstractGame;
import engine.GameContainer;
import engine.gfx.Renderer;

public class TestCellularAutomata extends AbstractGame {

    private int[] output;

    private int[] state;

    private int size;

    private TestCellularAutomata(String title) {
        super(title);
    }

    private void set(int x, int y, int width, String s) {
        int p = 0;
        for ( char c : s.toCharArray() ) {
            state[y * width + x + p] = ( c == '#')? 1 : 0;
            p++;
        }
    }

    private int getCell(int x, int y, int width) {
        return output[y * width + x];
    }

    @Override
    public void initialize(GameContainer gc) {
        size = gc.getWidth() * gc.getHeight();
        output = new int[size];
        state = new int[size];

        for ( int i = 0; i < size; i++ ) {
            output[i] = 0;
            state[i] = 0;
        }

        //set(80, 50, gc.getWidth(), "  ## ");
        //set(80, 51, gc.getWidth(), " ##  ");
        //set(80, 52, gc.getWidth(), "  #  ");

        set(60, 45, gc.getWidth(), "........................#............");
        set(60, 46, gc.getWidth(), "......................#.#............");
        set(60, 47, gc.getWidth(), "............##......##............##.");
        set(60, 48, gc.getWidth(), "...........#...#....##............##.");
        set(60, 49, gc.getWidth(), "##........#.....#...##...............");
        set(60, 50, gc.getWidth(), "##........#...#.##....#.#............");
        set(60, 51, gc.getWidth(), "..........#.....#.......#............");
        set(60, 52, gc.getWidth(), "...........#...#.....................");
        set(60, 53, gc.getWidth(), "............##.......................");

        //set(20, 50, gc.getWidth(), "########.#####...###......#######.#####");

    }

    @Override
    public void update(GameContainer gc, float dt) {
        if (size >= 0) System.arraycopy(state, 0, output, 0, size);

        for ( int x = 1; x < gc.getWidth() - 1; x++ ) {
            for ( int y = 1; y < gc.getHeight() - 1; y++ ) {
                int numNeighbours =
                        getCell(x - 1, y - 1, gc.getWidth()) +
                        getCell(x, y - 1, gc.getWidth()) +
                        getCell(x + 1, y - 1, gc.getWidth()) +
                        getCell(x - 1, y, gc.getWidth()) +
                        getCell(x + 1, y, gc.getWidth()) +
                        getCell(x - 1, y + 1, gc.getWidth()) +
                        getCell(x, y + 1, gc.getWidth()) +
                        getCell(x + 1, y + 1, gc.getWidth());

                if ( getCell(x, y, gc.getWidth()) == 1 ) {
                    state[y * gc.getWidth() + x] = (numNeighbours == 2 || numNeighbours == 3) ? 1 : 0;
                } else {
                    state[y * gc.getWidth() + x] = (numNeighbours == 3) ? 1 : 0;
                }
            }
        }
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        for ( int x = 1; x < gc.getWidth() - 1; x++ ) {
            for (int y = 1; y < gc.getHeight() - 1; y++) {
                if ( getCell(x, y, gc.getWidth()) == 1 ) {
                    r.setPixel(x, y, 0xffffffff);
                } else {
                    r.setPixel(x, y, 0xff000000);
                }
            }
        }
    }

    public static void main(String[] args) {
        GameContainer gc = new GameContainer(new TestCellularAutomata("Test Cellular Automata"));
        gc.setWidth(250);
        gc.setHeight(145);
        gc.setScale(5);
        gc.start();
    }

}
