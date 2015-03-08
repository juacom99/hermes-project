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
 * @author joaquin
 */
public class P5 extends HPackage
{

    private byte[] payload;
    public P5()
    {
        super(5);
    }

    public P5(ByteBuffer bb)
    {
        super(5);
       payload=bb.array(); 
    }

    @Override
    public ByteBuffer getPayload()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString()
    {
        return new String(payload,charset);
    }
    
    
    
    
}
