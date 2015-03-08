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
 * This Class represents the MSG_CHAT_SERVER_LOGIN_ACK Package
 * Data Format:    
 * [x Bytes - Username]
 * [1 Byte  - null]
 * [x Bytes - Room Name]
 * [1 Byte  - null]
 */
public class P3 extends HPackage
{

    private String username;
    private String roomname;

    public P3(String username, String roomname)
    {
        super(3);
        this.username = username;
        this.roomname = roomname;
    }

    public P3(ByteBuffer bb)
    {
        super(3);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        this.username = readNullTerminatedString(bb);
        this.roomname = readNullTerminatedString(bb);
    }

    @Override
    public ByteBuffer getPayload()
    {
       
        byte[] bUsername = this.username.getBytes(charset);
        byte[] bRoomname = this.roomname.getBytes(charset);

        int length = 2 + bUsername.length + bRoomname.length;

        ByteBuffer msg = ByteBuffer.allocate(length);
        msg.order(ByteOrder.LITTLE_ENDIAN);

        msg.put(bUsername);
        msg.put((byte) 0);
        msg.put(bRoomname);
        msg.put((byte) 0);
        return msg;
    }

    public String getUsername() {
        return username;
    }

    public String getRoomname() {
        return roomname;
    }

    @Override
    public String toString()
    {
        return "Welcome "+this.username+" to "+this.roomname;
    }
    
    
}
