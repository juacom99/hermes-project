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
public class P11 extends HPackage
{

    private String sender;
    private String text;

    public P11(String sender, String text)
    {
        super(11);
        this.sender = sender;
        this.text = text;
    }

    public P11(ByteBuffer bb)
    {
        super(11);
        this.sender=readNullTerminatedString(bb);
        this.text=readNullTerminatedString(bb);
    }

    @Override
    public String toString()
    {
        return this.sender+" "+this.text;
    }
    
    
    @Override
    public ByteBuffer getPayload()
    {
        return null;
    }
    
}
