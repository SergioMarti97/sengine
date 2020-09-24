package engine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * The class that controls the window. It is built with the
 * information present in <class>GameContainer</class>
 *
 * This is copied from the 2D Java Game Engine from the channel Majoolwip
 * GitHub: https://github.com/Majoolwip
 * Youtube channel: https://www.youtube.com/channel/UCYdJWlQWeuhDZicBbxM0-mg
 * Youtube video where he explains the Window class: "2D Java Game Engine #2 : Window"
 * https://www.youtube.com/watch?v=fVEuGM32VDY&list=PL7dwpoQd3a8j6C9p5LqHzYFSkii6iWPZF&index=2
 *
 * @class: Window
 * @autor: Sergio Martí Torregrosa
 * @date: 2020-07-06
 */
public class Window {

    /**
     * The frame
     */
    private JFrame frame;

    /**
     * The image what is build for each frame
     */
    private BufferedImage image;

    /**
     * The canvas which is drawn with all the information
     * for build the image
     */
    private Canvas canvas;

    /**
     * buffer strategy for fill the image
     */
    private BufferStrategy bs;

    /**
     * The graphics object for drawing the image
     */
    private Graphics g;

    /**
     * The GameContainer object
     */
    private GameContainer gc;

    /**
     * This method is the one that creates:
     * - the image <class> BufferedImage </class>.
     * - The <class> JFrame </class> frame. It is the class itself from the <package> swing </package> Java package that
     * creates the window.
     * - The canvas or canvas <class> Canvas </class>.
     * - The bufferStrategy <class> BufferStrategy </class>.
     * - The graphics object <class> Graphics </class>.
     * @param gc The <class> GameContainer </class> class object, which contains all the application information.
     */
    public Window(GameContainer gc) {
        this.gc = gc;
        image = new BufferedImage(gc.getWidth(), gc.getHeight(), BufferedImage.TYPE_INT_RGB);

        canvas = new Canvas();
        Dimension s = new Dimension((int)(gc.getWidth() * gc.getScale()), (int)(gc.getHeight() * gc.getScale()));
        canvas.setPreferredSize(s);
        canvas.setMaximumSize(s);
        canvas.setMinimumSize(s);

        frame = new JFrame(gc.getTitle());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(canvas, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        bs = canvas.getBufferStrategy();
        g = bs.getDrawGraphics();
    }

    /**
     * The update method is responsible for updating the title of the window's title bar
     * and to redraw the window.
     */
    public void update() {
        frame.setTitle(gc.getTitle());
        g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
        bs.show();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public BufferedImage getImage() {
        return image;
    }

    public JFrame getFrame() {
        return frame;
    }

}
