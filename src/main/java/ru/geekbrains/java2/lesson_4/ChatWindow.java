package ru.geekbrains.java2.lesson_4;

import javax.swing.*;
import java.awt.*;

public class ChatWindow extends JFrame {

    private static final Double SIZE_RATIO = 0.6;
    private static final Double WINDOW_RATIO = 0.75;
    private static final Double MIN_SIZE_RATIO = 0.2;


    public ChatWindow() throws HeadlessException {
        init();
    }

    private void init() {
        setTitle("Чат");
        setMinimumSize(calculateMinimumSize());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(calculateSizePosition());
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        TextArea textArea = new TextArea();
        add(textArea);
        TextField textField = new TextField();
        textField.setMaximumSize(new Dimension(
                Toolkit.getDefaultToolkit().getScreenSize().width,
                textField.getMinimumSize().height));
        add(textField);
        setVisible(true);
    }

    private static Dimension calculateMinimumSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension minimumSize = new Dimension();
        minimumSize.width = (int) (screenSize.width * MIN_SIZE_RATIO);
        minimumSize.height = (int) (minimumSize.width / WINDOW_RATIO);
        return minimumSize;
    }

    private static Rectangle calculateSizePosition() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle sizePosition = new Rectangle();
        sizePosition.height = (int) (screenSize.getHeight() * SIZE_RATIO);
        sizePosition.width = (int) (sizePosition.height * WINDOW_RATIO);
        return sizePosition;
    }

    public static void main(String[] args) {
        ChatWindow chatWindow = new ChatWindow();
    }
}
