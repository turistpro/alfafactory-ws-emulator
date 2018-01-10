package com.github.javafaker;

public class Russian {

    protected FakerRussian faker;

    private static final int[] REGION_NUMBERS = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,83,86,87,89,91,92};
    private static final int[] INN_NUMBERS = {3, 7, 2, 4, 10, 3, 5, 9, 4, 6, 8};

    public Russian(FakerRussian faker) {
        this.faker = faker;
    }

    private int innCheckSum(String inn, int offset, int arrOffset) {
        int sum = 0;
        int length = inn.length();
        for (int i = 0; i < length - offset; i++) {
            sum += (inn.charAt(i) - '0') * INN_NUMBERS[i + arrOffset];
        }
        return (sum % 11) % 10;
    }

    private boolean innCheck(String inn, int offset, int arrOffset) {
        return innCheckSum(inn, offset, arrOffset) == inn.charAt(inn.length() - offset) - '0';
    }

    public boolean isValidINN(String inn) {
        int length = inn.length();
        if (length == 12) {
            return innCheck(inn, 2, 1) && innCheck(inn, 1, 0);
        } else {
            return innCheck(inn, 1, 2);
        }
    }

    public String inn12() {
        String res = String.format("%02d", REGION_NUMBERS[faker.number().numberBetween(0, REGION_NUMBERS.length-1)]);
        res += faker.number().randomNumber(8, true);
        res += String.format("%01d", innCheckSum(res, 0, 1));
        res += String.format("%01d", innCheckSum(res, 0, 0));
        //System.out.println(isValidINN(res));
        return res;
    }

    public String inn10() {
        String res = String.format("%02d", REGION_NUMBERS[faker.number().numberBetween(0, REGION_NUMBERS.length-1)]);
        res += faker.number().randomNumber(7, true);
        res += String.format("%01d", innCheckSum(res, 0, 2));
        return res;
    }

}
