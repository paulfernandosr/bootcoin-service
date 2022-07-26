package com.nttdata.bootcoinservice.util;

public class Constants {

    private Constants() {
        throw new IllegalStateException(UTILITY_CLASS);
    }

    public static final String UTILITY_CLASS = "Utility class";

    public static final double PURCHASE_PRICE = 0.05;
    public static final double SELLING_PRICE = 0.025;
    public static final String PAYMENT_METHOD_TRANSFER = "TRANSFER";
    public static final String PAYMENT_METHOD_YANKI = "YANKI";
    public static final String PENDING_STATUS = "PENDING";
    public static final String ACCEPTED_STATUS = "ACCEPTED";
    public static final String TRANSACTION_NOT_FOUND = "Transaction not found";

}
