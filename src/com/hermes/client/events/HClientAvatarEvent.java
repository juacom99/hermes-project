/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.client.events;

import javax.swing.ImageIcon;

/**
 *
 * @author jomartinez
 */
public class HClientAvatarEvent extends HClientEvent
{
    private String username;
    private ImageIcon avatar;

    public HClientAvatarEvent(String username, ImageIcon avatar)
    {
        super();
        this.username = username;
        this.avatar = avatar;
    }

    public String getUsername()
    {
        return username;
    }

    public ImageIcon getAvatar()
    {
        return avatar;
    }
}
