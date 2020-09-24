package engine.gfx.images;

/**
 * An ImageTile is really useful because it is a large image that contains all the images
 * to be used in the program. It is a way to save and not increase the number
 * from resource files.
 * Important: all the images contained in the original image must have the same size
 * width and height.
 * As it is an image type, it inherits from the <class>Image</class> class.
 *
 * This is copied from the 2D Java Game Engine from the channel Majoolwip
 * GitHub: https://github.com/Majoolwip
 * Youtube channel: https://www.youtube.com/channel/UCYdJWlQWeuhDZicBbxM0-mg
 * Youtube video where he explains the Renderer class: "2D Java Game Engine #8 : ImageTile"
 * https://www.youtube.com/watch?v=bNq1UxL2cmE&list=PL7dwpoQd3a8j6C9p5LqHzYFSkii6iWPZF&index=8
 *
 * @class: ImageTile
 * @autor: Sergio Martí Torregrosa
 * @date: 2020-07-06
 */
public class ImageTile extends Image {

    /**
     * The width which has the tiles of the bigger image
     */
    private int tileW;

    /**
     * The height which has the tiles of the bigger image
     */
    private int tileH;

    /**
     * Constructor
     *
     * @param path It is the absolute or relative path from which the image is extracted
     * @param tileW It is the size of the small images in width
     * @param tileH It is the size of small images in height
     */
    public ImageTile(String path, int tileW, int tileH) {
        super(path);
        this.tileW = tileW;
        this.tileH = tileH;
    }

    /**
     * Constructor
     *
     * @param image the image tile
     * @param tileW It is the size of the small images in width
     * @param tileH It is the size of small images in height
     */
    public ImageTile(Image image, int tileW, int tileH) {
        super();
        this.tileW = tileW;
        this.tileH = tileH;
        this.setW(image.getW());
        this.setH(image.getH());
        this.setP(image.getP());
    }

    /**
     * The method of obtaining a small image of the
     * original image.
     *
     * @param tileX The X position of the small image within the original image.
     * @param tileY The Y position of the small image within the original image.
     * @return returns a small image of the original image.
     */
    public Image getTileImage(int tileX, int tileY) {
        int[] p = new int[tileW * tileH];
        for ( int y = 0; y < tileH; y++ ) {
            for ( int x = 0; x < tileW; x++ ) {
                try {
                    p[x + y * tileW] = this.getP()[(x + tileX * tileW) + (y + tileY * tileH) * this.getW()];
                } catch ( ArrayIndexOutOfBoundsException e ) {
                    p[x + y * tileW] = 0xffffffff;
                }
            }
        }
        return new Image(p, tileW, tileH);
    }

    public int getTileW() {
        return tileW;
    }

    public int getTileH() {
        return tileH;
    }

}
