package com.company;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Screen {
    ArrayList<String> commands = new ArrayList<>();
    char[][] charScreen = new char[50][6];

    void setCommands(ArrayList<String> commands) {
        this.commands = commands;
    }

    void resetScreen() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 50; j++) {
                charScreen[j][i] = '.';
            }
        }
    }

    void rect(int indexOfCommand) {
        int x;
        int y;
        StringBuilder xsb = new StringBuilder();
        StringBuilder ysb = new StringBuilder();
        boolean passedX = false;
        for (int i = 0; i < commands.get(indexOfCommand).length(); i++) {
            if (passedX == false && Character.isDigit(commands.get(indexOfCommand).charAt(i))) {
                xsb.append(commands.get(indexOfCommand).charAt(i));
            }
            if (commands.get(indexOfCommand).charAt(i) == 'x') {
                passedX = true;
            }
            if (passedX == true && Character.isDigit(commands.get(indexOfCommand).charAt(i))) {
                ysb.append(commands.get(indexOfCommand).charAt(i));
            }
        }
        x = Integer.parseInt(xsb.toString());
        y = Integer.parseInt(ysb.toString());
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                charScreen[j][i] = '#';
            }
        }
    }

    void rotateRow(int indexOfCommand) {
        int x;
        int y;
        StringBuilder xsb = new StringBuilder();
        StringBuilder ysb = new StringBuilder();
        boolean passedBy = false;
        for (int i = 0; i < commands.get(indexOfCommand).length(); i++) {
            if (passedBy == false && Character.isDigit(commands.get(indexOfCommand).charAt(i))) {
                ysb.append(commands.get(indexOfCommand).charAt(i));
            }
            if (commands.get(indexOfCommand).charAt(i) == 'b') {
                passedBy = true;
            }
            if (passedBy == true && Character.isDigit(commands.get(indexOfCommand).charAt(i))) {
                xsb.append(commands.get(indexOfCommand).charAt(i));
            }
        }

        x = Integer.parseInt(xsb.toString());
        y = Integer.parseInt(ysb.toString());
        char temp;
        for (int i = x; i > 0; i--) {
            temp = charScreen[49][y];
            for (int j = 49; j > 0; j--) {
                charScreen[(j)][y] = charScreen[j - 1][y];
            }
            charScreen[0][y] = temp;
        }
    }

    void rotateColumn(int indexOfCommand) {
        int x;
        int y;
        StringBuilder xsb = new StringBuilder();
        StringBuilder ysb = new StringBuilder();
        boolean passedBy = false;
        for (int i = 0; i < commands.get(indexOfCommand).length(); i++) {
            if (passedBy == false && Character.isDigit(commands.get(indexOfCommand).charAt(i))) {
                xsb.append(commands.get(indexOfCommand).charAt(i));
            }
            if (commands.get(indexOfCommand).charAt(i) == 'b') {
                passedBy = true;
            }
            if (passedBy == true && Character.isDigit(commands.get(indexOfCommand).charAt(i))) {
                ysb.append(commands.get(indexOfCommand).charAt(i));
            }
        }

        x = Integer.parseInt(xsb.toString());
        y = Integer.parseInt(ysb.toString());
        char temp;
        for (int i = y; i > 0; i--) {
            temp = charScreen[x][5];
            for (int j = 5; j > 0; j--) {
                charScreen[(x)][j] = charScreen[x][j - 1];
            }
            charScreen[x][0] = temp;
        }
    }

    void runCommands() {
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i).substring(0, 4).equals("rect")) {
                this.rect(i);
            } else if (commands.get(i).substring(0, 10).equals("rotate row")) {
                this.rotateRow(i);
            } else if (commands.get(i).substring(0, 13).equals("rotate column")) {
                this.rotateColumn(i);
            }
        }
    }

    int countPixels() {
        int counter = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 50; j++) {
                if (charScreen[j][i] == '#') {
                    counter++;
                }
            }
        }
        return counter;
    }

    void showScreen() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 50; j++) {
                if(j%5==0) {
                    System.out.print(" ");
                }
                System.out.print(charScreen[j][i]);
            }
            System.out.println();
        }
    }
}

public class Main {

    public static void main(String[] args) {
        Screen screen = new Screen();

        try {
            File file = new File("input.txt");

            ArrayList<String> list = new ArrayList<>();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                list.add(scanner.nextLine());
            }
            screen.setCommands(list);
        } catch (IOException e) {
            System.out.println("Error");
        }
        screen.resetScreen();
        screen.runCommands();
        System.out.println("Active Pixels " + screen.countPixels());
        screen.showScreen();
    }
}
