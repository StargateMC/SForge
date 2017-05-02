/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmocore.constants;

/**
 *
 * @author draks
 */

public enum NpcTexture{
    SGC_SOLDIER("customnpcs:textures/entity/stargate/Tauri/tauri_sg_team_member.png", NpcTextureType.Resource),
    WRAITH_SOLDIER("customnpcs:textures/entity/stargate/Wraith/wraith_soldier.png", NpcTextureType.Resource);

    private String value;
    private NpcTextureType type;
    
    NpcTexture(final String value, final NpcTextureType type) {
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }
    
    public NpcTextureType getType() {
        return this.type;
    }
    
    public String asTextureString() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}