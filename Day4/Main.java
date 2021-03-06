package com.company;

import java.io.File;
import java.io.IOException;
import java.util.*;

class RealRoom {

    private StringBuilder decryptedName;
    private int sectorId;
    private char asciiSymbol;
    private int asciiNumeric;

    StringBuilder getDecryptedName() {
        return decryptedName;
    }

    int getSectorId() {
        return sectorId;
    }

    RealRoom(StringBuilder decryptedName, int sectorId) {
        this.decryptedName = decryptedName;
        this.sectorId = sectorId;
    }

    void decipher() {
        for (int i = 0; i < decryptedName.length(); i++) {
            if (decryptedName.charAt(i) != '-') {
                asciiSymbol = decryptedName.charAt(i);
                asciiNumeric = asciiSymbol;
                asciiNumeric = asciiNumeric + sectorId % 26;
                if (asciiNumeric > 122) asciiNumeric -= 26;
                asciiSymbol = (char) asciiNumeric;
                decryptedName.setCharAt(i, asciiSymbol);
            }
        }
    }
}

class EncryptedName {
    private static int sectorSum = 0;
    private static int realRoomsAmmount = 0;
    private final static String alphabet = "abcdefghijklmnopqrstuvwxyz";

    static int getSectorSum() {
        return sectorSum;
    }

    static int getRealRoomsAmmount() {
        return realRoomsAmmount;
    }

    private int[] values = new int[alphabet.length()];
    private String encryptedName;
    private Map<Character, Integer> mapChar = new HashMap<Character, Integer>();
    private int sectorId = 0;
    private String checkSum;
    private StringBuilder mapCheckSum = new StringBuilder(alphabet);
    private StringBuilder justEncryptedName = new StringBuilder();

    StringBuilder getJustEncryptedName() {
        return justEncryptedName;
    }

    int getSectorId() {
        return sectorId;
    }

    EncryptedName(String encryptedName) {
        this.encryptedName = encryptedName;
    }

    void processingName() {
        for (int i = 0; i < encryptedName.length(); i++) {

            if (Character.isLetter(encryptedName.charAt(i)) && sectorId == 0) {
                if (mapChar.get(encryptedName.charAt(i)) != null) {
                    mapChar.put(encryptedName.charAt(i), mapChar.get(encryptedName.charAt(i)) + 1);
                } else
                    mapChar.put(encryptedName.charAt(i), 1);
                justEncryptedName.append(encryptedName.charAt(i));

            } else if (Character.isDigit(encryptedName.charAt(i))) {
                sectorId *= 10;
                sectorId += Integer.parseInt(encryptedName.substring(i, i + 1));
            } else if (encryptedName.charAt(i) == '-')
                justEncryptedName.append('-');

        }
        justEncryptedName.deleteCharAt(justEncryptedName.length() - 1);
        System.out.println(justEncryptedName.toString());
        checkSum = encryptedName.substring(encryptedName.length() - 6, encryptedName.length() - 1);
    }

    boolean checkIfRoom() {
        int temp = 0;
        char temp2 = ' ';

        for (int i = 0; i < alphabet.length(); i++) {
            for (int j = 0; j < encryptedName.length() - 10; j++) {
                if (alphabet.charAt(i) == encryptedName.charAt(j)) {
                    values[i]++;
                }
            }
        }
        for (int i = 0; i < alphabet.length(); i++) {
            for (int j = 1; j < (alphabet.length() - i); j++) {
                if (values[j - 1] < values[j]) {
                    temp = values[j - 1];
                    temp2 = mapCheckSum.charAt(j - 1);
                    values[j - 1] = values[j];
                    mapCheckSum.setCharAt(j - 1, mapCheckSum.charAt(j));
                    values[j] = temp;
                    mapCheckSum.setCharAt(j, temp2);
                }

            }
        }
        if (checkSum.equals(mapCheckSum.substring(0, 5).toString())) {
            sectorSum += sectorId;
            realRoomsAmmount++;
            return true;

        }
        return false;
    }
}

public class Main {

    public static void main(String[] args) {

        String[] line = null;
        File file = new File("input.txt");

        try {
            ArrayList<String> alPath = new ArrayList<>();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                alPath.add(scanner.next());
            }
            line = alPath.toArray(new String[alPath.size()]);
        } catch (IOException e) {
            System.out.println("Cannot read file: " + file.getPath());
        }

        ArrayList<EncryptedName> encryptedNameArrayList = new ArrayList<EncryptedName>();
        ArrayList<RealRoom> realRoomArrayList = new ArrayList<RealRoom>();
        for (int i = 0; i < line.length; i++) {
            encryptedNameArrayList.add(new EncryptedName(line[i]));
            encryptedNameArrayList.get(i).processingName();
            if (encryptedNameArrayList.get(i).checkIfRoom()) {
                realRoomArrayList.add(new RealRoom(encryptedNameArrayList.get(i).getJustEncryptedName(), encryptedNameArrayList.get(i).getSectorId()));
            }

        }
        for (int i = 0; i < realRoomArrayList.size(); i++) {
            realRoomArrayList.get(i).decipher();
            System.out.println(realRoomArrayList.get(i).getDecryptedName() + " " + realRoomArrayList.get(i).getSectorId());
        }
        System.out.println(EncryptedName.getSectorSum() + " " + EncryptedName.getRealRoomsAmmount());

    }
}
