/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.client.packages.tcp;

import com.hermes.common.packages.tcp.HPackage;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 *
 * @author joaquin
 */
public class P45  extends HPackage
{
    
    private String username;
    private boolean ignore;

    public P45(String username,boolean ignore)
    {
        super(45);
        this.username = username;
        this.ignore=ignore;
    }

    public P45(ByteBuffer bb)
    {
       super(45);
       bb.order(ByteOrder.LITTLE_ENDIAN);
       this.username = readNullTerminatedString(bb);
    }

    public String getUsername()
    {
        return username;
    }

    @Override
    public ByteBuffer getPayload()
    {
        byte[] bUsername = this.username.getBytes(charset);

        ByteBuffer msg = ByteBuffer.allocate(bUsername.length+2);
        msg.order(ByteOrder.LITTLE_ENDIAN);
        if(ignore)
        {
             msg.put((byte)1);
        }
        else
        {
             msg.put((byte)0);
        }
       
        msg.put(bUsername);
         msg.put((byte)0);
        return msg;
    }
    
}
