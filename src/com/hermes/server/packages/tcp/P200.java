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
public class P200 extends HPackage
{
    private String action;
    private String user;
    private byte unknown;
    
    public P200(ByteBuffer bb)
    {
        super(200);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        
        this.action=readNullTerminatedString(bb);
        this.user=readNullTerminatedString(bb);
        this.unknown=bb.get();        
    }

    
    
    @Override
    public ByteBuffer getPayload()
    {
        return null;
    }

    @Override
    public String toString()
    {
        return user+" is "+action+"("+unknown+")";
    }
    
    
    
}
