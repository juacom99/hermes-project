/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hermes.common;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author jomartinez
 */
public class EmoticonManager
{
        public HashMap<String, String> emoticons = new HashMap<String, String>();
        
        public static EmoticonManager instance;

    private EmoticonManager()
    {
        emoticons.put(":-?\\)", "0.gif");
        emoticons.put(":-?d", "1.gif");
        emoticons.put(";-?\\)", "2.gif");
        emoticons.put(":-?o", "3.gif");
        emoticons.put(":-?p", "4.gif");
        emoticons.put("\\(h\\)", "5.gif");
        emoticons.put(":-?@", "6.gif");
        emoticons.put(":-?\\$", "7.gif");
        emoticons.put(":-?s", "8.gif");
        emoticons.put(":-?\\(", "9.gif");
        emoticons.put(":'\\(", "10.gif");
        emoticons.put(":-?\\|", "11.gif");
        emoticons.put("\\(6\\)", "12.gif");
        emoticons.put("\\(a\\)", "13.gif");
        emoticons.put("\\(l\\)", "14.gif");
        emoticons.put("\\(u\\)", "15.gif");
        emoticons.put("\\(m\\)", "16.gif");
        emoticons.put("\\(@\\)", "17.gif");
        emoticons.put("\\(&\\)", "18.gif");
        emoticons.put("\\(s\\)", "19.gif");
        emoticons.put("\\(\\*\\)", "20.gif");
        emoticons.put("\\(~\\)", "21.gif");
        emoticons.put("\\(e\\)", "22.gif");
        emoticons.put("\\(8\\)", "23.gif");
        emoticons.put("\\(f\\)", "24.gif");
        emoticons.put("\\(w\\)", "25.gif");
        emoticons.put("\\(o\\)", "26.gif");
        emoticons.put("\\(k\\)", "27.gif");
        emoticons.put("\\(g\\)", "28.gif");
        emoticons.put("\\(\\^\\)", "29.gif");
        emoticons.put("\\(p\\)", "30.gif");
        emoticons.put("\\(i\\)", "31.gif");
        emoticons.put("\\(c\\)", "32.gif");
        emoticons.put("\\(t\\)", "33.gif");
        emoticons.put("\\(\\{\\)", "34.gif");
        emoticons.put("\\(\\}\\)", "35.gif");
        emoticons.put("\\(b\\)", "36.gif");
        emoticons.put("\\(d\\)", "37.gif");
        emoticons.put("\\(z\\)", "38.gif");
        emoticons.put("\\(x\\)", "39.gif");
        emoticons.put("\\(y\\)", "40.gif");
        emoticons.put("\\(n\\)", "41.gif");
        emoticons.put(":-?\\[", "42.gif");
        emoticons.put("\\(1\\)", "43.gif");
        emoticons.put("\\(2\\)", "44.gif");
        emoticons.put("\\(3\\)", "45.gif");
        emoticons.put("\\(4\\)", "46.gif");
    }
        
    
    public String get(String key)
    {
        return emoticons.get(key);
    }
    
    public Iterator<String> getAll()
    {
        return emoticons.keySet().iterator();
    }

    public static EmoticonManager getInstance()
    {
        if(instance==null)
        {
            instance=new EmoticonManager();
        }
        return instance;
    }
        
    
    
    
    
}
