package com.company;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class KeyPad {

    private int y;
    private int x;
    private int part;
    private char[][] keyPad;

    public KeyPad(int part) {
        this.part = part;
        if (part == 1) {
            y = 1;
            x = 1;
            keyPad = new char[][]{{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};
        } else if (part == 2) {
            y = 2;
            x = 2;
            keyPad = new char[][]{{' ', ' ', '1', ' ', ' '}, {' ', '2', '3', '4', ' '}, {'5', '6', '7', '8', '9'}, {' ', 'A', 'B', 'C', ' '}, {' ', ' ', 'D', ' ', ' '}};
        }
    }


    public char returnKeyPad() {
        return (keyPad[y][x]);
    }

    public void move(String moveSequence) {

        String direction;
        for (int i = 0; i < moveSequence.length(); i++) {
            if (moveSequence.charAt(i) == 'D' && y < (part * 2) && keyPad[y + 1][x] != ' ') {
                y++;
            }
            if (moveSequence.charAt(i) == 'U' && y > 0 && keyPad[y - 1][x] != ' ') {
                y--;
            }
            if (moveSequence.charAt(i) == 'R' && x < (part * 2) && keyPad[y][x + 1] != ' ') {
                x++;
            }
            if (moveSequence.charAt(i) == 'L' && x > 0 && keyPad[y][x - 1] != ' ') {
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

        KeyPad pinPad1 = new KeyPad(1);

        System.out.print("Part 1 PIN: ");
        for (int i = 0; i < step.length; i++) {
            pinPad1.move(step[i]);
            System.out.print(pinPad1.returnKeyPad());
        }
        System.out.println(" ");

        KeyPad pinPad2 = new KeyPad(2);

        System.out.print("Part 2 PIN: ");
        for (int i = 0; i < step.length; i++) {
            pinPad2.move(step[i]);
            System.out.print(pinPad2.returnKeyPad());
        }
    }
}
