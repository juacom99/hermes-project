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

    private String username;
    private String text;

    public P11(String username, String text)
    {
        super(11);
        this.username = username;
        this.text = text;
    }

    public P11(ByteBuffer bb)
    {
        super(11);
        this.username=readNullTerminatedString(bb);
        this.text=readNullTerminatedString(bb);
    }

    @Override
    public String toString()
    {
        return this.username+" "+this.text;
    }

    public String getUsername()
    {
        return username;
    }

    public String getText()
    {
        return text;
    }     
    
    @Override
    public ByteBuffer getPayload()
    {
        return null;
    }
    
}
