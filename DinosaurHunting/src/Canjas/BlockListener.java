package Canjas;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlockListener implements Listener {
    private static final List<BlockFace> NEIGHBORS = Arrays.asList(BlockFace.UP,
            BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST,
            BlockFace.DOWN);
    private static final List<String> Pos = new ArrayList<>();
    private final DinosaurHunting plugin;
    boolean tmp = false;
    boolean hasFace = false;

    public BlockListener(DinosaurHunting dinosaurHunting) {
        this.plugin = dinosaurHunting;
    }

    public void loop(Block block) {
        Location loc = block.getLocation();
        String a = String.valueOf((int)loc.getX());
        String b = String.valueOf((int) loc.getY());
        String c = String.valueOf((int) loc.getZ());
        Pos.add(a + "." + b + "." + c);
        if (tmp) { return; }
        for (BlockFace neighbor: NEIGHBORS) {
            final Block face = block.getRelative(neighbor);
            loc = face.getLocation();
            a = String.valueOf((int)loc.getX());
            b = String.valueOf((int) loc.getY());
            c = String.valueOf((int) loc.getZ());
            if (!Pos.contains(a + "." + b + "." + c)) {
                if (face.getType() != Material.BEDROCK && face.getType() != Material.AIR && face.getType() != Material.SMOOTH_SANDSTONE && face.getType() != Material.SMOOTH_SANDSTONE_STAIRS && face.getType() != Material.BONE_BLOCK && face.getType() != Material.SMOOTH_SANDSTONE_SLAB) {
                    Bukkit.broadcastMessage(String.valueOf(face.getType()));
                    tmp = true;
                }
                if (face.getType() != Material.AIR)
                    loop(face);
            }
        }
    }

    @EventHandler
    public void reco(PlayerJoinEvent event) {
        event.getPlayer().setGameMode(GameMode.CREATIVE);
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {
        int sec = 1;
        Block block = event.getBlock();
        Player player = event.getPlayer();
        //Bukkit.broadcastMessage(String.valueOf(this.Pos.size()));
        if (block.getType() == Material.SMOOTH_SANDSTONE || block.getType() == Material.SMOOTH_SANDSTONE_STAIRS || block.getType() == Material.BONE_BLOCK || block.getType() == Material.SMOOTH_SANDSTONE_SLAB) {
            player.sendMessage(ChatColor.DARK_GREEN + "On dirait un fossile ... Il ne faudrait pas l'abîmer !");
            event.setCancelled(true);
            return;
        }
        Location loc = block.getLocation();
        String a = String.valueOf((int)loc.getX());
        String b = String.valueOf((int) loc.getY());
        String c = String.valueOf((int) loc.getZ());
        Pos.add(a + "." + b + "." + c);
        for (BlockFace neighborFace: NEIGHBORS) {
            final Block face = block.getRelative(neighborFace);
            if ((face.getType() == Material.SMOOTH_SANDSTONE || face.getType() == Material.SMOOTH_SANDSTONE_STAIRS || face.getType() == Material.BONE_BLOCK || face.getType() == Material.SMOOTH_SANDSTONE_SLAB) && !hasFace) {
                hasFace = true;
                loop(face);
                if (!tmp) {
                    Fossil fossil = new Fossil();
                    player.sendMessage(ChatColor.DARK_GREEN + "Le fossile semble prêt à être détéré... L'excavation va débuter !");
                    for (String s : Pos) {
                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                            public void run() {
                                String[] part = s.split("\\.");
                                double x = Double.parseDouble(part[0]);
                                double y = Double.parseDouble(part[1]);
                                double z = Double.parseDouble(part[2]);
                                Location location = new Location(Bukkit.getWorld("world"), x, y, z);
                                location.getBlock().setType(Material.AIR);
                            }
                        }, sec);
                        sec++;
                    }
                    int finalSec = sec;
                    plugin.getServer().getScheduler().runTaskLater(plugin, () -> player.sendMessage(ChatColor.DARK_GREEN + "L'éxcavation est terminé, vous avez acquéri un nouveau fossile de " + ChatColor.RED + fossil.getName(finalSec) + " !"), sec);
                    sec = 0;
                }
                else {
                    Bukkit.broadcastMessage("NON VALIDE");
                }
            }
        }
        tmp = false;
        hasFace = false;
        Pos.clear();
    }
}
