package com.coremetrics.orion.tools.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CreateFolders {
    public static void main(String[] args)
    {   
        File srcFolder = new File("/Users/amrutha/Desktop/deep_learning/jpg");

        
        //make sure source exists
        if(!srcFolder.exists()){

           System.out.println("Directory does not exist.");
           //just exit
           System.exit(0);

        }else{

           try{
               String files[] = srcFolder.list();
               int i = 1;
               copyFolder(srcFolder, files);   
           }catch(IOException e){
            e.printStackTrace();
            //error, just exit
                System.exit(0);
           }
        }
        
        System.out.println("Done");
    }
    
    private static void copyFile(File src, File dest) throws IOException {
        //if file, then copy it
        //Use bytes stream to support all file types
        InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest); 
                         
            byte[] buffer = new byte[1024];
        
            int length;
            //copy the file content in bytes 
            while ((length = in.read(buffer)) > 0){
               out.write(buffer, 0, length);
            }

            in.close();
            out.close();
            System.out.println("File copied from " + src + " to " + dest);        
    }
    
    public static void copyFolder(File src, String files[])
        throws IOException{
        
        if(src.isDirectory()){
            int dirCount = 1;   
            String dirString = "";
            int fileC = 0;
           while(true) {
                if ((fileC%80) == 0) {
                    dirString = "/Users/amrutha/Desktop/deep_learning/"+"category_"+dirCount;
                    dirCount++;
                }
                if (dirCount == 19) {
                    break;
                }
                File destFolder = new File(dirString); 
                
                if(!destFolder.exists()){
                    destFolder.mkdir();
                    System.out.println("Directory copied from " 
                                   + src + "  to " + destFolder);
                 }
                String s = String.format("%04d", fileC+1);                 
                //File srcFile = new File(src, file);
                File srcFile = new File(src, "image_"+s+".jpg");
                File destFile = new File(destFolder, "image_"+s+".jpg");
                
                copyFile(srcFile,destFile);
                fileC++;
            }
        }
    }
}
