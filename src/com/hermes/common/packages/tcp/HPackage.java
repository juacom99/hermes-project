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
package com.hermes.common.packages.tcp;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

/**
 *
 * @author joaquin
 */
public abstract class HPackage {

    private int id;
    //TODO: change to protected
    public static Charset charset = Charset.forName("UTF-8");

    public HPackage() {

    }

    public HPackage(int id) {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }
    
    
    protected int findNull(ByteBuffer bb)
    {
        
        int pos=bb.position();
        int i=pos;
        while(bb.hasRemaining())
        {
            
            if(bb.get()==0)
            {
                break;
            }
            i=bb.position();
        }
        bb.position(pos);
        return i;
    }
    
    
    protected String readNullTerminatedString(ByteBuffer bb)
    {
        bb.order(ByteOrder.LITTLE_ENDIAN);
        String ret="";
        int indiceNull=findNull(bb);
        
        byte[] bString=new byte[indiceNull-bb.position()];
        
        bb.get(bString);
        
        if(bb.remaining()>=1)
        {
             //skip 
        bb.get();
        }
       
        return new String(bString,charset);
    }
    /**
     *
     */
    public abstract ByteBuffer getPayload();

}
