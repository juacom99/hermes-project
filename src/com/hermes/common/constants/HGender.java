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
 * @author Joaquin
 * This Enumerate represents the posibles admins levels in the Ares Galaxy Protocol
 * 0 - Unknown
 * 1 - Male
 * 2 - Female
 */
public enum HGender
{
    Unknow((byte)0),
    Male((byte)1),
    Female((byte)2);

    private byte value;
    
    private static final Map<Byte,HGender> lookup = new HashMap<Byte,HGender>();

     static {
          for(HGender s : EnumSet.allOf(HGender.class))
               lookup.put(s.getValue(), s);
     }

     
    

    private HGender(byte value)
    {
        this.value = value;
    }

    public byte getValue()
    {
        return value;
    }

    public void setValue(byte value)
    {
        this.value = value;
    }

    public static HGender get(byte code) {
          return lookup.get(code);
     }

}
