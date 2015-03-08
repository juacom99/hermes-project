/*
Hermes Project - Chat client/server for Ares Galaxy P2P
Copyright (C) 2011  Joaquin Martinez (juacom04@gmail.com)

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
*/
package com.hermes.common.constants;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @author joaquin
 * This Enumerate represents the posibles Line Types in the Ares Galaxy Protocol
 */
public enum HLineType
{
    HLNone(0),
    HL14_4(1440),
    HL28_8(2880),
    HL33_3(3330),
    HL56_00(5600),
    HL64(6400),
    HL128(12800),
    HLCable(25000),
    HLDSL(35000),
    HLT1(1000000),
    HLT2(2500000);
    
   
    private int value;

    private HLineType(int value)
    {
        this.value = value;
    }
    
    
    private static final Map<Integer,HLineType> lookup = new HashMap<Integer,HLineType>();

     static {
          for(HLineType s : EnumSet.allOf(HLineType.class))
               lookup.put(s.getValue(), s);
     }

     public static HLineType getValue(int key)
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
