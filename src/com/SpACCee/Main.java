package com.SpACCee;

import java.util.Scanner;

public class Main {


    static String[] params = new String[]{
            "-reddit",
            "-pages",
            "-account",
            "-subreddit",
            "-path"
    };
    //PARAMS: [-reddit || -instagram], [-pages [p]], [-account || -subreddit [account/subreddit]], [-path [p]]
    public static void main(String[] args){

        //if args are inputed, don't ask for input:
        Scanner sc = new Scanner(System.in);
        String website = null;
        String path = null;
        boolean reddit = false;
        int pages = 0;
        if(args.length > 0){

            for(int i = 0; i < args.length; i++){
                if(args[i].equalsIgnoreCase("-reddit")){
                    reddit = true;
                }else if(args[i].equalsIgnoreCase("-instagram")){
                    reddit = false;
                }else if(args[i].equalsIgnoreCase("-pgs")){
                    pages = Integer.parseInt(args[i+1]);
                    i++;
                }else if(args[i].equalsIgnoreCase("-account") || args[i].equalsIgnoreCase("-subreddit")){
                    website = args[i+1];
                    i++;
                }else if(args[i].equalsIgnoreCase("-path")){
                    path = args[i+1];
                    i++;
                }


            }


        }else{
            //Instagram or Reddit?
            System.out.println("-----------------------WARNING-----------------------");
            System.out.println(" THIS PROGRAM ONLY WORKS ON REDDIT OR INSTAGRAM PAGES");


            System.out.println("DOWNLOAD INSTAGRAM OR REDDIT?(I or R)");
            if(sc.nextLine().equalsIgnoreCase("r")){
                System.out.println("Input Subreddit: ");
                website = sc.nextLine();
                reddit = true;
                System.out.println("Because you chose reddit... How many pages would you like to download?");
                pages = sc.nextInt();
            }else{
                System.out.println("Input Instagram page: ");
                website = sc.nextLine();

            }

            System.out.println("Input save path: ");
            path = sc.nextLine();
        }






        if(reddit){


            Reddit_Reader rr = new Reddit_Reader("https://www.reddit.com/r/" + website,path,pages);
        }else if(!reddit){
            Instagram_Reader ir = new Instagram_Reader("https://www.instagram.com/" + website,path);
        }else{
            System.out.println("YOU DIDN'T INPUT A LINK TO EITHER A REDDIT PAGE OR AN INSTAGRAM PAGE");
        }


    }
}
