package game2048;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setBounds(0,0,400,530);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("2048 Game");
        DrawData panel = new DrawData();
        frame.add(panel);
        frame.setVisible(true);
    }
}
