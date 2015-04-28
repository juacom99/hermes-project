/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.server.packages.tcp;

import com.hermes.common.packages.tcp.HPackage;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 *
 * @author joaquin
 */
public class P26  extends HPackage
{
    
    private String username;

    public P26(String username)
    {
        super(26);
        this.username = username;
    }

    public P26(ByteBuffer bb)
    {
       super(26);
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
        //TODO: IMPLEMENTS
        return null;
    }
    
}
