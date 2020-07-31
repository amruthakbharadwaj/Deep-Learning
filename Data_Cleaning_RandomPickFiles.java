package com.coremetrics.orion.tools.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class RandomPickFiles {
    public static void main(String[] args)
    {   
        File srcFolder = new File("/Users/umesh/Desktop/Umesh/deep_learning/jpg");

        
        //make sure source exists
        if(!srcFolder.exists()){

           System.out.println("Directory does not exist.");
           //just exit
           System.exit(0);

        }else{

           try{
               String files[] = srcFolder.list();
               int i = 1;
               copyFolder();   
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
    
    public static void copyFolder()
        throws IOException{
        for (int dirCount = 1; dirCount <= 17; dirCount++) {
            
            String srcString = "/Users/umesh/Desktop/Umesh/deep_learning/deep_learning_assignment_2/"
                    + ""+"category_"+dirCount;
            File srcFolder = new File(srcString);
            String files[] = srcFolder.list();
            
            int fileC = 1;
            
            String dirString = "/Users/umesh/Desktop/Umesh/deep_learning/deep_learning_assignment_2/"
                    + ""+"category_"+dirCount+"_training";
            
            File destFolder = new File(dirString); 
            
            if(!destFolder.exists()){
                destFolder.mkdir();
             }       
            
            for (String file : files) {
                
                File srcFile = new File(srcFolder, file);
                File destFile = new File(destFolder, file);
                
                copyFile(srcFile,destFile);    
                
                if ((fileC%64) == 0) {
                    dirString = "/Users/umesh/Desktop/Umesh/deep_learning/deep_learning_assignment_2/"
                            + ""+"category_"+dirCount+"_testing";
                    
                    destFolder = new File(dirString); 
                    
                    if(!destFolder.exists()){
                        destFolder.mkdir();
                     }                      
                }
                fileC++;
            }
        }
    }
}
