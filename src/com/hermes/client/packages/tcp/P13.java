/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.client.packages.tcp;

import com.hermes.common.packages.tcp.HPackage;
import static com.hermes.common.packages.tcp.HPackage.charset;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 *
 * @author joaquin
 */
public class P13 extends HPackage
{
    private String personalMessage;

    public P13(String personalMessage)
    {
        super(13);
        this.personalMessage = personalMessage;
    }

    @Override
    public ByteBuffer getPayload()
    {
        byte[] bMessage = this.personalMessage.getBytes(charset);

        ByteBuffer msg = ByteBuffer.allocate(bMessage.length+1);
        msg.order(ByteOrder.LITTLE_ENDIAN);
        msg.put(bMessage);
        msg.put((byte)0);
        return msg;
    }
    
    
}
