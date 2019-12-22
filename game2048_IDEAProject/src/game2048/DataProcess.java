package game2048;

import java.util.ArrayList;
import java.util.Random;

public class DataProcess {

    private int[][] dataSet;
    public int scoreUp = 0;
    public int scoreDown = 0;
    public int scoreLeft = 0;
    public int scoreRight = 0;
    public int score = 0;
    private boolean canSlideUp = true;
    private boolean canSlideDown = true;
    private boolean canSlideRight = true;
    private boolean canSlideLeft = true;
    private boolean isFailed = false;

    public int[][] getDataSet() {
        return dataSet;
    }

    public boolean isCanSlideUp() {
        return canSlideUp;
    }

    public boolean isCanSlideDown() {
        return canSlideDown;
    }

    public boolean isCanSlideRight() {
        return canSlideRight;
    }

    public boolean isCanSlideLeft() {
        return canSlideLeft;
    }

    public boolean isFailed() {
        return isFailed;
    }

    public DataProcess() {
        init();
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

    public void ifCanSlideUp(){
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

    public void ifCanSlideDown(){
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

    public void ifCanSlideLeft(){
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

    public void ifCanSlideRight(){
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

    public void ifFailed() {
        if (!canSlideDown && !canSlideUp && !canSlideRight && !canSlideLeft) {
            isFailed = true;
        } else {
            isFailed = false;
        }
    }


    public void addRandomNumber(int[][] intDoubleArray) {
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
