/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.common.constants;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jomartinez
 */
public enum HShareTypes
{
    
    OTHER((byte)1),
    SOFTWARE((byte)2),
    DOCUMENT((byte)4),
    IMAGE((byte)8),
    VIDEO((byte)16),
    MP3((byte)32);
    
    
      private int value;

    private HShareTypes(int value)
    {
        this.value = value;
    }
    
    
    private static final Map<Integer,HShareTypes> lookup = new HashMap<Integer,HShareTypes>();

     static {
          for(HShareTypes s : EnumSet.allOf(HShareTypes.class))
               lookup.put(s.getValue(), s);
     }

     public static HShareTypes getValue(int key)
     {
         return lookup.get(key);
     }
    public int getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return super.toString().substring(2).replace("_",".");
    }
}
