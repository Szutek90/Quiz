package com.app.service.provider;

import java.util.Scanner;

public class UserInputProvider {
    private final Scanner sc = new Scanner(System.in);

    public String getUserText() {
        return sc.nextLine();
    }

    public int getUserInt() {
        return sc.nextInt();
    }
}
