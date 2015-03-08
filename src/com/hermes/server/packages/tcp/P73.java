/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hermes.server.packages.tcp;

import com.hermes.common.packages.tcp.HPackage;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;

/**
 *
 * @author jomartinez
 */
public class P73 extends HPackage
{
    private String caption;
    private String url;

    public P73(String caption, String url)
    {
        super(72);
        this.caption = caption;
        this.url = url;
    }

    public P73(ByteBuffer bb) throws MalformedURLException
    {
        super(73);
       this.url=readNullTerminatedString(bb);
       this.caption=readNullTerminatedString(bb);
    }

    
    
    @Override
    public ByteBuffer getPayload()
    {
        //TODO:IMPEMENTS
        return null;
    }

    @Override
    public String toString()
    {
        return this.caption+"["+this.url+"]";
    }
    
    
    
}
