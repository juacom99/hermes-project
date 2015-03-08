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
public class P250 extends HPackage
{

    private byte[] idk;

    private boolean canRoomScribble;
    
    public P250(byte[] idk)
    {
        super(250);
    }

    public P250(ByteBuffer bb)
    {
        super(250);
        idk=new byte[bb.limit()];
        bb.get(idk);
    }
    
    
    
    @Override
    public ByteBuffer getPayload()
    {
        return null;
    }

    @Override
    public String toString()
    {
        
        return "Content: "+new String(this.idk,charset);
    }
    
    
    
}
