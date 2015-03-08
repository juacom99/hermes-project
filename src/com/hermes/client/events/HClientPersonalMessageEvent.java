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
public class HClientPersonalMessageEvent extends HClientEvent
{
    private String username;
    private String personalMessage;

    public HClientPersonalMessageEvent(String username, String personalMessage)
    {
        super();
        this.username = username;
        this.personalMessage = personalMessage;
    }
    
    
}
