/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmocore.module.GameEvent.events;

import com.mmocore.MMOCore;
import com.mmocore.api.ForgeAPI;
import com.mmocore.api.NpcAPI;
import com.mmocore.api.PlayerAPI;
import com.mmocore.api.UniverseAPI;
import com.mmocore.constants.ConsoleMessageType;
import com.mmocore.constants.NpcSpawnMethod;
import com.mmocore.constants.RandomSpawnMode;
import com.mmocore.constants.uPosition;
import com.mmocore.module.Dimension.RegisterableDimension;
import com.mmocore.module.GameEvent.GameEvent;
import com.mmocore.module.Npc.RegisterableNpc;
import com.mmocore.module.GameEvent.events.options.SpawnEventOptions;
import com.mmocore.module.Player.RegisterablePlayer;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author draks
 */
public class RandomSpawnEvent extends GameEvent {
    
    private ArrayList<RegisterableNpc> storedNpcs = new ArrayList<RegisterableNpc>();
    private SpawnEventOptions randomOptions = new SpawnEventOptions();
    
    public RandomSpawnEvent(String name) {
        super(name);
    }
    
    private boolean shouldSpawn(uPosition position) {
        if (this.storedNpcs.isEmpty()) return false;
        if (!getOptions().spawnEnabled()) return false;        
        if (!getOptions().chancePassed()) return false;
        if (getOptions().getRequiresAtmosphere() && !position.getDimension().hasAtmopshere()) return false;
        if (!getOptions().getSpawnDimensions().isEmpty() && !getOptions().getSpawnDimensions().contains(position.getDimension())) return false;
        if (!getOptions().getSpawnFactions().isEmpty() && !getOptions().getSpawnFactions().contains(position.getDimension().getFaction())) return false;
        if (getOptions().getDimensionDensity() < NpcAPI.getAllReadOnlyCreatedBy(position.getDimension(), this.getClass()).size()) return false;
        if (!getOptions().getSpawnsOnContestedWorlds() && position.getDimension().getFaction() == null) return false;
        if (!getOptions().getSpawnFactions().isEmpty() && position.getDimension().getFaction() != null && !getOptions().getSpawnFactions().contains(position.getDimension().getFaction())) return false;
        return true;
    }
    
    private boolean spawn(uPosition position) {
        if (!this.shouldSpawn(position)) return false;            
        uPosition origSpawnPos = UniverseAPI.getRandomNearbyPosition(position, 64, 128);
        if (getOptions().getMode().equals(RandomSpawnMode.SingleFromGroup)) {
            if (origSpawnPos == null) {
                return false;
            }
            RegisterableNpc toSpawn = getRandomNpc();
            RegisterableNpc actualSpawner = NpcAPI.simpleClone(toSpawn, NpcSpawnMethod.Random, origSpawnPos);
            actualSpawner.setCreator(this.getClass().getName());
            MMOCore.getNpcRegistry().register(actualSpawner);
            return true;
        } else {
            uPosition actualSpawnPos = UniverseAPI.getRandomNearbyPosition(origSpawnPos, getOptions().getMinSpawnSpread(), getOptions().getMaxSpawnSpread());
            uPosition prevSpawnPos = actualSpawnPos;
            for (RegisterableNpc npc : storedNpcs) {                
                actualSpawnPos = UniverseAPI.getRandomNearbyPosition(prevSpawnPos, getOptions().getMinSpawnSpread(), getOptions().getMaxSpawnSpread());
                if (actualSpawnPos == null) {
                    continue;
                }
                RegisterableNpc toSpawn = NpcAPI.simpleClone(npc, NpcSpawnMethod.Random, actualSpawnPos);
                toSpawn.setCreator(this.getClass().getName());
                MMOCore.getNpcRegistry().register(toSpawn);
                prevSpawnPos = actualSpawnPos;
            }
            return true;
        }
    }
    
    public void addNpc(RegisterableNpc npc) {
        if (!this.storedNpcs.contains(npc)) this.storedNpcs.add(npc);
    }
    
    public void removeNpc(RegisterableNpc npc) {
        if (this.storedNpcs.contains(npc)) this.storedNpcs.remove(npc);
    }
    
    public SpawnEventOptions getOptions() {
        return this.randomOptions;
    }
    
    private RegisterableNpc getRandomNpc() {
        Random r = new Random();
        RegisterableNpc npc = NpcAPI.clone(storedNpcs.get(r.nextInt(storedNpcs.size())));
        return npc;
    }
    
    public void setOptions(SpawnEventOptions options) {
        this.randomOptions = options;
    }

    @Override
    public void tickForDimension(RegisterableDimension dimension) {
        for (RegisterablePlayer p : PlayerAPI.getForDimension(dimension)) {
            spawn(p.getPosition());
        }
    }

    @Override
    public boolean ticksForDimension(RegisterableDimension dimension) {
        return getOptions().getSpawnDimensions().contains(dimension);
    }

    @Override
    public void cleanup() {
        // This event doesnt clean anything up, Random Npcs despawn naturally.
    }
    
}
