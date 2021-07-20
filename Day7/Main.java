package com.company;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class AddressIP {
    ArrayList<String> arrayAddressIP = new ArrayList<>();
    ArrayList<String> arraySupportedTLS = new ArrayList<>();
    ArrayList<String> arraySupportedSLS = new ArrayList<>();

    public void setArrayAddressIP(ArrayList<String> arrayAddressIP) {
        this.arrayAddressIP = arrayAddressIP;
    }

    public void checkTLS() {
        for (int i = 0; i < arrayAddressIP.size(); i++) {
            boolean passedFirstBracket = false;
            boolean passedSecondBracket = false;
            boolean correctTLS = false;
            boolean tlsInBrackets = false;
            for (int j = 0; j < arrayAddressIP.get(i).length() - 3; j++) {
                if (arrayAddressIP.get(i).charAt(j) == '[') {
                    passedFirstBracket = true;
                }
                if (arrayAddressIP.get(i).charAt(j) == ']') {
                    passedSecondBracket = true;
                }
                if (passedFirstBracket == false && arrayAddressIP.get(i).charAt(j) == arrayAddressIP.get(i).charAt(j + 3) && arrayAddressIP.get(i).charAt(j + 1) == arrayAddressIP.get(i).charAt(j + 2) && arrayAddressIP.get(i).charAt(j) != arrayAddressIP.get(i).charAt(j + 1)) {
                    correctTLS = true;
                }
                if (passedFirstBracket == true && passedSecondBracket == false && arrayAddressIP.get(i).charAt(j) == arrayAddressIP.get(i).charAt(j + 3) && arrayAddressIP.get(i).charAt(j + 1) == arrayAddressIP.get(i).charAt(j + 2) && arrayAddressIP.get(i).charAt(j) != arrayAddressIP.get(i).charAt(j + 1)) {
                    tlsInBrackets = true;
                }
                if (passedSecondBracket == true) {
                    passedFirstBracket = false;
                    passedSecondBracket = false;
                }
            }
            if (correctTLS == true && tlsInBrackets == false) {
                arraySupportedTLS.add(arrayAddressIP.get(i));
            }
        }
    }

    public void checkSLS() {
        for (int i = 0; i < arrayAddressIP.size(); i++) {
            StringBuffer sls1 = new StringBuffer();
            StringBuffer sls2 = new StringBuffer();
           boolean passedFirstBracket = false;
           boolean passedSecondBracket = false;
           boolean slsFounded = false;
            for (int j = 0; j < arrayAddressIP.get(i).length() - 2; j++) {
                if (arrayAddressIP.get(i).charAt(j) == '[') {
                    passedFirstBracket = true;
                }
                if (arrayAddressIP.get(i).charAt(j) == ']') {
                    passedSecondBracket = true;
                }
                if (passedFirstBracket == true && passedSecondBracket == true) {
                    passedFirstBracket = false;
                    passedSecondBracket = false;
                }
                if (passedFirstBracket == false && arrayAddressIP.get(i).charAt(j) == arrayAddressIP.get(i).charAt(j + 2) && arrayAddressIP.get(i).charAt(j) != arrayAddressIP.get(i).charAt(j + 1)) {
                    sls1.append(arrayAddressIP.get(i).charAt(j));
                    sls1.append(arrayAddressIP.get(i).charAt(j + 1));
                    sls1.append(arrayAddressIP.get(i).charAt(j + 2));
                }
                if (passedFirstBracket == true && arrayAddressIP.get(i).charAt(j) == arrayAddressIP.get(i).charAt(j + 2) && arrayAddressIP.get(i).charAt(j) != arrayAddressIP.get(i).charAt(j + 1)) {
                    sls2.append(arrayAddressIP.get(i).charAt(j));
                    sls2.append(arrayAddressIP.get(i).charAt(j + 1));
                    sls2.append(arrayAddressIP.get(i).charAt(j + 2));
                }
            }
            if (sls1.length() > 0 && sls2.length() > 0) {
                for (int j = 0; j < sls1.length() - 2; j += 3) {
                    for (int k = 0; k < sls2.length(); k += 3) {
                        if (slsFounded == false && sls1.charAt(j) == sls2.charAt(k + 1) && sls1.charAt(j + 1) == sls2.charAt(k)) {
                            arraySupportedSLS.add(arrayAddressIP.get(i));
                            slsFounded = true;
                        }
                    }
                }
            }
        }
    }

    public void print() {
        System.out.println(arraySupportedTLS.size());
        System.out.println(arraySupportedSLS.size());
    }
}

public class Main {

    public static void main(String[] args) {


        AddressIP addressIP = new AddressIP();
        try {
            File file = new File("input.txt");

            ArrayList<String> list = new ArrayList<>();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                list.add(scanner.next());
            }
            addressIP.setArrayAddressIP(list);
        } catch (IOException e) {
            System.out.println("Error");
        }
        addressIP.checkTLS();
        addressIP.checkSLS();
        addressIP.print();
    }
}