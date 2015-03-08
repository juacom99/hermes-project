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
public class PDefault extends HPackage
{

    private int length;
    private byte[] rawPayload;
    public PDefault(short id,ByteBuffer bb,int length)
    {
        super(id);
        this.length=length;
       rawPayload=new byte[length];
       bb.get(rawPayload);
    }

    public PDefault(ByteBuffer bb)
    {
        this.rawPayload=bb.array();
    }

    
    
    @Override
    public ByteBuffer getPayload()
    {
        return null;
    }

    @Override
    public String toString()
    {
      return "Package With Id "+getId()+" and length "+this.length+" Unknown"+ new String(rawPayload,charset);
    }
    
    
    
    
    
}
