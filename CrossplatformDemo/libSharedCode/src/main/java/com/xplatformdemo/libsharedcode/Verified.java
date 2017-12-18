package com.xplatformdemo.libsharedcode;

/**
 * Created by Morten on 18/12/2017.
 */

public class Verified {
    public final boolean isValid;
    public final String errorMsg;

    public Verified(boolean isValid, String errorMsg) {
        this.isValid = isValid;
        this.errorMsg = errorMsg;

    }

}
