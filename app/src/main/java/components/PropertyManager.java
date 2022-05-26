/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Saqsy
 */
public class PropertyManager {
    public static Properties properties = new Properties();
    public static File propFile = new File("config.properties");

    public static void propertyFileReader(){
        try (FileInputStream reader = new FileInputStream(propFile)){
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generatePropFile(){
        try {
            if (propFile.createNewFile()){
                System.out.println("prop file created");
            }else {
                System.out.println("error creating prop file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileOutputStream out = new FileOutputStream(propFile)){
            properties.setProperty("Project_ID", "ENTER_PROJECT_ID");
            properties.setProperty("Xray_Client_Id", "ENTER_CLIENT_ID");
            properties.setProperty("Xray_Client_Secret_Key", "ENTER_CLIENT_SECRET_KEY");
            properties.store(out,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
