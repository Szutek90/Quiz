package com.app.service.provider;

import java.util.Scanner;

public interface UserInputProvider {
    Scanner sc = new Scanner(System.in);

    static String getUserText() {
        return sc.nextLine();
    }
}
