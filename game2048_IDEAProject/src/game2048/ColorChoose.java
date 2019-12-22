package game2048;

import java.awt.Color;

/**
 * code optimize:
 * using a class to choose color for each number
 */

public class ColorChoose {

    private int number;
    private Color color;

    public ColorChoose(int number) {
        this.number = number;
        chooseColor(this.number);
    }

    public Color getColor() {
        return color;
    }

    private void chooseColor (int number) {
        if (number == 2){
            this.color = new Color(255, 254, 15);
        } else if (number == 4){
            this.color = new Color(255, 225, 8);
        }else if (number == 8){
            this.color = new Color(255, 193, 10);
        }else if (number == 16){
            this.color = new Color(255, 145, 6);
        }else if (number == 32){
            this.color = new Color(255, 92, 10);
        }else if (number == 64){
            this.color = new Color(255, 70, 5);
        }else if (number == 128){
            this.color = new Color(255, 4, 43);
        }else if (number == 256){
            this.color = new Color(255, 1, 102);
        }else if (number == 512){
            this.color = new Color(255, 2, 163);
        }else if (number == 1024){
            this.color = new Color(255, 6, 249);
        } else if (number == 2048){
            this.color = new Color(159, 0, 255);
        }
    }
}
