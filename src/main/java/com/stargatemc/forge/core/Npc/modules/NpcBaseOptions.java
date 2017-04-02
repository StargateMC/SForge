/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stargatemc.forge.core.Npc.modules;

import com.stargatemc.forge.core.NpcFaction.RegisterableNpcFaction;
import com.stargatemc.forge.core.constants.NpcGender;
import com.stargatemc.forge.core.constants.NpcTexture;
import com.stargatemc.forge.core.constants.NpcTextureType;
import com.stargatemc.forge.core.constants.NpcVisibleOption;
import com.stargatemc.forge.core.constants.uPosition;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 *
 * @author draks
 */

@SideOnly(Side.SERVER)
public class NpcBaseOptions {
    
    private String name;
    private String title;
    private uPosition spawnPosition;
    private NpcTexture texture;
    private RegisterableNpcFaction faction;
    private NpcVisibleOption visibility;
    private NpcGender gender;
    
    public NpcGender getGender() {
        return this.gender;
    }
    
    public void setGender(NpcGender gender) {
        this.gender = gender;
    }

    public uPosition getSpawnPosition() {
        return this.spawnPosition;
    }
    
    public void setSpawnPosition(uPosition position) {
        this.spawnPosition = position;
    }
    
    public void setTexture(NpcTexture texture) {
        this.texture = texture;
    }
    
    public NpcTexture getTexture() {
        return this.texture;
    }
    
    public void setFaction(RegisterableNpcFaction faction) {
            this.faction = faction;
    }
    
    public RegisterableNpcFaction getFaction() {
        return this.faction;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setVisibleOption(NpcVisibleOption option) {
        this.visibility = option;
    }
    
    public NpcVisibleOption getVisibleOption() {
        return visibility;
    }
}