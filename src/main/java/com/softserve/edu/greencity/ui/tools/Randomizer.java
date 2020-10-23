package com.softserve.edu.greencity.ui.tools;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

public class Randomizer {

    private static int LENGTH;
    private static boolean USE_LETTERS;
    private static boolean USE_NUMBERS;

    /**
     * Returns a string that can have letters (A-Z, a-z) and numbers (0-9) and a
     * given length.
     * @param length int
     * @param useLetters boolean
     * @param useNumbers boolean
     * @return String
     */
    public static String getRamdomString1(int length, boolean useLetters,
            boolean useNumbers) {
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

    /**
     * Returns a string that has letters (A-Z, a-z) and a given length.
     * @param length int
     * @return String
     */
    public static String getRamdomStringLetters1(int length) {
        USE_LETTERS = true;
        USE_NUMBERS = false;
        return RandomStringUtils.random(length, USE_LETTERS, USE_NUMBERS);
    }

    /**
     * Returns a string that has numbers (0-9) and a given length.
     * @param length
     * @return
     */
    public static String getRamdomStringNumbers1(int length) {
        USE_LETTERS = false;
        USE_NUMBERS = true;
        return RandomStringUtils.random(length, USE_LETTERS, USE_NUMBERS);
    }

    /**
     * Returns a string that has letters (A-Z, a-z) 20 symbols length.
     * @return String
     */
    public static String getRamdomString20Letters() {
        LENGTH = 20;
        USE_LETTERS = true;
        USE_NUMBERS = false;
        return RandomStringUtils.random(LENGTH, USE_LETTERS, USE_NUMBERS);
    }

    /**
     * Returns a string that has letters (A-Z, a-z) and numbers (0-9) 20 symbols
     * length.
     * @return String
     */
    public static String getRamdomString20LettersNumbers() {
        LENGTH = 20;
        USE_LETTERS = true;
        USE_NUMBERS = true;
        return RandomStringUtils.random(LENGTH, USE_LETTERS, USE_NUMBERS);
    }

    /**
     * Returns a string that has numbers (0-9) 20 symbols length.
     * @return String
     */
    public static String getRamdomString20Numbers() {
        LENGTH = 20;
        USE_LETTERS = false;
        USE_NUMBERS = true;
        return RandomStringUtils.random(LENGTH, USE_LETTERS, USE_NUMBERS);
    }

    /**
     * Returns a one ASCII symbol (!"#$%^&()*+,-./:;<=>?@[\]^_'{|}~).
     * @return String
     */
    public static String getRamdomSpecSymbol() {
        int leftLimit = 33; // ASCII symbol '!'
        int rightLimit = 126; // ASCII symbol '~'
        int targetStringLength = 1;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 47 || i >= 58) && (i <= 64 || i >= 91)
                        && (i <= 96 || i >= 122))
                .limit(targetStringLength).collect(StringBuilder::new,
                        StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    /**
     * Returns a string that has ASCII symbols
     * (!"#$%^&()*+,-./:;<=>?@[\]^_'{|}~) a given length..
     * @param targetStringLength int
     * @return String
     */
    public static String getRamdomSpecSymbols(int targetStringLength) {
        int leftLimit = 33; // ASCII symbol '!'
        int rightLimit = 126; // ASCII symbol '~'
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 47 || i >= 58) && (i <= 64 || i >= 91)
                        && (i <= 96 || i >= 122))
                .limit(targetStringLength).collect(StringBuilder::new,
                        StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    /**
     * Returns a string that has ASCII symbols (A-Z, a-z) and a given length.
     * @param targetStringLength int
     * @return String
     */
    public static String getRamdomStringLetters2(int targetStringLength) {
        int leftLimit = 65; // ASCII symbol 'A'
        int rightLimit = 122; // ASCII symbol 'z'
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 90 || i >= 97)).limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
        return generatedString;
    }

    /**
     * Returns a string that has ASCII symbols (A-Z) and a given length.
     * @param targetStringLength int
     * @return String
     */
    public static String getRamdomStringUppercaseLetters(
            int targetStringLength) {
        int leftLimit = 65; // ASCII symbol 'A'
        int rightLimit = 90; // ASCII symbol 'Z'
        Random random = new Random();

        String generatedString = random
                .ints(leftLimit, rightLimit + 1).collect(StringBuilder::new,
                        StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    /**
     * Returns a one ASCII symbol (A-Z).
     * @return String
     */
    public static String getRamdomUppercaseLetter() {
        int leftLimit = 65; // ASCII symbol 'A'
        int rightLimit = 90; // ASCII symbol 'Z'
        int targetStringLength = 1;
        Random random = new Random();

        String generatedString = random
                .ints(leftLimit, rightLimit + 1).collect(StringBuilder::new,
                        StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    /**
     * Returns a string that has ASCII symbols (a-z) and a given length.
     * @param targetStringLength
     * @return String
     */
    public static String getRamdomStringLowercaseLetters(
            int targetStringLength) {
        int leftLimit = 97; // ASCII symbol 'a'
        int rightLimit = 122; // ASCII symbol 'z'
        Random random = new Random();

        String generatedString = random
                .ints(leftLimit, rightLimit + 1).collect(StringBuilder::new,
                        StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    /**
     * Returns a one ASCII symbol (a-z).
     * @return String
     */
    public static String getRamdomStringLowercaseLetters() {
        int leftLimit = 97; // ASCII symbol 'a'
        int rightLimit = 122; // ASCII symbol 'z'
        int targetStringLength = 1;
        Random random = new Random();

        String generatedString = random
                .ints(leftLimit, rightLimit + 1).collect(StringBuilder::new,
                        StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    /**
     * Returns a string that has ASCII symbols (0-9) and a given length.
     * @param targetStringLength
     * @return String
     */
    public static String getRamdomStringNumbers2(int targetStringLength) {
        int leftLimit = 48; // ASCII symbol '0'
        int rightLimit = 57; // ASCII symbol '9'
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
//                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength).collect(StringBuilder::new,
                        StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    /**
     * Returns a string that has ASCII symbols (0-9)(A-z), special symbols
     * (!"#$%^&()*+,-./:;<=>?@[\]^_'{|}~) and a given length.
     * @param targetStringLength
     * @return String
     */
    public static String getRamdomString2(int targetStringLength) {
        int leftLimit = 33; // ASCII symbol '!'
        int rightLimit = 126; // ASCII symbol '~'
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
//                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength).collect(StringBuilder::new,
                        StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }
}
