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

/*
 * This Enumerate represents the posibles Languages in the Ares Galaxy Protocol

*/
public enum HLanguage
{

    English((byte) 10),
    Arabic((byte) 11),
    Chinese((byte) 12),
    Chinese_taiwan((byte) 13),
    Czech((byte) 14),
    Dansk((byte) 15),
    Dutch((byte) 16),
    Japanese((byte) 17),
    Kurdish((byte) 18),
    Kyrgyz((byte) 19),
    Polish((byte) 20),
    Portugues((byte) 21),
    Slovak((byte) 22),
    Spanish((byte) 23),
    Spanish_Latine((byte) 24),
    Swedish((byte) 25),
    Turkish((byte) 26),
    Finnish((byte) 27),
    French((byte) 28),
    German((byte) 29),
    Italean((byte) 30),
    Russian((byte) 31);
    
    private byte value;

     private static final Map<Byte,HLanguage> lookup = new HashMap<Byte,HLanguage>();

     static {
          for(HLanguage s : EnumSet.allOf(HLanguage.class))
               lookup.put(s.getValue(), s);
     }
     
    private HLanguage(byte value)
    {
        this.value = value;
    }

    public byte getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return super.toString().replace("_"," ");
    }

    public static HLanguage get(byte value)
    {
        return lookup.get(value);
    }
}