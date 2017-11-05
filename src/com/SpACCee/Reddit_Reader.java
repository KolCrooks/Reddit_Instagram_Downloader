package com.SpACCee;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class Reddit_Reader {

    //The url of the website. This is just an example
    private static String webSiteURL = "https://www.reddit.com/r/rotmg";

    //The path of the folder that you want to save the images to
    private static String folderPath = "C:\\Users\\kol\\Desktop\\test\\";


    public Reddit_Reader(String webURL, String foldePath, int numPages){
        if(!webSiteURL.equalsIgnoreCase("")) this.webSiteURL = webURL;
        if(!folderPath.equalsIgnoreCase("")) this.folderPath = foldePath;


        Document page;


        try{

            int cont = 0;

            //fetching the document from the URL in html
            page = Jsoup.connect(webSiteURL).get();
            while(cont < numPages){

                //selecting all the elements whose urls end with "jpg" or "png"
                Elements images = page.select("a[href$=jpg], a[href$=png]");
                for (Element link : images) {
                    if(link.attr("href").endsWith("jpg")) {
                        getImages(link.attr("href"), folderPath + "\\" + link.text() + ".jpg");
                    }else{
                        getImages(link.attr("href"), folderPath + "\\" + link.text() + ".png");
                    }
                }
                String tempS = "";
                for (int i = 0; (i = page.toString().indexOf("https://i.redd.it/", i + 1)) != -1; i++) {
                    int temp = i;

                    tempS = "";


                    while(true){
                        if(page.toString().toCharArray()[temp] == '\"') break;
                            tempS += page.toString().toCharArray()[temp];
                            temp++;
                    }

                    if(tempS.length() >= 50){
                        tempS = "";
                        temp = i;
                        while(true){
                            if(page.toString().toCharArray()[temp] == '&') break;
                            tempS += page.toString().toCharArray()[temp];
                            temp++;
                        }
                    }

                    System.out.println(tempS);
                    String link = "";
                    temp = 0;
                    int i2 = 0;
                    while(i2 < 3){
                        if(tempS.toCharArray()[temp] == '/') {
                            i2++;
                        }

                        if(i2 < 3) temp++;
                    }

                    for(int ii = temp; ii < tempS.length(); ii++){
                        link += tempS.toCharArray()[ii];
                    }
                    getImages(tempS, folderPath + "\\" + link);

                }




                //moving to next page
                Element next = page.select("a[rel = nofollow next]").first();
                String nextLink = next.attr("href").toString();
                page = Jsoup.connect(nextLink).get();
                cont = cont + 25;

            }

            System.out.println("Download complete.");
        }

        catch(IOException e){
            e.printStackTrace();

        }




    }

    private static void getImages(String src, String pth) throws IOException {

        //Exctract the name of the image from the src attribute



        //Open a URL Stream
        URL url = new URL(src);

        URLConnection uc;

        uc = url.openConnection();
        uc.connect();

        uc = url.openConnection();
        uc.addRequestProperty("User-Agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
        InputStream in = uc.getInputStream();

        OutputStream out = new BufferedOutputStream(new FileOutputStream(pth));

        for (int b; (b = in.read()) != -1;) {
            out.write(b);
        }
        out.close();
        in.close();

    }

}
