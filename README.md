## Game Engine by Sergio Martí Torregrosa
<p>This is a project made by Sergio Martí Torregrosa. It pretends to be a simple 
game engine to develop window applications, coded in Java 12.</p>
<p>Currently it uses the java.swing package, however in the future the project 
is wanted to be able to use JavaFx also.</p>
<p>The project has been made following the series "2D Java Game Engine" from
the YouTube channel Majoolwip.<br>
GitHub: <a>https://github.com/Majoolwip</a><br>
Youtube channel: <a>https://www.youtube.com/channel/UCYdJWlQWeuhDZicBbxM0-mg</a><br>
Youtube series: "2D Java Game Engine" <a>https://www.youtube.com/watch?v=4iPEjFUZNsw&list=PL7dwpoQd3a8j6C9p5LqHzYFSkii6iWPZF&index=1</a>
</p>
<p>Thought, part of the code is inspired from the PixelGameEngine, develop by David Barr (Javidx9) 
<a>https://github.com/OneLoneCoder</a>, and some drawing algorithms from Renderer class are copied.</p>
<p>Project date start: 06/07/2020.</p>

## Installation and Configuration
There is two ways for install: 
1. Download all the classes and build its .jar, start another project adding the jar as external library.
2. Download all the project and code your program in other folder next to "engine" folder.

The engine uses some resources images for the fonts, so it is important download the images also and added
them to the resources folder of your project.

### How to make a new application
1. Make a new class which inherits (extends) from the abstract class "AbstractGame".
2. Implement all class methods.
3. Make a matching class constructor.
4. Inside the main function, make an instance of the class GameContainer. To the constructor of GameContainer, 
instance a new object from your class, and add the title for the application.
5. Call the start method of the object from GameContainer.
6. Compile, build and run your code.

<p>The default size of the window is set to 1080 pixels width, 720 pixels height and 1 for pixel size. 
It is able to set other values with the provided methods in the class GameContainer.</p>

There will be three methods for all applications:
* **initialize**: the user has to create things here.
* **update**: user input management, update or modify objects.
* **render**: draw the program output screen.

Example application:
```java
import engine.AbstractGame;
import engine.GameContainer;
import engine.gfx.Renderer;

public class Example extends AbstractGame {

    /**
     * Constructor
     *
     * @param title title
     */
    public Example(String title) {
        super(title);
    }

    private int getRandomColorHexBetweenRange(int max, int min) {
        return (int) ((Math.random() * ((max - min) + 1)) + min);
    }

    @Override
    public void initialize(GameContainer gc) {

    }

    @Override
    public void update(GameContainer gc, float dt) {

    }

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
```

## Main developer
<p>The main programmer for this project is Sergio Martí Torregrosa.</p>

## Contributing
<p>Pull requests are welcome. For major changes, please open an issue first to discuss what you 
would like to change.</p>
<p>Please make sure to update tests as appropriate.</p>

## Credits and Acknowledgment
<p>
Big thanks to David Barr, the OneLoneCoder community and Majoolwip. 
Infinite thanks to my father Rubén Martí Dominguez for teaching me to code. 
Finally, I also thanks to Alexandre Coloma Gisbert as a tutor and the entire team of teachers at the CIPFP Batoi.
</p>