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
 * This Class represents the MSG_CHAT_SERVER_PUBLIC Package
 * Data Format:
 * [x Bytes - Sender]
 * [1 Byte  - null]
 * [x Bytes - Message]
 * [1 Byte  - null]
 */
public class P10 extends HPackage
{

    private String sender;
    private String message;

    public P10(String sender, String message)
    {
        super(10);
        this.sender = sender;
        this.message = message;
    }

    public P10(ByteBuffer bb)
    {
        super(10);
       bb.order(ByteOrder.LITTLE_ENDIAN);
       this.sender = readNullTerminatedString(bb);
       this.message = readNullTerminatedString(bb);
    }

    @Override
    public ByteBuffer getPayload()
    {
        byte[] bSender = this.sender.getBytes(charset);
        byte[] bMessage = this.message.getBytes(charset);

        int length = 2 + bSender.length + bMessage.length;

        ByteBuffer msg = ByteBuffer.allocate(length);
        msg.order(ByteOrder.LITTLE_ENDIAN);

        msg.put(bSender);
        msg.put((byte) 0);
        msg.put(bMessage);
        msg.put((byte) 0);
        return msg;
    }

    public String getMessage()
    {
        return message;
    }

    public String getSender()
    {
        return sender;
    }

    @Override
    public String toString()
    {
        return this.sender+"> "+this.message;
    }
    
    
}
