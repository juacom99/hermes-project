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

/*
 * @author Joaquin
 * This Enumerate represents the posibles Browsing Types in the Ares Galaxy Protocol
 * 1- Not Browsable
 * 3- Browsable
 * 7- Browsable and support compress
 */
public enum HBrowsable
{
    
    Not_Browsable((byte)1),
    Browsable((byte)3),
    Browsable_and_support_compress((byte)7);
    
    private byte value;

    private static final HashMap<Byte, HBrowsable> lookup = new HashMap<Byte, HBrowsable>();

    static
    {
        for (HBrowsable l : EnumSet.allOf(HBrowsable.class))
        {
            lookup.put(l.getValue(), l);
        }
    }
    
    private HBrowsable(byte value)
    {
        this.value = value;
    }

    public byte getValue()
    {
        return value;
    }
    
    public static HBrowsable get(byte code)
    {
        return lookup.get(code);
    }

    @Override
    public String toString()
    {
        return super.toString().replace("_", " ");
    }
}
