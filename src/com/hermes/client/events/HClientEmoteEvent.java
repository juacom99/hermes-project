/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.client.events;

/**
 *
 * @author joaquin
 */
public class HClientEmoteEvent extends HClientEvent
{
    private String username;
    private String emote;

    public HClientEmoteEvent(String username, String emote)
    {
        super();
        this.username = username;
        this.emote = emote;
    }

    public String getUsername()
    {
        return username;
    }
    
    public String getEmote()
    {
        return emote;
    }
}
