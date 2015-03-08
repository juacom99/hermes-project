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
 * This Enumerate Represents the posibles admins levels in the Ares Galaxy Protocol
 * 0- Normal User
 * 1- Moderator
 * 2- Administrator
 * 3- Host
 */
public enum HAdminLevel
{
    Normal_User((byte)0),
    Moderator((byte)1),
    Administratior((byte)2),
    Host((byte)3);
    
    private byte value;

    private HAdminLevel(byte value)
    {
        this.value = value;
    }
    
    
    private static final HashMap<Byte, HAdminLevel> lookup = new HashMap<Byte, HAdminLevel>();

    static
    {
        for (HAdminLevel l : EnumSet.allOf(HAdminLevel.class))
        {
            lookup.put(l.getValue(), l);
        }
    }

    public static HAdminLevel get(byte key)
    {
        return lookup.get(key);
    }
    public byte getValue()
    {
        return value;
    }    
}
