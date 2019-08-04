package com.planetgallium.kitpvp.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.planetgallium.kitpvp.util.Config;
import com.planetgallium.kitpvp.util.Sounds;
import com.planetgallium.kitpvp.util.Toolkit;
import com.planetgallium.kitpvp.util.XMaterial;

public class SoupListener implements Listener {

	private int health = 6;
	
	@EventHandler
	public void onDamage(PlayerDeathEvent e) {

		Player victim = e.getEntity();
		
		if (victim.getKiller() != null && victim.getKiller() instanceof Player) {
			
			Player killer = victim.getKiller();
			
			if (Config.getB("GiveSoupOnKill.Enabled")) {
				
				if (killer.hasPermission("kp.soupreturn")) {
				
					int count = 0;
					for (int i = 0; i < 36; i++) {
						if (killer.getInventory().getItem(i) == null) {
							count++;
						}
					}
					
					if (count < Config.getI("GiveSoupOnKill.Amount")) {
						killer.sendMessage(Config.getS("GiveSoupOnKill.NoSpace"));
					}
					
					for (int r = 0; r < count; r++) {
						killer.getInventory().addItem(new ItemStack(XMaterial.MUSHROOM_STEW.parseItem()));
					}
					
				}
				
			}
			
		}
		
	}
	
	@EventHandler
	public void useSoup(PlayerInteractEvent e) {
	    
		if (Config.getB("Soups.Enabled")) {
			
			Player p = e.getPlayer();
			
			if (Toolkit.getMainHandItem(p).getType() == XMaterial.MUSHROOM_STEW.parseMaterial() || Toolkit.getOffhandItem(p).getType() == XMaterial.MUSHROOM_STEW.parseMaterial()) {
				
		        e.setCancelled(true);
		        
		        if (p.getHealth() < 20.0) {
		        	
		            p.setHealth(p.getHealth() + (double) health >= 20.0 ? 20.0 : p.getHealth() + (double) health);
		            p.playSound(p.getLocation(), Sounds.valueOf(Config.getS("Soups.Sound")).bukkitSound(), 1, (float) Config.getI("Soups.Pitch"));
		            
		        }
				
				if (Toolkit.getMainHandItem(p).getType() == XMaterial.MUSHROOM_STEW.parseMaterial()) {
					
		            if (Config.getB("Soups.RemoveAfterUse")) {
		            	
		            	Toolkit.setMainHandItem(p, new ItemStack(XMaterial.AIR.parseItem()));
		            	
		            } else {
		            	
		            	Toolkit.setMainHandItem(p, new ItemStack(XMaterial.BOWL.parseItem()));
		            	
		            }
			        
			    } else if (Toolkit.getOffhandItem(p).getType() == XMaterial.MUSHROOM_STEW.parseMaterial()) {
			    	
		            if (Config.getB("Soups.RemoveAfterUse")) {
		            	
		            	Toolkit.setOffhandItem(p, new ItemStack(XMaterial.AIR.parseItem()));
		            	
		            } else {
		            	
		            	Toolkit.setOffhandItem(p, new ItemStack(XMaterial.BOWL.parseItem()));
		            	
		            }
			    	
			    }
				
			}
			
		}
		
	}
	
}
