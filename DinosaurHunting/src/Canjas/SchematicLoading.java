package Canjas;

import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.world.block.BlockState;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SchematicLoading {
    private final String path;

    public SchematicLoading(String path) {
        this.path = path;
    }

    public Clipboard load() {
        File file = new File(path);
        ClipboardFormat format = ClipboardFormats.findByFile(file);
        Clipboard clipboard;
        try {
            ClipboardReader reader = format.getReader(new FileInputStream(file));
            clipboard = reader.read();
            for (int x = clipboard.getMinimumPoint().getX(); x <= clipboard.getMaximumPoint().getX(); x++) {
                for (int y = clipboard.getMinimumPoint().getY(); y <= clipboard.getMaximumPoint().getY(); y++) {
                    for (int z = clipboard.getMinimumPoint().getZ(); z <= clipboard.getMaximumPoint().getZ(); z++) {
                        BlockState block = clipboard.getBlock(BlockVector3.at(x, y, z));
                        if (block.getBlockType().toString().equals("minecraft:smooth_sandstone") || block.getBlockType().toString().equals("minecraft:bone_block") ||  block.getBlockType().toString().equals("minecraft:smooth_sandstone_stairs"))
                            continue;
                    }
                }
            }
            return clipboard;
        } catch (IOException ignored) {
            System.out.println("CANT LOAD SCHEMATIC");
        }
        return null;
    }
}
