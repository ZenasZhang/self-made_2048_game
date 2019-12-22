package game2048;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DrawData extends JPanel implements KeyListener {

    private DataProcess dataProcess = new DataProcess();

    public DrawData() {
        this.setFocusable(true);
        this.addKeyListener(this);
    }

    public void paintComponent(Graphics g) {   //主画笔
        super.paintComponent(g);
        g.setColor(Color.black);
        this.setBackground(Color.lightGray);
        g.setFont(new Font("", Font.BOLD, 25));
        g.drawString("请按方向键操作",10,50);
        g.setFont(new Font("", Font.BOLD, 30));
        g.drawString("分数: "+this.dataProcess.score,220,50);
        g.drawLine(0,100,400,100);
        g.drawLine(0,200,400,200);
        g.drawLine(0,300,400,300);
        g.drawLine(0,400,400,400);
        g.drawLine(100,100,100,500);
        g.drawLine(200,100,200,500);
        g.drawLine(300,100,300,500);
        g.setFont(new Font("", Font.BOLD, 50));
        if (this.dataProcess.isFailed()) {
            g.setColor(Color.black);
            g.drawString("you have lost",40,200);
            g.setColor(Color.white);
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (this.dataProcess.getDataSet()[i][j] != 0){
                        g.drawString(String.valueOf(this.dataProcess.getDataSet()[i][j]),100*j+15,170+100*i);
                    }
                }
            }
        } else {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (this.dataProcess.getDataSet()[i][j] == 0){
                        continue;
                    } else {
                        ColorChoose colorChoose = new ColorChoose(this.dataProcess.getDataSet()[i][j]);
                        g.setColor(colorChoose.getColor());
                        g.drawString(String.valueOf(this.dataProcess.getDataSet()[i][j]),100*j+15,170+100*i);
                    }
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        this.dataProcess.ifCanSlideRight();
        this.dataProcess.ifCanSlideLeft();
        this.dataProcess.ifCanSlideUp();
        this.dataProcess.ifCanSlideDown();
        this.dataProcess.ifFailed();
        if (!this.dataProcess.isFailed()){
            if (keyCode == KeyEvent.VK_LEFT && this.dataProcess.isCanSlideLeft()) {
                this.dataProcess.score += this.dataProcess.scoreLeft;
                this.dataProcess.slideLeft(this.dataProcess.getDataSet());
                this.dataProcess.addRandomNumber(this.dataProcess.getDataSet());
                repaint();
            } else if (keyCode == KeyEvent.VK_RIGHT && this.dataProcess.isCanSlideRight()) {
                this.dataProcess.score += this.dataProcess.scoreRight;
                this.dataProcess.slideRight(this.dataProcess.getDataSet());
                this.dataProcess.addRandomNumber(this.dataProcess.getDataSet());
                repaint();
            } else if (keyCode == KeyEvent.VK_UP && this.dataProcess.isCanSlideUp()) {
                this.dataProcess.score += this.dataProcess.scoreUp;
                this.dataProcess.slideUp(this.dataProcess.getDataSet());
                this.dataProcess.addRandomNumber(this.dataProcess.getDataSet());
                repaint();
            } else if (keyCode == KeyEvent.VK_DOWN && this.dataProcess.isCanSlideDown()) {
                this.dataProcess.score += this.dataProcess.scoreDown;
                this.dataProcess.slideDown(this.dataProcess.getDataSet());
                this.dataProcess.addRandomNumber(this.dataProcess.getDataSet());
                repaint();
            }
        } else {
            repaint();
        }
        this.dataProcess.scoreRight = 0;
        this.dataProcess.scoreLeft = 0;
        this.dataProcess.scoreUp = 0;
        this.dataProcess.scoreDown = 0;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}
