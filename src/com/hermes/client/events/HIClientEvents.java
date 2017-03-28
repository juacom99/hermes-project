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
    
    public void onJoin(HClientUserEvent evt);
    
    public void onPart(HClientUserEvent evt);
    
    public void onPersonalMessage(HClientPersonalMessageEvent evt);
    
     public void onAvatar(HClientAvatarEvent evt);
     
    public void onEmote(HClientEmoteEvent evt);
        
    public void onURL(HClientUrlEvent evt);
    
    public void onTopic(HClientTopicEvent evt);
    
    public void onUserList(HClientUserListevent evt);
    
    public void onUserListEnds(HClientEvent evt);
    
    public void onConnect(HClientEvent evt);
    
    public void onDisconnect(HClientEvent evt);
    
    public void onUserUpdate(HClientUserUpdateEvent evt);
    
    public void onServerAck(HClientAckEvent evt);
    
    public void onUserIsIgnorinYou(HClientUserEvent evt);
    
    public void onClientRected(HClientRedirectedEvent evt);
}
