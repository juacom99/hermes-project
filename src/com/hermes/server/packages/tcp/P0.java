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
package com.hermes.server.packages.tcp;

import com.hermes.common.packages.tcp.HPackage;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* 
 * @author Joaquin Martinez
 * This Class represents the MSG_CHAT_SERVER_ERROR Package
 * Data Format:
 * [x Bytes - Message]
 */
public class P0 extends HPackage
{

    private String message;

    public P0(String message)
    {
        super(0);
        this.message = message;
    }
    
    public P0(ByteBuffer bb)
    {
        super(0);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        
        this.message=new String(bb.array(),charset);
    }

    @Override
    public ByteBuffer getPayload()
    {
        byte[] bMessage = this.message.getBytes(charset);
        int length = bMessage.length;

        ByteBuffer msg = ByteBuffer.allocate(length);
        msg.order(ByteOrder.LITTLE_ENDIAN);
        msg.put(bMessage);

        return msg;
    }

    public String getMessage()
    {
        return message;
    }

    @Override
    public String toString()
    {
        return this.message;
    }
    
    
}
