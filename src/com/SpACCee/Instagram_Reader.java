package com.SpACCee;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.URL;

public class Instagram_Reader {

    //The path of the folder that you want to save the images to
    private static String folderPath = "C:\\Users\\kol\\Desktop\\test\\";

    static int counter = 0;

    public Instagram_Reader(String webSiteURL, String folderPath){
    if(folderPath != null) this.folderPath = folderPath;


        try {

            //Connect to the website and get the html
            Document doc = Jsoup.connect(webSiteURL).get();

            int cnt2 = 0;
            for (int i = 0; (i = doc.toString().indexOf("display_src", i + 1)) != -1; i++) {
                cnt2++;
            }
            System.out.println("Found " + cnt2 + " Images");


            for (int i = 0; (i = doc.toString().indexOf("display_src", i + 1)) != -1; i++) {
                int internal = i;

                int i2 = 0;
                while(i2 < 2){
                    if(doc.toString().toCharArray()[internal] == '\"') {
                        i2++;
                    }
                    if(i2 < 2) internal++;
                }

                String link = "";

                while(true){
                    internal++;
                    if(doc.toString().toCharArray()[internal] == '\"')
                        break;

                    link += doc.toString().toCharArray()[internal];

                }
                System.out.println(link);


                getImages(link);
            }


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void getImages(String src) throws IOException {

        //Exctract the name of the image from the src attribute

        counter++;

        //Open a URL Stream
        URL url = new URL(src);
        InputStream in = url.openStream();

        OutputStream out = new BufferedOutputStream(new FileOutputStream( folderPath + "\\" + counter + ".jpg"));

        for (int b; (b = in.read()) != -1;) {
            out.write(b);
        }
        out.close();
        in.close();

    }


}
