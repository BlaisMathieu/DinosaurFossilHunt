package Canjas;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class PlesiosaurPopulator extends BlockPopulator {

    private int SpawnChance = 30000000;

    public PlesiosaurPopulator() {}

    public void generate(Block block) {
        Location loc = block.getLocation();
        try {
            SchematicLoading schem = new SchematicLoading("C:\\Users\\Mathieu\\Desktop\\serv\\plugins\\WorldEdit\\schematics\\GOODmosasaurhead.schem");
            Clipboard clipboard = schem.load();
            com.sk89q.worldedit.world.World adaptedWorld = BukkitAdapter.adapt(Bukkit.getServer().getWorld("world"));
            EditSession session = WorldEdit.getInstance().getEditSessionFactory().getEditSession(adaptedWorld, -1);
            Operation operation = new ClipboardHolder(clipboard).createPaste(session)
                    .to(BlockVector3.at(loc.getX(), 20, loc.getZ())).ignoreAirBlocks(false).build();
            Bukkit.broadcastMessage("Nouveau fossile de plesiosaur : " + loc.getX() + "   " + loc.getZ());
            Bukkit.broadcastMessage("Biome :" + block.getBiome());
            Operations.complete(operation);
            session.flushSession();
        } catch (ArrayIndexOutOfBoundsException | WorldEditException ignored) {
        }
    }

    public void populate(World world, Random random, Chunk chunk) {
        for (int x = 0; x < 16; x++) {
            for (int y = 1; y < 70; y++) {
                for (int z = 0; z < 16; z++) {
                    Block block = chunk.getBlock(x, y, z);
                    int r = (int) (Math.random() * 10000000);
                    if (r == 3 && block.getType().compareTo(Material.STONE) == 0) {
                        if (block.getBiome().equals(Biome.OCEAN) || block.getBiome().equals(Biome.COLD_OCEAN)
                                || block.getBiome().equals(Biome.DEEP_COLD_OCEAN) || block.getBiome().equals(Biome.DEEP_LUKEWARM_OCEAN)
                                || block.getBiome().equals(Biome.DEEP_OCEAN) || block.getBiome().equals(Biome.DEEP_WARM_OCEAN)
                                || block.getBiome().equals(Biome.LUKEWARM_OCEAN) || block.getBiome().equals(Biome.WARM_OCEAN))
                            generate(block);
                        else if (block.getBiome().equals(Biome.FROZEN_OCEAN) || block.getBiome().equals(Biome.DEEP_FROZEN_OCEAN)) {
                            int t = (int) (Math.random() * 3);
                            if (t == 1 && block.getType().compareTo(Material.STONE) == 0)
                                generate(block);
                        }
                        else return;
                    }
                }
            }
        }
    }
}
