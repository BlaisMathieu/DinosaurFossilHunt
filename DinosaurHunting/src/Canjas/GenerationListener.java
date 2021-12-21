package Canjas;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;

public class GenerationListener implements Listener {

    @EventHandler
    public void onInit(WorldInitEvent event) {
        //event.getWorld().getPopulators().add(new SpinosaurPopulator(1000000));
        //event.getWorld().getPopulators().add(new RaptorPopulator());
        //event.getWorld().getPopulators().add(new CeratosaurusPopulator());
        event.getWorld().getPopulators().add(new MosasaurPopulator());
    }
}
