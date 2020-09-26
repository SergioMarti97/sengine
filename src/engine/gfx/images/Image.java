package engine.gfx.images;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * This class represents an image
 *
 * This is copied from the 2D Java Game Engine from the channel Majoolwip
 * GitHub: https://github.com/Majoolwip
 * Youtube channel: https://www.youtube.com/channel/UCYdJWlQWeuhDZicBbxM0-mg
 * Youtube video where he explains the Renderer class: "2D Java Game Engine #6 : Drawing an Image"
 * https://www.youtube.com/watch?v=bNq1UxL2cmE&list=PL7dwpoQd3a8j6C9p5LqHzYFSkii6iWPZF&index=6
 *
 * @class: Image
 * @autor: Sergio Martí Torregrosa
 * @date: 2020-07-06
 */
public class Image {

    /**
     * The image width
     */
    private int w;

    /**
     * The image height
     */
    private int h;

    /**
     * The image pixel array
     */
    private int[] p;

    /**
     * If the image has alpha
     */
    private boolean alpha = false;

    /**
     * The null constructor
     */
    public Image() {
        w = 0;
        h = 0;
        p = new int[w * h];
    }

    /**
     * The constructor
     * A path is needed to build the image
     *
     * The image can be inside the resources folder of
     * the project, or it can be in other folder outside
     * of the project. But, when the image is outside
     * of the project, the path needs to be absolute path
     *
     * @param path is the absolute or relative path where is the image
     */
    public Image(String path) {
        BufferedImage image = null;

        if ( path != null ) {

            try {
                InputStream inputStream = Image.class.getResourceAsStream(path);
                if ( inputStream != null ) {
                    image = ImageIO.read(inputStream);
                } else {
                    image = ImageIO.read(new File(path));
                }
            } catch ( NullPointerException e ) {
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("The image could not be read");
                e.printStackTrace();
            }

            assert image != null;
            w = image.getWidth();
            h = image.getHeight();
            p = image.getRGB(0, 0, w, h, null, 0, w);
            image.flush();

        } else {

            w = 0;
            h = 0;
            p = new int[w * h];

            System.out.println("The image path is null");

        }

    }

    /**
     * The constructor
     *
     * @param p the pixel array what conforms the image
     * @param w width
     * @param h height
     */
    public Image(int[] p, int w, int h) {
        this.p = p;
        this.w = w;
        this.h = h;
    }

    /**
     * The constructor
     *
     * @param w width
     * @param h height
     */
    public Image(int w, int h) {
        this.w = w;
        this.h = h;
        p = new int[w * h];
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public int[] getP() {
        return p;
    }

    public boolean isAlpha() {
        return alpha;
    }

    /**
     * This method returns an pixel for the image
     *
     * @param x the X position of the pixel inside the image
     * @param y the Y position of the pixel inside the image
     * @return a pixel for the image
     * @throws ArrayIndexOutOfBoundsException if the x and/or y positions are outside of the image
     */
    public int getPixel(int x, int y) throws ArrayIndexOutOfBoundsException {
        int index = x + w * y;
        if ( index < p.length ) {
            return p[x + w * y];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * This method returns a sample (pixel) of the image
     * @param x the X position of the pixel inside the image
     * @param y the Y position of the pixel inside the image
     * @return a pixel of the image
     * @throws ArrayIndexOutOfBoundsException if the x and/or y positions are outside of the image
     */
    public int getSample(float x, float y) {
        int color;
        int sampleX = Math.min((int)(x * (float)w), w -1);
        int sampleY = Math.min((int)(y * (float)h), h -1);
        try {
            color = getPixel(sampleX, sampleY);
        } catch ( ArrayIndexOutOfBoundsException e ) {
            color = 0x00000000;
            String errorMessage = "X: " + x + " Y: " + y + " outside of " + getW() + "x" + getH();
            System.out.println("Get sample Error: " + errorMessage + e.getMessage());
        }
        return color;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setP(int[] p) {
        this.p = p;
    }

    public void setAlpha(boolean alpha) {
        this.alpha = alpha;
    }

}
