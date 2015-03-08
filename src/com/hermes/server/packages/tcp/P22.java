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
 * @author jomartinez
 */
public class P22 extends HPackage
{
    private String username;

    public P22(String user)
    {
        super(22);
        this.username = user;
    }

    public P22(ByteBuffer bb)
    {
        super(22);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        username=readNullTerminatedString(bb);
    }
    
    
    
    @Override
    public ByteBuffer getPayload()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getUser()
    {
        return username;
    }
    
    
    
}
