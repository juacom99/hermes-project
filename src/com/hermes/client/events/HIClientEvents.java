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
public interface HIClientEvents
{
    public void onPublicMessage(HClientMessageEvent evt);
    
    public void onPrivateMessage(HClientMessageEvent evt);
    
    public void onNoSuch(HClientNoSuchEvent evt);
    
    public void onJoin(HClientJoinEvent evt);
    
    public void onPart(HClientPartEvent evt);
    
    public void onPersonalMessage(HClientPersonalMessageEvent evt);
    
     public void onAvatar(HClientAvatarEvent evt);
     
    public void onEmote(HClientEmoteEvent evt);
    
}
