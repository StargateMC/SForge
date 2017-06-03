/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmocore.module.GameEvent;

import com.mmocore.MMOCore;
import com.mmocore.module.Galaxy.*;
import com.mmocore.api.ForgeAPI;
import com.mmocore.api.UniverseAPI;
import com.mmocore.constants.ConsoleMessageType;
import com.mmocore.constants.NpcModifier;
import com.mmocore.constants.NpcSpawnMethod;
import com.mmocore.constants.NpcTexture;
import com.mmocore.module.AbstractRegistry;
import com.mmocore.module.Dimension.RegisterableDimension;
import com.mmocore.module.GameEvent.events.RandomSpawnEvent;
import com.mmocore.module.GameEvent.events.options.RandomSpawnEventOptions;
import com.mmocore.module.Npc.RegisterableNpc;
import com.mmocore.module.NpcFaction.RegisterableNpcFaction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author draks
 */
public class GameEventRegistry extends AbstractRegistry<GameEventRegistry, String, GameEvent> {

    @Override
    public void initialise() {
        if (ForgeAPI.isServer()) {
            RegisterableNpcFaction faction = new RegisterableNpcFaction("Test");
            faction.setFriendlyPoints(1499);
            faction.setDefaultPoints(1500);
            MMOCore.getNpcFactionRegistry().register(faction);
            ArrayList<RegisterableNpc> npcs = new ArrayList<RegisterableNpc>();
            RegisterableNpc npc = new RegisterableNpc("Everett Young", "Commander of the Destiny", NpcTexture.TAURI_EVERETT_YOUNG, NpcModifier.RANGED_COMMANDER, NpcSpawnMethod.Static, faction);
            npcs.add(npc);
            RandomSpawnEvent gameEvent = new RandomSpawnEvent("Test Random Spawn", npcs);
            RandomSpawnEventOptions options = gameEvent.getOptions();
            options.addSpawnDimension(UniverseAPI.getDimension("P2X-3YZ"));
            gameEvent.setOptions(options);
            this.register(gameEvent);
        }
        // Not required.
    }
    
    public void tickForDimension(RegisterableDimension dimension) {
        Collection<GameEvent> events = this.getRegistered().values();
        for (GameEvent event : events) {
            ForgeAPI.sendConsoleEntry("Ticking event : " + event.getIdentifier() + " for world : " + dimension.getName(), ConsoleMessageType.FINE);
            event.tickForDimension(dimension);
        }
    }
    
    @Override
    public void finalise() {
        // No code required, yet.
    }

    @Override
    public boolean canBeEnabled() {
        return true;
    }

}
