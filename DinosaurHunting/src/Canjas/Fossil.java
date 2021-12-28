package Canjas;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Fossil {
    public Fossil() {}

    public String getName(int sec) {
        if (sec == 3048)
            return ChatColor.GOLD + "[Legendaire] Spinosaur";    //good
        if (sec == 3454)
            return ChatColor.GOLD + "[Legendaire] Tyranosaurus-Rex";    //good
        if (sec == 2022)
            return ChatColor.DARK_PURPLE + "[Epique] Ceratosaurus";     //good
        if (sec == 1450)
            return ChatColor.DARK_PURPLE + "[Epique] Crane de Mosasaur";  //good
        if (sec == 2055)
            return ChatColor.DARK_PURPLE + "[Epique] Suchominus";       //good
        if (sec == 2617)
            return ChatColor.DARK_PURPLE + "[Epique] Saurophaganax";    //good
        if (sec == 786)
            return ChatColor.YELLOW + "[Rare] Pterodactyle";        // good
        if (sec == 2974)
            return ChatColor.YELLOW + "[Rare] Stegosaur";            //good
        if (sec == 2530)
            return ChatColor.YELLOW + "[Rare] Plesiosaur";          //good
        if (sec == 686)
            return ChatColor.YELLOW + "[Rare] Crane de Giganotosaurus"; //good
        if (sec == 1527)
            return ChatColor.GREEN + "[Rare] Carnotaurus";    //good
        if (sec == 528)
            return ChatColor.GREEN + "[Commun] Raptor";         //good
        if (sec == 2610)
            return ChatColor.GREEN + "[Commun] Triceratops";    //good
        if (sec == 1197)
            return ChatColor.YELLOW + "[Commun] Pachycephalosaurus";   //good
        if (sec == 1735)
            return ChatColor.GREEN + "[Commun] Parasaure";      //good
        if (sec == 2284)
            return ChatColor.GREEN + "[Commun] Nothronychus";   //good
        if (sec == 1204)
            return ChatColor.GREEN + "[Commun] Crane de Pentaceratops";  //good
        if (sec == 1179)
            return ChatColor.GREEN + "[Commun] Crane d'Agathaumas";     //good
        Bukkit.broadcastMessage(String.valueOf(sec));
        return "name";
    }
}
