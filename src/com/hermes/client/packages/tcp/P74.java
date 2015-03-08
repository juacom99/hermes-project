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
 * @author jomartinez
 */
public class P74 extends HPackage
{
    private String command;

    public P74(String command)
    {
        super(74);
        this.command = command;
    }

    public P74(ByteBuffer bb)
    {
        super(74);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        this.command=readNullTerminatedString(bb);
    }

    @Override
    public ByteBuffer getPayload()
    {
         byte[] bCommand = command.getBytes(charset);

        ByteBuffer msg = ByteBuffer.allocate(bCommand.length);
        msg.order(ByteOrder.LITTLE_ENDIAN);
        msg.put(bCommand);
        return msg;
    }
    
    
    
    
}
