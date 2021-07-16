package com.company;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class KeyPad {

    private int y;
    private int x;

    public KeyPad(int y, int x){
    this.y=y;
    this.x=x;
    }
    private char[][] keyPad = {{' ', ' ', '1', ' ', ' '}, {' ', '2', '3', '4', ' '}, {'5', '6', '7', '8', '9'}, {' ', 'A', 'B', 'C', ' '}, {' ', ' ', 'D', ' ', ' '}};

    public char returnKeyPad() {
        return (keyPad[y][x]);
    }

    public void move(String moveSequence) {

        String direction;
        for (int i = 0; i < moveSequence.length(); i++) {
            if (moveSequence.charAt(i) == 'D' && y < 4 && keyPad[y+1][x] != ' ') {
                y++;
            }
            if (moveSequence.charAt(i) == 'U' && y > 0 && keyPad[y-1][x] != ' ') {
                y--;
            }
            if (moveSequence.charAt(i) == 'R' && x < 4 && keyPad[y][x+1] != ' ') {
                x++;
            }
            if (moveSequence.charAt(i) == 'L' && x > 0 && keyPad[y][x-1] != ' ') {
                x--;

            }
        }


    }
}

public class Main {

    public static void main(String[] args) {

        String[] step = null;
        File file = new File("input.txt");

        try {
            ArrayList<String> alPath = new ArrayList<>();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                alPath.add(scanner.next());
            }
            step = alPath.toArray(new String[alPath.size()]);
        } catch (IOException e) {
            System.out.println("Error");
        }

        KeyPad pinPad = new KeyPad(2,2);


        for (int i = 0; i < step.length; i++) {
            pinPad.move(step[i]);
            System.out.println(pinPad.returnKeyPad());
        }
    }
}
