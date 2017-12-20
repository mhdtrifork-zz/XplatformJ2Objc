package com.xplatformdemo.libsharedcode;

import com.google.j2objc.annotations.ObjectiveCName;

@ObjectiveCName("StringVerification")
public class StringVerification {

    @ObjectiveCName("verifyUsername:")
    public Verified verifyUsername(String username) {
        if (username.length() > 0) {
            return new Verified(true, "");
        } else {
            return new Verified(false, "You must specify a username");
        }
    }

    @ObjectiveCName("verifyPassword:")
    public Verified verifyPassword(String password) {
        if (password.length() >= 4) {
            return new Verified(true, "");
        } else {
            return new Verified(false, "Passwords must be at least 4 characters");
        }
    }

    @ObjectiveCName("compareString:withString:")
    public static Verified compareStrings(String string1, String string2) {
        if (string1.equals(string2)) {
            return new Verified(true, "");
        } else {
            return new Verified(false, "The 2 entered passwords, must be identical");
        }
    }
}
