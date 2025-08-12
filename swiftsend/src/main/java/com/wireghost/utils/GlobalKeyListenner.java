package com.wireghost.utils;


import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import javafx.application.Platform;

public class GlobalKeyListenner implements  NativeKeyListener
{
    private final Runnable onF5Pressed;

    public GlobalKeyListenner(Runnable onF5Pressed)
    {
        this.onF5Pressed = onF5Pressed;
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
        GlobalScreen.addNativeKeyListener(this);
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e)
    {
        if (e.getKeyCode() == NativeKeyEvent.VC_F5) {
            System.out.println("F5 Global Detected !");
            Platform.runLater(onF5Pressed);
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e){}

    @Override
    public void nativeKeyTyped(NativeKeyEvent e){}

    public void shutdown()
    {
        try {
            GlobalScreen.unregisterNativeHook();
        } 
        catch (NativeHookException e) {
            e.printStackTrace();
        }
    }

}
