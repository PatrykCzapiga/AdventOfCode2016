package com.company;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Triangle {

    int a = 0;
    int b = 0;
    int c = 0;


    public void setTriangle(String a, String b, String c) {
        this.a = Integer.parseInt(a);
        this.b = Integer.parseInt(b);
        this.c = Integer.parseInt(c);
    }

    boolean checkTriangle() {
        if (a + b > c) {
            if (a + c > b) {
                if (b + c > a) {
                    return true;
                }
            }
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
            System.out.println("Error");
        }

        int amountOfTriangles = 0;
        Triangle triangle = new Triangle();
        for (int i = 0; i < line.length; i=i+9) {
            for (int j = 0; j < 3; j++) {
                triangle.setTriangle(line[i+j], line[i+j + 3], line[i+j + 6]);
                if (triangle.checkTriangle()) {
                    amountOfTriangles++;
                }
            }
        }
        System.out.println(amountOfTriangles);


    }
}
