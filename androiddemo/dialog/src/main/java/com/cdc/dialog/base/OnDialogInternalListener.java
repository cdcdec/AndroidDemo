package com.cdc.dialog.base;

public interface OnDialogInternalListener {
    void dialogAtLocation(int x, int y);

    void dialogDismiss();

    int[] getScreenSize();

    int getStatusBarHeight();
}
