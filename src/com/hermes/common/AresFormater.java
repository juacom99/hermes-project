/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.common;

import java.awt.Color;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author joaquin
 */
public class AresFormater
{

    public static final char FOREGROUND_CHARACTER = ((char) 3);
    public static final char BACKGROUND_CHARACTER = ((char) 5);
    public static final char BOLD_CHARACTER = ((char) 6);
    public static final char ITALIC_CHARACTER = ((char) 9);
    public static final char UNDERLINE_CHARACTER = ((char) 7);


    public static final Color[] COLORS =
    {
        new Color(248, 248, 248),
        Color.BLACK,
        new Color(0, 0, 128),
        new Color(0, 128, 0),
        Color.RED,
        new Color(128, 0, 0),
        new Color(128, 0, 128),
        new Color(255, 128, 0),
        new Color(255, 255, 0),
        Color.GREEN,
        new Color(0, 128, 128),
        new Color(0, 255, 255),
        Color.BLUE,
        new Color(255, 0, 255),
        new Color(128, 128, 128),
        new Color(191, 191, 191)
    };

    private static AresFormater instance;

    private AresFormater()
    {
       
        
    }

    public String toHTML(String s)
    {

        String ret = s;
        if (!s.trim().equals(""))
        {
            ret = ret.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
            ret = ret.replaceAll(" ", "&nbsp;");
            ret = ret.replaceAll("    ", "&#09;");
            ret = ret.replace(((char) 2) + "6", "" + AresFormater.BOLD_CHARACTER);
            ret = ret.replaceAll(((char) 2) + "7", "" + AresFormater.UNDERLINE_CHARACTER);
            ret = ret.replaceAll(((char) 2) + "9", "" + AresFormater.ITALIC_CHARACTER);
            ret = posterReplace(ret);
            ret = foregroundReplace(ret);
            ret = backGrounddReplace(ret);
            ret = boldReplace(ret);
            ret = underlineReplace(ret);
            ret = italicReplace(ret);
            ret = emoticonReplace(ret);
        }
        return ret;
    }

    private String backGrounddReplace(String str)
    {
        //replace foreground
        Pattern pattern = Pattern.compile(BACKGROUND_CHARACTER + "(\\d{2})((.*?))");
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();

        String color;
        String text;

        while (matcher.find())
        {
            color = "#" + Integer.toHexString(COLORS[Integer.parseInt(matcher.group(1))].getRGB()).substring(2);
            text = matcher.group(2);
            matcher.appendReplacement(sb, "<span style=\"background-color:" + color + "\">" + text);

        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private String foregroundReplace(String str)
    {
        //replace foreground

        Pattern pattern = Pattern.compile(FOREGROUND_CHARACTER + "(\\d{2})((.*?))");
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();

        String color;
        String text;

        while (matcher.find())
        {
            color = "#" + Integer.toHexString(COLORS[Integer.parseInt(matcher.group(1))].getRGB()).substring(2);
            text = matcher.group(2);
            matcher.appendReplacement(sb, "<span style=\"color:" + color + "\">" + text);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private String boldReplace(String str)
    {
        String s = "(" + BOLD_CHARACTER + "(.*?)" + BOLD_CHARACTER + ")|(" + BOLD_CHARACTER + "(.*?)$)";
        /* Pattern pattern = Pattern.compile(s, Pattern.UNICODE_CHARACTER_CLASS);
         Matcher matcher = pattern.matcher(str);
         StringBuffer sb = new StringBuffer();

         String color;
         String text;

         while (matcher.find())
         {
         text = matcher.group(0);

         matcher.appendReplacement(sb, "<span style='font-weight:bold;'>" + text + "</span>");
         }

         matcher.appendTail(sb);
         return sb.toString();*/

        return str.replaceAll(s, "<span style='font-weight:bold;'>$0</span>");
    }

    private String underlineReplace(String str)
    {
        String s = "(" + UNDERLINE_CHARACTER + "(.*?)" + UNDERLINE_CHARACTER + ")|(" + UNDERLINE_CHARACTER + "(.*?)$)";
        /*  Pattern pattern = Pattern.compile(s, Pattern.UNICODE_CHARACTER_CLASS);
         Matcher matcher = pattern.matcher(str);
         StringBuffer sb = new StringBuffer();

         String text;


         while (matcher.find())
         {
         text = matcher.group(0);

         matcher.appendReplacement(sb, "<span style=\"text-decoration: underline;\">" + text + "</span>");
         }

         matcher.appendTail(sb);
         return sb.toString();*/

        return str.replaceAll(s, "<span style='font-weight:bold;'>$0</span>");
    }

    private String italicReplace(String str)
    {
        String s = "(" + ITALIC_CHARACTER + "(.*?)" + ITALIC_CHARACTER + ")|(" + ITALIC_CHARACTER + "(.*?)$)";
        /* Pattern pattern = Pattern.compile(s, Pattern.UNICODE_CHARACTER_CLASS);
         Matcher matcher = pattern.matcher(str);
         StringBuffer sb = new StringBuffer();
         String text;

         while (matcher.find())
         {
         text = matcher.group(0);

         matcher.appendReplacement(sb, "<span style=\"font-style: italic;\">" + text + "</span>");
         }

         matcher.appendTail(sb);
         return sb.toString();*/

        return str.replaceAll(s, "<span style='font-weight:bold;'>$0</span>");
    }

    private String posterReplace(String str)
    {

        String regexUrl = "((https?)://(www\\d?|[a-zA-Z0-9]+)?.[a-zA-Z0-9-]+(\\:|.)([a-zA-Z0-9.]+|(\\d+)?)([/?:].*)?)";
        String urlValidationRegex = "\\[(poster|image)=" + regexUrl + "\\]";
        Pattern p = Pattern.compile(urlValidationRegex);
        Matcher m = p.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (m.find())
        {
            String found = m.group(2);
            m.appendReplacement(sb, "<img src='" + found + "'/>");
        }
        m.appendTail(sb);

        str = sb.toString();
        str = str.replaceAll("(?!<img src=)^(" + regexUrl + ")", "<a href='$1'></a>");

        return sb.toString();

    }

    private String emoticonReplace(String str)
    {
        Iterator<String> i = EmoticonManager.getInstance().getAll();
        String ret = str;
        String key;
        String emoticonsFolder = (System.getProperty("user.dir") + File.separator + "emoticons" + File.separator).replace("\\", "\\\\");
        while (i.hasNext())
        {
            key = i.next();

            ret = ret.replaceAll("(?i)" + key, "<img src=\"file:\\" + emoticonsFolder + EmoticonManager.getInstance().get(key) + "\"/>");
        }
        return ret;
    }

    public static AresFormater getInstance()
    {
        if (instance == null)
        {
            instance = new AresFormater();
        }
        return instance;
    }

}
