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
package com.hermes.client.packages.tcp;

import com.hermes.common.packages.tcp.HPackage;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;


public class P11 extends HPackage
{

    private String emote;

    public P11(String emote)
    {
        super(11);
        this.emote = emote;
    }

    public P11(ByteBuffer bb)
    {
        super(11);
        bb.order(ByteOrder.LITTLE_ENDIAN);        
        this.emote=new String(bb.array(),charset);
    }
    
    
    
    @Override
    public ByteBuffer getPayload()
    {
         byte[] bEmote = emote.getBytes(charset);

        ByteBuffer msg = ByteBuffer.allocate(bEmote.length+1);
        msg.order(ByteOrder.LITTLE_ENDIAN);
        msg.put(bEmote);
        msg.put((byte)0);
        return msg;
    }
    
}
