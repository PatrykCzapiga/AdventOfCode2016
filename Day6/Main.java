package com.company;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Message {
    private ArrayList<String> corruptedMessage = new ArrayList<String>();
    private StringBuffer fixedMessage = new StringBuffer();

    void setCorruptedMessage(ArrayList corruptedMessage) {
        this.corruptedMessage = corruptedMessage;
    }

    private Map<Character, Integer>[] charMap = new HashMap[8];

    void fixMessage() {
        for (int i = 0; i < corruptedMessage.get(0).length(); i++) {
            charMap[i] = new HashMap<Character, Integer>();
        }
        for (int i = 0; i < corruptedMessage.size(); i++) {
            for (int j = 0; j < corruptedMessage.get(0).length(); j++) {
                if (!(charMap[j].containsKey(corruptedMessage.get(i).charAt(j)))) {
                    charMap[j].put(corruptedMessage.get(i).charAt(j), 1);
                } else {
                    charMap[j].put(corruptedMessage.get(i).charAt(j), charMap[j].get(corruptedMessage.get(i).charAt(j)) + 1);
                }
            }
        }


        for (int i = 0; i < 8; i++) {
            char mostUsedLetter = ' ';
            int temp = 0;
            for (int j = 97; j < charMap[i].size() + 97; j++) {
                if (temp < charMap[i].get((char) j)) {
                    mostUsedLetter = (char) j;
                    temp = charMap[i].get((char) j);
                }
            }
            fixedMessage.append(mostUsedLetter);
        }
        System.out.println("Part1: " + fixedMessage.toString());
        fixedMessage.setLength(0);

        for (int i = 0; i < 8; i++) {
            char mostUsedLetter = ' ';
            int temp = 25;
            for (int j = 97; j < charMap[i].size() + 97; j++) {
                if (temp > charMap[i].get((char) j)) {
                    mostUsedLetter = (char) j;
                    temp = charMap[i].get((char) j);
                }
            }

            fixedMessage.append(mostUsedLetter);

        }



        System.out.println("Part2: " + fixedMessage.toString());
    }
}

public class Main {

    public static void main(String[] args) {
        Message part1 = new Message();
        File file = new File("input.txt");

        try {
            ArrayList<String> alPath = new ArrayList<>();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                alPath.add(scanner.next());
            }
            part1.setCorruptedMessage(alPath);
        } catch (IOException e) {
            System.out.println("Error");
        }

        part1.fixMessage();

    }
}
