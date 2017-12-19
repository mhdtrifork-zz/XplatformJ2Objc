package com.xplatformdemo.libsharedcode;

/**
 * Created by Morten on 18/12/2017.
 */

public class Verified {
    private boolean _isValid;
    private String _errorMsg;

    public boolean isValid() {
        return _isValid;
    }

    public String errorMsg() {
        return _errorMsg;
    }

    public Verified(boolean isValid, String errorMsg) {
        this._isValid = isValid;
        this._errorMsg = errorMsg;

    }

}
