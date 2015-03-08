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
public class P13 extends HPackage
{

    private String username;
    private String personalMessage;

    public P13(String username, String personalMessage)
    {
        super(13);
        this.username = username;
        this.personalMessage = personalMessage;
    }
    
    
    public P13(ByteBuffer bb)
    {
        super(13);
        this.username=readNullTerminatedString(bb);
        this.personalMessage=readNullTerminatedString(bb);
    }

    
    
    @Override
    public ByteBuffer getPayload()
    {
        //TODO: IMPLEMENTS
        
        return null;
    }

    @Override
    public String toString()
    {
        return this.username+" --> "+this.personalMessage;
    }

    public String getPersonalMessage()
    {
        return personalMessage;
    }

    public String getUsername()
    {
        return username;
    }
}
