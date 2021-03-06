/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmocore.module.data.npcs;

import com.mmocore.MMOCore;
import com.mmocore.api.DialogAPI;
import com.mmocore.api.NpcFactionAPI;
import com.mmocore.api.UniverseAPI;
import com.mmocore.constants.DialogChatColor;
import com.mmocore.constants.DialogConversationOption;
import com.mmocore.constants.NpcModifier;
import com.mmocore.constants.NpcMovementAnimation;
import com.mmocore.constants.NpcProjectile;
import com.mmocore.constants.NpcRotation;
import com.mmocore.constants.NpcSound;
import com.mmocore.constants.NpcSpawnMethod;
import com.mmocore.constants.NpcTexture;
import com.mmocore.constants.TextVisibleOption;
import com.mmocore.constants.uPosition;
import com.mmocore.module.Dialog.RegisterableDialog;
import com.mmocore.module.Npc.RegisterableNpc;
import com.mmocore.module.Npc.loadout.NpcHeldItemSet;
import com.mmocore.module.Npc.loadout.NpcItem;
import com.mmocore.module.Npc.options.NpcBaseOptions;
import com.mmocore.module.Npc.options.NpcCombatOptions;
import com.mmocore.module.Npc.options.NpcInteractOptions;
import com.mmocore.module.Npc.options.NpcMovementOptions;
import com.mmocore.module.data.AbstractDictionary;

/**
 *
 * @author draks
 */
public class WalterHarriman extends RegisterableNpc {
    
    public WalterHarriman() {
        super(  "Walter Harriman",
                "Chief Master Sergeant",
                NpcTexture.TAURI_WALTER_HARRIMAN,
                NpcModifier.RANGED_COMMANDER,
                NpcSpawnMethod.Static,
                NpcFactionAPI.getRegistered("Stargate Command")
        );
        
        NpcHeldItemSet weapons = this.getRangedHeldItems();
        NpcItem heldItem = new NpcItem("flansmod", "m9", 1, 0);
        NpcBaseOptions options = this.getBaseOptions();
        uPosition spawnPos = new uPosition(-132.0,8.0,-619.0, UniverseAPI.getDimensionByDisplayName("P2X-3YZ"));
        options.setBossBarVisible(TextVisibleOption.WhenAttacking);
        options.setSpawnMethod(NpcSpawnMethod.Static);
        options.setSpawnPosition(spawnPos);
        NpcMovementOptions opts = this.getMovementOptions();
        opts.setRotation(NpcRotation.WEST);
        opts.setMovingAnimation(NpcMovementAnimation.Sitting);
        this.setMovementOptions(opts);
        this.setBaseOptions(options);
        weapons.setMainHand(heldItem);
        this.setRangedHeldItems(weapons);
        NpcCombatOptions cOpts = this.getCombatOptions();
        cOpts.setFireWeaponSound(NpcSound.M9);
        cOpts.setProjectile(NpcProjectile.PISTOL_BULLET);
        this.setCombatOptions(cOpts);
        NpcInteractOptions interactOptions = this.getInteractOptions();
        interactOptions.addInteractLine("I haven't got anything for you at the moment...");
        this.setInteractOptions(interactOptions);
    }
}
