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
package com.hermes.common.serealization;

import com.hermes.common.packages.tcp.HPackage;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 *
 * @author jomartinez
 */
public class HPackageSerializer implements Serializable
{
    
    private static HPackageSerializer instance;

    private HPackageSerializer()
    {
    }
    
     public ByteBuffer getBytes(HPackage msg)
     {
        ByteBuffer payload = msg.getPayload();
        payload.flip();
        ByteBuffer ret = ByteBuffer.allocate(payload.capacity() + 3);
        ret.order(ByteOrder.LITTLE_ENDIAN);
        ret.putShort((short) payload.capacity());
        ret.put((byte)msg.getId());
        ret.put(payload);
        ret.rewind();
        return ret;
    }

    public static HPackageSerializer getInstance()
    {
        if(instance==null)
        {
            instance=new HPackageSerializer();
        }
        return instance;
    }
     
     
}
