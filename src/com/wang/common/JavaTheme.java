package com.wang.common;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class JavaTheme
{

    public static String tag = "DEBUG";

    public static void print(String message)
    {
        System.out.println(tag + ": " + message);
    }

    public final static String LOOKANDFEEL_METAL = "Metal";
    public final static String LOOKANDFEEL_SYSTEM = "System";
    public final static String LOOKANDFEEL_MOTIF = "Motif";
    public final static String LOOKANDFEEL_GTK = "GTK";
    public final static String LOOKANDFEEL_NIBUM = "Nibum";
    public final static String LOOKANDFEEL_WINDOWS = "Windows";

    public static void setLookAndFeel(String lookandfeel)
    {
        String result = "Motif";

        if (lookandfeel.equals("Metal"))
        {
            result = UIManager.getCrossPlatformLookAndFeelClassName();
            //  an alternative way to set the Metal L&F is to replace the 
            // previous line with:
            // lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";

        } else if (lookandfeel.equals("System"))
        {
            result = UIManager.getSystemLookAndFeelClassName();
        } else if (lookandfeel.equals("Motif"))
        {
            result = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
        } else if (lookandfeel.equals("GTK"))
        {
            result = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
        } else if (lookandfeel.equals("Nibum"))
        {
            result = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
        } else if (lookandfeel.equals("Windows"))
        {
            result = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        } else
        {
            System.err.println("Unexpected value of LOOKANDFEEL specified: "
                    + lookandfeel);
            result = UIManager.getCrossPlatformLookAndFeelClassName();
        }

        try
        {
            UIManager.setLookAndFeel(result);
        } catch (ClassNotFoundException e)
        {
            System.err.println("Couldn't find class for specified look and feel:"
                    + result);
            System.err.println("Did you include the L&F library in the class path?");
            System.err.println("Using the default look and feel.");
        } catch (UnsupportedLookAndFeelException e)
        {
            System.err.println("Can't use the specified look and feel ("
                    + result
                    + ") on this platform.");
            System.err.println("Using the default look and feel.");
        } catch (Exception e)
        {
            System.err.println("Couldn't get specified look and feel ("
                    + result
                    + "), for some reason.");
            System.err.println("Using the default look and feel.");
            e.printStackTrace();
        }
    }

}
