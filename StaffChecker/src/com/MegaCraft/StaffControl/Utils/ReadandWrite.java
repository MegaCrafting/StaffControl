package com.MegaCraft.StaffControl.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.MegaCraft.StaffControl.StaffControl;

public class ReadandWrite {	
	
	public static void writer(String player, String message, String time, String location) {
		try {
			File saveTo = new File(StaffControl.plugin.getDataFolder().toString() + "/" + location + "/", player + ".txt");

			FileWriter fw = new FileWriter(saveTo, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println( message + " at [" + time + "]");
			pw.flush();
			pw.close();

		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void reader(CommandSender sender, String player, String location) {
		File saveTo = new File(StaffControl.plugin.getDataFolder().toString() + "/" + location + "/", player + ".txt");
		try {
            byte[] buffer = new byte[1000];

            FileInputStream inputStream =  new FileInputStream(saveTo);

            int total = 0;
            int nRead = 0;
            while((nRead = inputStream.read(buffer)) != -1) {
                System.out.println(new String(buffer));
                sender.sendMessage(new String(buffer));
                total += nRead;
            }   
            inputStream.close();        

            System.out.println("Read " + total + " bytes");
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                		saveTo + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + saveTo + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    }
}

