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

/*
 * @author Joaquin Martinez
 * This Class represents the MSG_CHAT_SERVER_LOGIN_ACK Package
 * Data Format:    
 * [1 Byte  - OpChange]
 * [1 Byte  - null]
 */
public class P75 extends HPackage
{

    private byte admin;

    public P75(byte admin)
    {
        super(75);
        this.admin = admin;
    }
    
    public P75(ByteBuffer bb)
    {
        super(75);
        this.admin=bb.get();
        
        bb.get();
    }

    @Override
    public String toString()
    {
        return "OP Change: "+this.admin;
    }
    
    
    
    
    @Override
    public ByteBuffer getPayload()
    {
        //TODO: IMPLEMENT
        return null;
    }
    
}
