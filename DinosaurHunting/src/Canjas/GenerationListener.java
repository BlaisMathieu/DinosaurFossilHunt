package Canjas;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;

public class GenerationListener implements Listener {

    @EventHandler
    public void onInit(WorldInitEvent event) {
        //faire un seul populator pour améliorer les performances

        event.getWorld().getPopulators().add(new SpinosaurPopulator());
        event.getWorld().getPopulators().add(new TyranosaurusPopulator());

        event.getWorld().getPopulators().add(new CeratosaurusPopulator());
        event.getWorld().getPopulators().add(new MosasaurPopulator());
        event.getWorld().getPopulators().add(new SuchominusPopulator());
        event.getWorld().getPopulators().add(new SaurophaganaxPopulator());

        event.getWorld().getPopulators().add(new PlesiosaurPopulator());
        event.getWorld().getPopulators().add(new PterodactylPopulator());
        event.getWorld().getPopulators().add(new StegosaurPopulator());
        event.getWorld().getPopulators().add(new GiganotosaurusPopulator());
        event.getWorld().getPopulators().add(new CarnotaurusPopulator());

        event.getWorld().getPopulators().add(new RaptorPopulator());
        event.getWorld().getPopulators().add(new TriceratopsPopulator());
        event.getWorld().getPopulators().add(new PachycephalosaurusPopulator());
        event.getWorld().getPopulators().add(new ParasaurePopulator());
        event.getWorld().getPopulators().add(new NothronychusPopulator());
        event.getWorld().getPopulators().add(new PentaceratopsPopulator());
        event.getWorld().getPopulators().add(new AgathaumasPopulator());
    }
}
