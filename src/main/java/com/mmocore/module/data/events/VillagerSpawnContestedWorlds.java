/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmocore.module.data.events;

import com.mmocore.MMOCore;
import com.mmocore.api.UniverseAPI;
import com.mmocore.module.Dimension.RegisterableDimension;
import com.mmocore.module.GameEvent.GameEvent;
import com.mmocore.module.GameEvent.events.RandomSpawnEvent;
import com.mmocore.module.GameEvent.events.VillagerReplacementEvent;
import com.mmocore.module.GameEvent.events.options.SpawnEventOptions;
import com.mmocore.module.Npc.RegisterableNpc;
import com.mmocore.module.data.factions.*;
import com.mmocore.module.NpcFaction.RegisterableNpcFaction;
import com.mmocore.module.data.AbstractDictionary;
import com.mmocore.module.data.npcs.mobs.GoauldSoldier;
import com.mmocore.module.data.npcs.mobs.TauriSoldier;
import java.util.ArrayList;

/**
 *
 * @author draks
 */
public class VillagerSpawnContestedWorlds extends VillagerReplacementEvent {

    public VillagerSpawnContestedWorlds() {
            super("Villager Spawn - Uncontrolled Worlds");
            this.addNpc(AbstractDictionary.getNpcByName("SGC Soldier", "Stargate Command"));
            this.addNpc(AbstractDictionary.getNpcByName("Jaffa Soldier", "Goauld Loyal Jaffa"));
            SpawnEventOptions options = this.getOptions();
            for (RegisterableDimension dimension : UniverseAPI.getDimensions()) {
                if (dimension.getFaction() == null) options.addSpawnDimension(dimension);
            }
            options.setDimensionDensity(20);
            this.setOptions(options);
    }
}
