/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hermes.common;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author jomartinez
 */
public class EmoticonManager
{
        public TreeMap<String, String> emoticons ;
        
        public static EmoticonManager instance;

    private EmoticonManager()
    {
        
        HashMap<String,String> temp=new HashMap<>();
        
        temp.put(":-?\\)", "00.gif");
        temp.put(":-?d", "01.gif");
        temp.put(";-?\\)", "02.gif");
        temp.put(":-?o", "03.gif");
        temp.put(":-?p", "04.gif");
        temp.put("\\(h\\)", "05.gif");
        temp.put(":-?@", "06.gif");
        temp.put(":-?\\$", "07.gif");
        temp.put(":-?s", "08.gif");
        temp.put(":-?\\(", "09.gif");
        temp.put(":'\\(", "10.gif");
        temp.put(":-?\\|", "11.gif");
        temp.put("\\(6\\)", "12.gif");
        temp.put("\\(a\\)", "13.gif");
        temp.put("\\(l\\)", "14.gif");
        temp.put("\\(u\\)", "15.gif");
        temp.put("\\(m\\)", "16.gif");
        temp.put("\\(@\\)", "17.gif");
        temp.put("\\(&\\)", "18.gif");
        temp.put("\\(s\\)", "19.gif");
        temp.put("\\(\\*\\)", "20.gif");
        temp.put("\\(~\\)", "21.gif");
        temp.put("\\(e\\)", "22.gif");
        temp.put("\\(8\\)", "23.gif");
        temp.put("\\(f\\)", "24.gif");
        temp.put("\\(w\\)", "25.gif");
        temp.put("\\(o\\)", "26.gif");
        temp.put("\\(k\\)", "27.gif");
        temp.put("\\(g\\)", "28.gif");
        temp.put("\\(\\^\\)", "29.gif");
        temp.put("\\(p\\)", "30.gif");
        temp.put("\\(i\\)", "31.gif");
        temp.put("\\(c\\)", "32.gif");
        temp.put("\\(t\\)", "33.gif");
        temp.put("\\(\\{\\)", "34.gif");
        temp.put("\\(\\}\\)", "35.gif");
        temp.put("\\(b\\)", "36.gif");
        temp.put("\\(d\\)", "37.gif");
        temp.put("\\(z\\)", "38.gif");
        temp.put("\\(x\\)", "39.gif");
        temp.put("\\(y\\)", "40.gif");
        temp.put("\\(n\\)", "41.gif");
        temp.put(":-?\\[", "42.gif");
        temp.put("\\(1\\)", "43.gif");
        temp.put("\\(2\\)", "44.gif");
        temp.put("\\(3\\)", "45.gif");
        temp.put("\\(4\\)", "46.gif");
        
         ValueComparator bvc =  new ValueComparator(temp);
        emoticons = new TreeMap<String,String>(bvc);
         emoticons.putAll(temp);
                 
        
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

class ValueComparator implements Comparator<String> {

    Map<String, String> base;
    public ValueComparator(Map<String, String> base) {
        this.base = base;
    }

    // Note: this comparator imposes orderings that are inconsistent with equals.    
    public int compare(String a, String b) {
      return  base.get(a).compareTo(base.get(b));
    }
}