/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.client.events;

import java.util.Date;

/**
 *
 * @author jomartinez
 */
public class HClientMessageEvent extends HClientEvent
{
    private String sender;
    private String text;

    public HClientMessageEvent(String sender, String text)
    {
        super();
        this.sender = sender;
        this.text = text;
    }

    public String getSender()
    {
        return sender;
    }

    public String getText()
    {
        return text;
    }    
}
