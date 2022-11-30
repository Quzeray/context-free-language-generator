package Application.UI;

import javax.swing.*;
import java.awt.*;

public final class Settings {
    public static final ImageIcon ICON = new ImageIcon("images/ico.png");

    public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int FRAME_WIDTH = 700;
    public static final int FRAME_HEIGHT = 500;
    public static final int FRAME_X = SCREEN_SIZE.width / 2 - FRAME_WIDTH / 2;
    public static final int FRAME_Y = SCREEN_SIZE.height / 2 - FRAME_HEIGHT / 2;
}
