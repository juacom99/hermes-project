/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.common;

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
    private static final String[] COLORS =
    {
        "#ffffff", "#000000", "#000080", "#008000", "#ff0000", "#800000", "#800080", "#ff8000", "#ffff00", "#00ff00", "#008080", "#00ffff", "#0000ff", "#ff00ff", "#808080", "#c0c0c0"
    };

    private static AresFormater instance;

    private AresFormater()
    {

    }

    public String toHTML(String s)
    {

        String ret = foregroundReplace(s);
        ret = backGrounddReplace(ret);
        ret = specialHTMLReplace(ret);

        return ret;
    }

    public String backGrounddReplace(String str)
    {
        char bgchar = ((char) 5);
        //replace foreground
        Pattern pattern = Pattern.compile(bgchar + "(\\d{2})((\\w*))");
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();

        String color;
        String text;

        while (matcher.find())
        {
            color = COLORS[Integer.parseInt(matcher.group(1))];
            text = matcher.group(2);
            matcher.appendReplacement(sb, "<span style=\"background-color:" + color + "\">" + text);

        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public String foregroundReplace(String str)
    {
        char fgchar = ((char) 3);
        //replace foreground

        Pattern pattern = Pattern.compile(fgchar + "(\\d{2})((\\w*))");
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();

        String color;
        String text;

        while (matcher.find())
        {
            color = COLORS[Integer.parseInt(matcher.group(1))];
            text = matcher.group(2);
            matcher.appendReplacement(sb, "<span style=\"color:" + color + "\">" + text);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public String specialHTMLReplace(String str)
    {

        String urlValidationRegex = "(https?|ftp)://(www\\d?|[a-zA-Z0-9]+)?.[a-zA-Z0-9-]+(\\:|.)([a-zA-Z0-9.]+|(\\d+)?)([/?:].*)?";
        Pattern p = Pattern.compile(urlValidationRegex);
        Matcher m = p.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (m.find())
        {
            String found = m.group(0);
            m.appendReplacement(sb, "<a href='" + found + "'>" + found + "</a>");
        }
        m.appendTail(sb);
        return sb.toString().replaceAll(" ", "&nbsp;");
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
