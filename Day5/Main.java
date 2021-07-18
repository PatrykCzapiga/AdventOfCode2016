package com.company;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class ConvertHex{
        static final char[] hexSymbols = "0123456789ABCDEF".toCharArray();

        public static String convertHex(byte[] data) {

                char[] symbols = new char[data.length*2];

                for (int i = 0; i < data.length; i++) {
                        symbols[i * 2]= hexSymbols[(data[i] >> 4) & 0xf];
                        symbols[i * 2 + 1]= hexSymbols[data[i] & 0xf];
                }
                return new String(symbols);
        }

}

public class Main {

        public static String md5(String input) {
                String md5 = null;
                if (null == input) return null;
                try {
                        //Create MessageDigest object for MD5
                        MessageDigest digest = MessageDigest.getInstance("MD5");
                        //Update input string in message digest
                        digest.update(input.getBytes(), 0, input.length());
                        //Converts message digest value in base 16 (hex)
                        ConvertHex hex1 = new ConvertHex();
                        md5 = hex1.convertHex(digest.digest());
                } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                }
                return md5;

        }

        public static void main(String args[]){

                String inputPuzzle = "ugkcyxxp";
                int index = 0;
                md5(inputPuzzle);
                StringBuffer part1 = new StringBuffer();
                char[] charsPart2 = new char[8];
                int position=0;
                int repetitions=0;
                while(part1.length()<8) {
                        if (md5(inputPuzzle + index).substring(0, 5).equals("00000")) {
                                part1.append(md5(inputPuzzle + index).charAt(5));
                        }
                        index++;
                }
                index = 0;
                while(repetitions<8) {

                        if (md5(inputPuzzle + index).substring(0, 5).equals("00000") && Character.isDigit(md5(inputPuzzle + index).charAt(5)) && Character.getNumericValue(md5(inputPuzzle + index).charAt(5))<8){
                                position=Character.getNumericValue(md5(inputPuzzle + index).charAt(5));
                                if(charsPart2[position]=='\u0000'){
                                        charsPart2[position]=md5(inputPuzzle + index).charAt(6);
                                        repetitions++;
                                }

                        }

                        index++;
                }
                String part2 = new String (charsPart2);
                System.out.println("Part1: " + part1.toString());
                System.out.println("Part2: " + part2);
                }
}
