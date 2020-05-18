package com.system.test;

import java.util.Scanner;

public class test2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int l = scanner.nextInt();
        int r = scanner.nextInt();
        String ss[] = new String[r];
        String s = "";
        int n = 0;
        for(int i = 1; i <= r; i++) {
            s += i;
            ss[i - 1] = s;
        }
        for(int i = l - 1; i < r; i++) {
            if(Integer.parseInt(ss[i]) % 3 == 0) {
                n++;
            }
        }
        System.out.println(n);
        scanner.close();
    }
}