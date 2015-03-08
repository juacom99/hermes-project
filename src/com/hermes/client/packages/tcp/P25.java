/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.client.packages.tcp;

import com.hermes.server.packages.tcp.*;
import com.hermes.common.packages.tcp.HPackage;
import static com.hermes.common.packages.tcp.HPackage.charset;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 *
 * @author jomartinez
 */
public class P25 extends HPackage
{
    private String recipient;
    private String message;

    public P25(String sender, String message)
    {
        super(25);
        this.recipient = sender;
        this.message = message;
    }

    public P25(ByteBuffer bb)
    {
        super(25);
        this.recipient = readNullTerminatedString(bb);
        this.message=readNullTerminatedString(bb);
    }

    @Override
    public ByteBuffer getPayload()
    {
        byte[] bRecipient=this.recipient.getBytes(charset);
       byte[] bMessage = this.message.getBytes(charset);

        ByteBuffer msg = ByteBuffer.allocate(bRecipient.length+bMessage.length+1);
        msg.order(ByteOrder.LITTLE_ENDIAN);
        msg.put(bRecipient);
        msg.put((byte)0);
        msg.put(bMessage);
        return msg;
    }

    @Override
    public String toString()
    {
        return "(PM)"+this.recipient+">"+this.message;
    }
    
    
    
    
    
}
