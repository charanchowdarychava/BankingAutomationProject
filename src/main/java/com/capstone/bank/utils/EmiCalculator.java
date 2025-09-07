package com.capstone.bank.utils;

public class EmiCalculator {
    // Standard EMI formula
    public static double monthlyEmi(double principal, double annualRate, int months) {
        double r = annualRate / 12.0 / 100.0;
        if (r == 0) return principal / months;
        double pow = Math.pow(1 + r, months);
        return (principal * r * pow) / (pow - 1);
    }
}
