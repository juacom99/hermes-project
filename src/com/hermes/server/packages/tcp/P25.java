/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.server.packages.tcp;

import com.hermes.common.packages.tcp.HPackage;
import java.nio.ByteBuffer;

/**
 *
 * @author jomartinez
 */
public class P25 extends HPackage
{
    private String sender;
    private String message;

    public P25(String sender, String message)
    {
        super(25);
        this.sender = sender;
        this.message = message;
    }

    public P25(ByteBuffer bb)
    {
        super(25);
        this.sender = readNullTerminatedString(bb);
        this.message=readNullTerminatedString(bb);
    }

    @Override
    public ByteBuffer getPayload()
    {
       return null;  
    }

    @Override
    public String toString()
    {
        return "(PM)"+this.sender+">"+this.message;
    }

    public String getSender()
    {
        return sender;
    }

    public String getMessage()
    {
        return message;
    }
}
