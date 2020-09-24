package engine;

import engine.vectors.points2d.Vec2di;

import java.awt.event.*;

/**
 * This class manages the inputs of the user
 * The mouse input and keyboard input
 *
 * This is copied from the 2D Java Game Engine from the channel Majoolwip
 * GitHub: https://github.com/Majoolwip
 * Youtube channel: https://www.youtube.com/channel/UCYdJWlQWeuhDZicBbxM0-mg
 * Youtube video where he explains the Renderer class: "2D Java Game Engine #4 : Input"
 * https://www.youtube.com/watch?v=bNq1UxL2cmE&list=PL7dwpoQd3a8j6C9p5LqHzYFSkii6iWPZF&index=4
 *
 * @class: Input
 * @autor: Sergio Martí Torregrosa
 * @date: 2020-07-06
 */
public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

    private GameContainer gc;

    private final int NUM_KEYS = 256;

    private boolean[] keysState = new boolean[NUM_KEYS];

    private boolean[] keysStateLast = new boolean[NUM_KEYS];

    private final int NUM_BUTTONS = 5;

    private boolean[] buttons = new boolean[NUM_BUTTONS];

    private boolean[] buttonsLast = new boolean[NUM_BUTTONS];

    private int mouseX;

    private int mouseY;

    private int scroll;

    public Input(GameContainer gc) {
        this.gc = gc;
        mouseX = 0;
        mouseY = 0;
        scroll = 0;

        gc.getWindow().getCanvas().addKeyListener(this);
        gc.getWindow().getCanvas().addMouseListener(this);
        gc.getWindow().getCanvas().addMouseMotionListener(this);
        gc.getWindow().getCanvas().addMouseWheelListener(this);
    }

    public void update() {
        System.arraycopy(keysState, 0, keysStateLast, 0, NUM_KEYS);
        System.arraycopy(buttons, 0, buttonsLast, 0, NUM_BUTTONS);
    }

    public boolean isKey(int keyCode) {
        return keysState[keyCode];
    }

    public boolean isKeyUp(int keyCode) {
        return !keysState[keyCode] && keysStateLast[keyCode];
    }

    public boolean isKeyDown(int keyCode) {
        return keysState[keyCode] && !keysStateLast[keyCode];
    }

    public boolean isKeyHeld(int keyCode) {
        return keysState[keyCode] && keysStateLast[keyCode];
    }

    public boolean isButton(int button) {
        return buttons[button];
    }

    public boolean isButtonUp(int button) {
        return !buttons[button] && buttonsLast[button];
    }

    public boolean isButtonDown(int button) {
        return buttons[button] && !buttonsLast[button];
    }

    public boolean isButtonHeld(int button) {
        return buttons[button] && buttonsLast[button];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keysState[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keysState[e.getKeyCode()] = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        buttons[e.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        buttons[e.getButton()] = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = (int)(e.getX() / gc.getScale());
        mouseY = (int)(e.getY() / gc.getScale());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = (int)(e.getX() / gc.getScale());
        mouseY = (int)(e.getY() / gc.getScale());
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        scroll += e.getWheelRotation();
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public Vec2di getMouse() {
        return new Vec2di(mouseX, mouseY);
    }

    public int getScroll() {
        return scroll;
    }

}
