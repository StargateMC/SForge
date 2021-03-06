/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmocore.module.data;

import com.mmocore.api.DictionaryAPI;
import com.mmocore.module.AbstractRegisterable;
import com.mmocore.module.Dialog.RegisterableDialog;
import com.mmocore.module.GameEvent.GameEvent;
import com.mmocore.module.Npc.RegisterableNpc;
import com.mmocore.module.NpcFaction.RegisterableNpcFaction;
import com.mmocore.module.Quest.RegisterableQuest;
import com.mmocore.module.data.dialogs.GeneralHammond.LetsGetStarted;
import com.mmocore.module.data.dialogs.GeneralHammond.WelcomeToTheSGC;
import com.mmocore.module.data.dialogs.GeneralHammond.WhereAmI;
import com.mmocore.module.data.dialogs.JaffaMarketeer.JaffaMarketeerIntro;
import com.mmocore.module.data.dialogs.JanetFraser.JanetFraserWelcome;
import com.mmocore.module.data.events.LocStargateCommand;
import com.mmocore.module.data.events.RandomSpawnCommanders;
import com.mmocore.module.data.events.RandomSpawnContestedWorlds;
import com.mmocore.module.data.events.RandomSpawnSnipers;
import com.mmocore.module.data.events.StargateCommandMarines;
import com.mmocore.module.data.events.SGCInfirmary;
import com.mmocore.module.data.events.VillagerSpawnContestedWorlds;
import com.mmocore.module.data.factions.BountyHunters;
import com.mmocore.module.data.factions.Goauld;
import com.mmocore.module.data.factions.JaffaMarketeers;
import com.mmocore.module.data.factions.StargateCommand;
import com.mmocore.module.data.npcs.GeneralHammond;
import com.mmocore.module.data.npcs.mobs.GoauldCommander;
import com.mmocore.module.data.npcs.mobs.GoauldSoldier;
import com.mmocore.module.data.npcs.JaffaMarketeer;
import com.mmocore.module.data.npcs.JanetFraser;
import com.mmocore.module.data.npcs.MarcusBell;
import com.mmocore.module.data.npcs.mobs.TauriSoldier;
import com.mmocore.module.data.npcs.WalterHarriman;
import com.mmocore.module.data.npcs.mobs.TauriCommander;
import com.mmocore.module.data.npcs.mobs.TauriSniper;
import com.mmocore.module.data.quests.VisitingTheInfirmary;
import java.util.ArrayList;

/**
 *
 * @author draks
 */
public class AbstractDictionary {
    
    private static ArrayList<AbstractRegisterable> objects = new ArrayList<AbstractRegisterable>();   

    public static void add(AbstractRegisterable object) {
     if (!objects.contains(object)) objects.add(object);
    }
    
    public static ArrayList<AbstractRegisterable> getAll() {
        return objects;
    }
    
    public static ArrayList<RegisterableNpcFaction> getFactions() {
        return getNpcFactions();
    }
    
    public static ArrayList<RegisterableQuest> getQuests() {
        return loadQuests();
    }
    
    public static ArrayList<GameEvent> getEvents() {
        return getGameEvents();
    }
            
    public static ArrayList<RegisterableNpc> getNpcs() {
        return getAllNpcs();
    }
    
    public static ArrayList<RegisterableDialog> getDialogs() {
        return loadDialogs();
    }
    
    public static RegisterableDialog getDialogByName(String title, String category) {
        for (AbstractRegisterable registered : getAll()) {
            if (registered instanceof RegisterableDialog) {
                RegisterableDialog dialog = (RegisterableDialog)registered;
                if (dialog.getBaseOptions().getTitle().equals(title) && dialog.getBaseOptions().getCategory().equals(category)) return dialog;
            }
        }
        return null;
    }
    
    public static RegisterableNpc getNpcByName(String name, String title) {
        for (AbstractRegisterable registered : getAllNpcs()) {
            if (registered instanceof RegisterableNpc) {
                RegisterableNpc npc = (RegisterableNpc)registered;
                if (npc.getBaseOptions().getName().equals(name) && npc.getBaseOptions().getTitle().equals(title)) return npc;
            }
        }
        return null;
    }
    
    public static GameEvent getEventByName(String name) {
        for (AbstractRegisterable registered : getGameEvents()) {
            if (registered instanceof GameEvent) {
                GameEvent event = (GameEvent)registered;
                if (event.getIdentifier().equals(name)) return event;
            }
        }
        return null;
    }
    
    public static RegisterableQuest getQuestByName(String title, String category) {
        for (AbstractRegisterable registered : getAll()) {
            if (registered instanceof RegisterableQuest) {
                RegisterableQuest quest = (RegisterableQuest)registered;
                if (quest.getBaseOptions().getTitle().equals(title) && quest.getBaseOptions().getQuestChain().equals(category)) return quest;
            }
        }
        return null;
    }
    
    public static ArrayList<RegisterableDialog> loadDialogs() {
        ArrayList<RegisterableDialog> dialogs = new ArrayList<RegisterableDialog>();
        dialogs.add(new LetsGetStarted());
        dialogs.add(new WelcomeToTheSGC());
        dialogs.add(new WhereAmI());
        dialogs.add(new JanetFraserWelcome());
        dialogs.add(new JaffaMarketeerIntro());
        return dialogs;
    }

    public static ArrayList<RegisterableNpcFaction> getNpcFactions() {
        ArrayList<RegisterableNpcFaction> factions = new ArrayList<RegisterableNpcFaction>();
        factions.add(new Goauld());
        factions.add(new StargateCommand());
        factions.add(new JaffaMarketeers());
        factions.add(new BountyHunters());
        return factions;
    }

    public static ArrayList<RegisterableNpc> getAllNpcs() {
        ArrayList<RegisterableNpc> npcs = new ArrayList<RegisterableNpc>();
        npcs.add(new GeneralHammond());
        npcs.add(new TauriSoldier());
        npcs.add(new GoauldCommander());
        npcs.add(new GoauldSoldier());
        npcs.add(new TauriCommander());
        npcs.add(new JanetFraser());
        npcs.add(new MarcusBell());
        npcs.add(new WalterHarriman());
        npcs.add(new JaffaMarketeer());
        npcs.add(new TauriSniper());
        return npcs;
    }
    
    public static ArrayList<GameEvent> getGameEvents() {
        ArrayList<GameEvent> events = new ArrayList<GameEvent>();
        events.add(new RandomSpawnContestedWorlds());
        events.add(new RandomSpawnCommanders());
        events.add(new LocStargateCommand());
        events.add(new SGCInfirmary());
        events.add(new StargateCommandMarines());
        events.add(new VillagerSpawnContestedWorlds());
        events.add(new RandomSpawnSnipers());
        return events;
    }

    public static ArrayList<RegisterableQuest> loadQuests() {
        ArrayList<RegisterableQuest> quests = new ArrayList<RegisterableQuest>();
        quests.add(new VisitingTheInfirmary());
        return quests;
    }
}
