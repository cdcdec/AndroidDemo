package com.cdc.crash;

public interface EventListener{
    void onLaunchErrorActivity();

    void onRestartAppFromErrorActivity();

    void onCloseAppFromErrorActivity();
}
