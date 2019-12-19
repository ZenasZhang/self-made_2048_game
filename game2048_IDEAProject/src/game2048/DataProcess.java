package game2048;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class DataProcess extends JPanel implements KeyListener {

    private int[][] dataSet;
    private int scoreUp = 0;
    private int scoreDown = 0;
    private int scoreLeft = 0;
    private int scoreRight = 0;
    private int score = 0;
    private boolean canSlideUp = true;
    private boolean canSlideDown = true;
    private boolean canSlideRight = true;
    private boolean canSlideLeft = true;
    private boolean isFailed = false;

    public DataProcess() {
        init();
        this.setFocusable(true);
        this.addKeyListener(this);
    }



    private void init() {
        int[][] dataSet = new int[4][4];
        for (int i = 0; i < 4; i++) {
            int[] line = new int[]{0, 0, 0, 0};
            dataSet[i] = line;
        }
        dataSet[1][0] = 2;
        dataSet[2][0] = 2;
        this.dataSet = dataSet;

    }

    public int[][] getDataSet() {
        return dataSet;
    }

    public void paintComponent(Graphics g) {   //主画笔
        super.paintComponent(g);
        g.setColor(Color.black);
        this.setBackground(Color.lightGray);
        g.setFont(new Font("", Font.BOLD, 25));
        g.drawString("请按方向键操作",10,50);
        g.setFont(new Font("", Font.BOLD, 30));
        g.drawString("分数: "+this.score,220,50);
        g.drawLine(0,100,400,100);
        g.drawLine(0,200,400,200);
        g.drawLine(0,300,400,300);
        g.drawLine(0,400,400,400);
        g.drawLine(100,100,100,500);
        g.drawLine(200,100,200,500);
        g.drawLine(300,100,300,500);
        g.setFont(new Font("", Font.BOLD, 50));
        if (isFailed) {
            g.setColor(Color.black);
            g.drawString("you have lost",40,200);
            g.setColor(Color.white);
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (this.dataSet[i][j]!= 0){
                        g.drawString(String.valueOf(this.dataSet[i][j]),100*j+15,170+100*i);
                    }
                }
            }
        } else {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (this.dataSet[i][j] == 0){
                        continue;
                    }else if (this.dataSet[i][j] == 2){
                        g.setColor(new Color(255, 254, 15));
                        g.drawString(String.valueOf(this.dataSet[i][j]),100*j+15,170+100*i);
                    } else if (this.dataSet[i][j] == 4){
                        g.setColor(new Color(255, 225, 8));
                        g.drawString(String.valueOf(this.dataSet[i][j]),100*j+15,170+100*i);
                    }else if (this.dataSet[i][j] == 8){
                        g.setColor(new Color(255, 193, 10));
                        g.drawString(String.valueOf(this.dataSet[i][j]),100*j+15,170+100*i);
                    }else if (this.dataSet[i][j] == 16){
                        g.setColor(new Color(255, 145, 6));
                        g.drawString(String.valueOf(this.dataSet[i][j]),100*j+15,170+100*i);
                    }else if (this.dataSet[i][j] == 32){
                        g.setColor(new Color(255, 92, 10));
                        g.drawString(String.valueOf(this.dataSet[i][j]),100*j+15,170+100*i);
                    }else if (this.dataSet[i][j] == 64){
                        g.setColor(new Color(255, 70, 5));
                        g.drawString(String.valueOf(this.dataSet[i][j]),100*j+15,170+100*i);
                    }else if (this.dataSet[i][j] == 128){
                        g.setColor(new Color(255, 4, 43));
                        g.drawString(String.valueOf(this.dataSet[i][j]),100*j+15,170+100*i);
                    }else if (this.dataSet[i][j] == 256){
                        g.setColor(new Color(255, 1, 102));
                        g.drawString(String.valueOf(this.dataSet[i][j]),100*j+15,170+100*i);
                    }else if (this.dataSet[i][j] == 512){
                        g.setColor(new Color(255, 2, 163));
                        g.drawString(String.valueOf(this.dataSet[i][j]),100*j+15,170+100*i);
                    }else if (this.dataSet[i][j] == 1024){
                        g.setColor(new Color(255, 6, 249));
                        g.drawString(String.valueOf(this.dataSet[i][j]),100*j+15,170+100*i);
                    }
                }
            }
        }
    }

    public int[][] slideUp(int[][] intDoubleArray) {
        for (int i = 0; i < 4; i++) {
            ArrayList<Integer> column = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                if (intDoubleArray[j][i] != 0) {
                    Integer integer = new Integer(intDoubleArray[j][i]);
                    column.add(integer);
                }
            }
            if (column.isEmpty()) {
                continue;
            }
            this.scoreUp += oneLineProcess(column);
            for (int j = 0; j < 4; j++) {
                if (j < column.size()) {
                    intDoubleArray[j][i] = column.get(j).intValue();
                } else {
                    intDoubleArray[j][i] = 0;
                }
            }
        }
        return intDoubleArray;
    }

    public int[][] slideDown(int[][] intDoubleArray) {
        for (int i = 0; i < 4; i++) {
            ArrayList<Integer> column = new ArrayList<>();
            for (int j = 3; j > -1; j--) {
                if (intDoubleArray[j][i] != 0) {
                    Integer integer = new Integer(intDoubleArray[j][i]);
                    column.add(integer);
                }
            }
            if (column.isEmpty()) {
                continue;
            }
            this.scoreDown += oneLineProcess(column);
            for (int j = 0; j < 4; j++) {
                if (j < column.size()) {
                    intDoubleArray[3 - j][i] = column.get(j).intValue();
                } else {
                    intDoubleArray[3 - j][i] = 0;
                }
            }
        }
        return intDoubleArray;
    }

    public int[][] slideLeft(int[][] intDoubleArray) {
        for (int i = 0; i < 4; i++) {
            ArrayList<Integer> column = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                if (intDoubleArray[i][j] != 0) {
                    Integer integer = new Integer(intDoubleArray[i][j]);
                    column.add(integer);
                }
            }
            if (column.isEmpty()) {
                continue;
            }
            this.scoreLeft += oneLineProcess(column);
            for (int j = 0; j < 4; j++) {
                if (j < column.size()) {
                    intDoubleArray[i][j] = column.get(j).intValue();
                } else {
                    intDoubleArray[i][j] = 0;
                }
            }
        }
        return intDoubleArray;
    }

    public int[][] slideRight(int[][] intDoubleArray) {
        for (int i = 0; i < 4; i++) {
            ArrayList<Integer> column = new ArrayList<>();
            for (int j = 3; j > -1; j--) {
                if (intDoubleArray[i][j] != 0) {
                    Integer integer = new Integer(intDoubleArray[i][j]);
                    column.add(integer);
                }
            }
            if (column.isEmpty()) {
                continue;
            }
            this.scoreRight += oneLineProcess(column);
            for (int j = 0; j < 4; j++) {
                if (j < column.size()) {
                    intDoubleArray[i][3 - j] = column.get(j).intValue();
                } else {
                    intDoubleArray[i][3 - j] = 0;
                }
            }
        }
        return intDoubleArray;
    }

    private int oneLineProcess(ArrayList<Integer> line) {
        int oneLineScore = 0;
        for (int j = 0; j < line.size() - 1; j++) {
            if (line.get(j).equals(line.get(j + 1))) {
                line.set(j, line.get(j).intValue() * 2);
                oneLineScore += line.get(j).intValue();
                line.set(j + 1, 0);
                j++;
            }
        }
        for (int j = 0; j < 2; j++) {
            line.remove((Integer) 0);
        }
        return oneLineScore;
    }

    private void ifCanSlideUp(){
        int[][] before = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                before[i][j] = this.dataSet[i][j];
            }
        }
        int[][] after = slideUp(this.dataSet).clone();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (!(before[i][j] == after[i][j])) {
                    canSlideUp = true;
                    for (int a = 0; a < 4; a++) {
                        for (int b = 0; b < 4; b++) {
                             this.dataSet[a][b] = before[a][b];
                        }
                    }
                    return;
                }
            }
        }
        canSlideUp = false;
    }

    private void ifCanSlideDown(){
        int[][] before = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                before[i][j] = this.dataSet[i][j];
            }
        }
        int[][] after = slideDown(this.dataSet).clone();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (!(before[i][j] == after[i][j])) {
                    canSlideDown = true;
                    for (int a = 0; a < 4; a++) {
                        for (int b = 0; b < 4; b++) {
                            this.dataSet[a][b] = before[a][b];
                        }
                    }
                    return;
                }
            }
        }
        canSlideDown = false;
    }

    private void ifCanSlideLeft(){
        int[][] before = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                before[i][j] = this.dataSet[i][j];
            }
        }
        int[][] after = slideLeft(this.dataSet).clone();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (!(before[i][j] == after[i][j])) {
                    canSlideLeft = true;
                    for (int a = 0; a < 4; a++) {
                        for (int b = 0; b < 4; b++) {
                            this.dataSet[a][b] = before[a][b];
                        }
                    }
                    return;
                }
            }
        }
        canSlideLeft = false;
    }

    private void ifCanSlideRight(){
        int[][] before = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                before[i][j] = this.dataSet[i][j];
            }
        }
        int[][] after = slideRight(this.dataSet).clone();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (!(before[i][j] == after[i][j])) {
                    canSlideRight = true;
                    for (int a = 0; a < 4; a++) {
                        for (int b = 0; b < 4; b++) {
                            this.dataSet[a][b] = before[a][b];
                        }
                    }
                    return;
                }
            }
        }
        canSlideRight = false;
    }

    private void ifFailed() {
        if (!canSlideDown && !canSlideUp && !canSlideRight && !canSlideLeft) {
            isFailed = true;
        } else {
            isFailed = false;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        ifCanSlideRight();
        ifCanSlideLeft();
        ifCanSlideUp();
        ifCanSlideDown();
        ifFailed();
        if (!isFailed){
            if (keyCode == KeyEvent.VK_LEFT && canSlideLeft) {
                this.score += this.scoreLeft;
                slideLeft(this.dataSet);
                addRandomNumber(this.dataSet);
                repaint();
            } else if (keyCode == KeyEvent.VK_RIGHT && canSlideRight) {
                this.score += this.scoreRight;
                slideRight(this.dataSet);
                addRandomNumber(this.dataSet);
                repaint();
            } else if (keyCode == KeyEvent.VK_UP && canSlideUp) {
                this.score += this.scoreUp;
                slideUp(this.dataSet);
                addRandomNumber(this.dataSet);
                repaint();
            } else if (keyCode == KeyEvent.VK_DOWN && canSlideDown) {
                this.score += this.scoreDown;
                slideDown(this.dataSet);
                addRandomNumber(this.dataSet);
                repaint();
            }
        } else {
            repaint();
        }
        this.scoreRight = 0;
        this.scoreLeft = 0;
        this.scoreUp = 0;
        this.scoreDown = 0;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private void addRandomNumber(int[][] intDoubleArray) {
        Random random = new Random();
        ArrayList<int[]> blankCoordinate = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (intDoubleArray[i][j] == 0) {
                    blankCoordinate.add(new int[]{i,j});
                }
            }
        }
        // there is a 10% possibility to get a 4
        if (random.nextInt(10)>0){
            int[] coordinate = blankCoordinate.get(random.nextInt(blankCoordinate.size()));
            intDoubleArray[coordinate[0]][coordinate[1]] = 2;
        }else {
            int[] coordinate = blankCoordinate.get(random.nextInt(blankCoordinate.size()));
            intDoubleArray[coordinate[0]][coordinate[1]] = 4;
        }
    }
}
