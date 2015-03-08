/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.common.constants;

import java.util.EnumSet;
import java.util.HashMap;

/**
 *
 * @author jomartinez
 */
public enum HServerFeatures
{
     
    SUPPORT_PVT((byte)1),
    SUPPORTS_SHARING((byte)2),
    SUPPORTS_COMPRESSION((byte)4),
    SUPPORTS_VC((byte)8),
    SUPPORTS_OPUS_VC((byte)16),
    SUPPORTS_ROOM_SCRIBBLES((byte)32),
    SUPPORTS_PM_SCRIBBLES((byte)64),
    SUPPORTS_HTML((byte)128);
    
    private byte value;

    private static final HashMap<Byte, HServerFeatures> lookup = new HashMap<Byte, HServerFeatures>();

    static
    {
        for (HServerFeatures l : EnumSet.allOf(HServerFeatures.class))
        {
            lookup.put(l.getValue(), l);
        }
    }
    
    private HServerFeatures(byte value)
    {
        this.value = value;
    }

    public byte getValue()
    {
        return value;
    }
    
    public static HServerFeatures get(byte code)
    {
        return lookup.get(code);
    }

    @Override
    public String toString()
    {
        return super.toString().replace("_", " ");
    }
}
