/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.client.events;

/**
 *
 * @author jomartinez
 */
public class HClientAckEvent extends HClientEvent
{
    private String channelName;
    private String username;

    public HClientAckEvent(String channelName, String username)
    {
        super();
        this.channelName = channelName;
        this.username = username;
    }

    public String getChannelName()
    {
        return channelName;
    }

    public String getUsername()
    {
        return username;
    }
}
