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
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class TyranosaurusPopulator extends BlockPopulator {

    private final String DinosaurName;
    private final int SpawnChance;
    private int count = 0;

    public TyranosaurusPopulator(String name, int chance) {
        this.DinosaurName = name;
        this.SpawnChance = chance;
    }

    public void populate(World world, Random random, Chunk chunk) {
        for (int x = 0; x < 16; x++) {
            for (int y = 1; y < 70; y++) {
                for (int z = 0; z < 16; z++) {
                    Block block = chunk.getBlock(x, y, z);
                    int r = (int) (Math.random() * 3000000);
                    if (r == 3 && block.getType().compareTo(Material.STONE) == 0) {
                        Location loc = block.getLocation();
                        try {
                            SchematicLoading schem = new SchematicLoading("C:\\Users\\Mathieu\\Desktop\\serv\\plugins\\WorldEdit\\schematics\\tyranosaurus.schem");
                            Clipboard clipboard = schem.load();
                            Bukkit.broadcastMessage(String.valueOf(count));
                            count = 0;
                            com.sk89q.worldedit.world.World adaptedWorld = BukkitAdapter.adapt(Bukkit.getServer().getWorld("world"));
                            EditSession session = WorldEdit.getInstance().getEditSessionFactory().getEditSession(adaptedWorld, -1);
                            Operation operation = new ClipboardHolder(clipboard).createPaste(session)
                                    .to(BlockVector3.at(loc.getX(), 65, loc.getZ())).ignoreAirBlocks(true).build();
                            Operations.complete(operation);
                            session.flushSession();
                        } catch (ArrayIndexOutOfBoundsException | WorldEditException ignored) {}
                    }
                }
            }
        }
    }
}

