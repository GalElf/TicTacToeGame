package com.example.tictactoegame;
import android.widget.Button;

import java.io.Serializable;

public class TicTacToeGame implements Serializable {
    final private int SIZE = 3;
    Button[][] ticTacToe;
    int player;
    int computer;

    public TicTacToeGame(Button Square00, Button Square01, Button Square02, Button Square10, Button Square11, Button Square12, Button Square20, Button Square21, Button Square22) {
        ticTacToe = new Button[SIZE][SIZE];
        player = 0;
        computer = 0;
        ticTacToe[0][0] = Square00;
        ticTacToe[0][1] = Square01;
        ticTacToe[0][2] = Square02;
        ticTacToe[1][0] = Square10;
        ticTacToe[1][1] = Square11;
        ticTacToe[1][2] = Square12;
        ticTacToe[2][0] = Square20;
        ticTacToe[2][1] = Square21;
        ticTacToe[2][2] = Square22;
        for (int i = 0; i < 9; i++) {
            ticTacToe[i / SIZE][i % SIZE].setText("");
        }
    }

    public void allMove(Button Square) {
        String str = checkWin(ticTacToe);
        if (str.equals("")) {
            playerMove(Square);
            str = checkWin(ticTacToe);
            if (str.equals("")) {
                if (player == computer + 1) {
                    computerMove();
                }
            }
        }
    }

    public void playerMove(Button Square) {
        if (Square.getText().toString().equals("")) {
            Square.setText("X");
            player++;
        }
    }

    public String checkWin(Button[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[0][i].getText().toString().equals(arr[1][i].getText().toString())) {
                if (arr[1][i].getText().toString().equals(arr[2][i].getText().toString())) {
                    if (!arr[0][i].getText().toString().equals("")) {
                        return arr[0][i].getText().toString();
                    }
                }
            }
            if (arr[i][0].getText().toString().equals(arr[i][1].getText().toString())) {
                if (arr[i][1].getText().toString().equals(arr[i][2].getText().toString())) {
                    if (!arr[i][0].getText().toString().equals("")) {
                        return arr[i][0].getText().toString();
                    }
                }
            }
        }
        if (arr[0][0].getText().toString().equals(arr[1][1].getText().toString())) {
            if (arr[1][1].getText().toString().equals(arr[2][2].getText().toString())) {
                if (!arr[0][0].getText().toString().equals("")) {
                    return arr[0][0].getText().toString();
                }
            }
        }
        if (arr[2][0].getText().toString().equals(arr[1][1].getText().toString())) {
            if (arr[1][1].getText().toString().equals(arr[0][2].getText().toString())) {
                if (!arr[2][0].getText().toString().equals("")) {
                    return arr[2][0].getText().toString();
                }
            }
        }
        int count = 0;
        for (int i = 0; i < SIZE*SIZE; i++) {
            if (!arr[i / SIZE][i % SIZE].getText().toString().equals("")) {
                count++;
            }
        }
        if (count == SIZE*SIZE) {
            return "Draw";
        }
        return "";
    }

    public void createNewGame() {
        for (int i = 0; i < SIZE*SIZE; i++) {
            ticTacToe[i / SIZE][i % SIZE].setText("");
        }
        player = 0;
        computer = 0;
        disable(true);
    }

    public void disable(boolean status) {
        for (int i = 0; i < SIZE*SIZE; i++) {
            ticTacToe[i / SIZE][i % SIZE].setEnabled(status);
        }
    }


    public void computerMove() {
        if (player < 5) {
            boolean found = false;
            int stage = 0;
            if (ticTacToe[1][1].getText().toString().equals("")) {
                ticTacToe[1][1].setText("O");
                computer++;
                return;
            }
            found = computerMove2and3("O");
            if(found == true){
                computer++;
                return;
            }
            found = computerMove2and3("X");
            if(found == true){
                computer++;
                return;
            }
            found = computerMove4();
            if(found == true){
                computer++;
                return;
            }
            found = computerMove5();
            if(found == true){
                computer++;
                return;
            }
            while (found == false) {
                int i = (int) (Math.random() * SIZE);
                int j = (int) (Math.random() * SIZE);
                if (ticTacToe[i][j].getText().toString().equals("") && found == false) {
                    ticTacToe[i][j].setText("O");
                    computer++;
                    return;
                }
            }
        }
    }

    private boolean computerMove2and3(String move) {
        for (int i = 0; i < SIZE; i++) {
            if (ticTacToe[i][0].getText().toString().equals(move) && ticTacToe[i][0].getText().toString().equals(ticTacToe[i][1].getText().toString())) {
                if (ticTacToe[i][2].getText().toString().equals("")) {
                    ticTacToe[i][2].setText("O");
                    return true;
                }
            }
            if (ticTacToe[i][0].getText().toString().equals(move) && ticTacToe[i][0].getText().toString().equals(ticTacToe[i][2].getText().toString())) {
                if (ticTacToe[i][1].getText().toString().equals("")) {
                    ticTacToe[i][1].setText("O");
                    return true;
                }
            }
            if (ticTacToe[i][1].getText().toString().equals(move) && ticTacToe[i][1].getText().toString().equals(ticTacToe[i][2].getText().toString())) {
                if (ticTacToe[i][0].getText().toString().equals("")) {
                    ticTacToe[i][0].setText("O");
                    return true;
                }
            }

            if (ticTacToe[0][i].getText().toString().equals(move) && ticTacToe[0][i].getText().toString().equals(ticTacToe[1][i].getText().toString())) {
                if (ticTacToe[2][i].getText().toString().equals("")) {
                    ticTacToe[2][i].setText("O");
                    return true;
                }
            }
            if (ticTacToe[0][i].getText().toString().equals(move) && ticTacToe[0][i].getText().toString().equals(ticTacToe[2][i].getText().toString())) {
                if (ticTacToe[1][i].getText().toString().equals("")) {
                    ticTacToe[1][i].setText("O");
                    return true;
                }
            }
            if (ticTacToe[1][i].getText().toString().equals(move) && ticTacToe[1][i].getText().toString().equals(ticTacToe[2][i].getText().toString())) {
                if (ticTacToe[0][i].getText().toString().equals("")) {
                    ticTacToe[0][i].setText("O");
                    return true;
                }
            }
        }
        if (ticTacToe[0][0].getText().toString().equals(move) && ticTacToe[0][0].getText().toString().equals(ticTacToe[1][1].getText().toString())) {
            if (ticTacToe[2][2].getText().toString().equals("")) {
                ticTacToe[2][2].setText("O");
                return true;
            }
        }
        if (ticTacToe[0][0].getText().toString().equals(move) && ticTacToe[0][0].getText().toString().equals(ticTacToe[2][2].getText().toString())) {
            if (ticTacToe[1][1].getText().toString().equals("")) {
                ticTacToe[1][1].setText("O");
                return true;
            }
        }
        if (ticTacToe[1][1].getText().toString().equals(move) && ticTacToe[1][1].getText().toString().equals(ticTacToe[2][2].getText().toString())) {
            if (ticTacToe[0][0].getText().toString().equals("")) {
                ticTacToe[0][0].setText("O");
                return true;
            }
        }

        if (ticTacToe[0][2].getText().toString().equals(move) && ticTacToe[0][2].getText().toString().equals(ticTacToe[1][1].getText().toString())) {
            if (ticTacToe[2][0].getText().toString().equals("")) {
                ticTacToe[2][0].setText("O");
                return true;
            }
        }
        if (ticTacToe[1][1].getText().toString().equals(move) && ticTacToe[1][1].getText().toString().equals(ticTacToe[2][0].getText().toString())) {
            if (ticTacToe[0][2].getText().toString().equals("")) {
                ticTacToe[0][2].setText("O");
                return true;
            }
        }
        if (ticTacToe[0][2].getText().toString().equals(move) && ticTacToe[0][2].getText().toString().equals(ticTacToe[2][0].getText().toString())) {
            if (ticTacToe[1][1].getText().toString().equals("")) {
                ticTacToe[1][1].setText("O");
                return true;
            }
        }
        return false;
    }

    private boolean computerMove4() {
        if(this.computer+this.player != 3 && ticTacToe[1][1].getText().toString().equals("X")){
            return false;
        }
        int arr[] = computerCheckCorner();
        while(true) {
            int x = (int) (Math.random() * arr.length);
            if(arr[x] == 0){
                if(x == 0){
                    if (ticTacToe[0][0].getText().toString().equals("")) {
                        ticTacToe[0][0].setText("O");
                        break;
                    }
                }
                if(x == 1){
                    if (ticTacToe[0][2].getText().toString().equals("")) {
                        ticTacToe[0][2].setText("O");
                        break;
                    }
                }
                if(x == 2){
                    if (ticTacToe[2][0].getText().toString().equals("")) {
                        ticTacToe[2][0].setText("O");
                        break;
                    }
                }
                if(x == 3){
                    if (ticTacToe[2][2].getText().toString().equals("")) {
                        ticTacToe[2][2].setText("O");
                        break;
                    }
                }
            }
        }
        return true;
    }

    private boolean computerMove5() {
        int arr[] = computerCheckCorner();
        boolean check = false;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == 1){
                check = true;
                break;
            }
        }
        if(check == false){
            return false;
        }
        while(true) {
            int x = (int) (Math.random() * arr.length);
            if(arr[x] == 1){
                if(x == 0){
                    ticTacToe[0][0].setText("O");
                    break;
                }
                if(x == 1){
                    ticTacToe[0][2].setText("O");
                    break;
                }
                if(x == 2){
                    ticTacToe[2][0].setText("O");
                    break;
                }
                if(x == 3){
                    ticTacToe[2][2].setText("O");
                    break;
                }
            }
        }
        return true;
    }

    private int[] computerCheckCorner(){
        int arr[] = new int[4];
        if (ticTacToe[0][0].getText().toString().equals("") &&
                ticTacToe[0][0].getText().toString().equals(ticTacToe[1][0].getText().toString()) &&
                ticTacToe[1][0].getText().toString().equals(ticTacToe[2][0].getText().toString()) &&
                ticTacToe[2][0].getText().toString().equals(ticTacToe[0][2].getText().toString()) &&
                ticTacToe[0][2].getText().toString().equals(ticTacToe[0][1].getText().toString())) {
            arr[0] = 1;
        }
        if (ticTacToe[0][2].getText().toString().equals("") &&
                ticTacToe[0][2].getText().toString().equals(ticTacToe[0][1].getText().toString()) &&
                ticTacToe[0][1].getText().toString().equals(ticTacToe[0][0].getText().toString()) &&
                ticTacToe[0][0].getText().toString().equals(ticTacToe[1][2].getText().toString()) &&
                ticTacToe[1][2].getText().toString().equals(ticTacToe[2][2].getText().toString())) {
            arr[1] = 1;
        }
        if (ticTacToe[2][0].getText().toString().equals("") &&
                ticTacToe[2][0].getText().toString().equals(ticTacToe[1][0].getText().toString()) &&
                ticTacToe[1][0].getText().toString().equals(ticTacToe[0][0].getText().toString()) &&
                ticTacToe[0][0].getText().toString().equals(ticTacToe[2][1].getText().toString()) &&
                ticTacToe[2][1].getText().toString().equals(ticTacToe[2][2].getText().toString())) {
            arr[2] = 1;
        }
        if (ticTacToe[2][2].getText().toString().equals("") &&
                ticTacToe[2][2].getText().toString().equals(ticTacToe[1][2].getText().toString()) &&
                ticTacToe[1][2].getText().toString().equals(ticTacToe[0][2].getText().toString()) &&
                ticTacToe[0][2].getText().toString().equals(ticTacToe[2][1].getText().toString()) &&
                ticTacToe[1][2].getText().toString().equals(ticTacToe[2][0].getText().toString())) {
            arr[3] = 1;
        }
        return arr;
    }
}

