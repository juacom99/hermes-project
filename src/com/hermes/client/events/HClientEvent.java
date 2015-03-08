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
public class HClientEvent
{
    private Date time;

    public HClientEvent()
    {
        this.time = new Date();
    }

    public Date getTime()
    {
        return time;
    }
    
    
    
    
}
