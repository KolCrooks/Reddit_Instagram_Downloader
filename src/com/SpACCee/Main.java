package com.SpACCee;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        //Instagram or Reddit?
        System.out.println("-----------------------WARNING-----------------------");
        System.out.println(" THIS PROGRAM ONLY WORKS ON REDDIT OR INSTAGRAM PAGES");

        System.out.println("Input link: ");
        String website = sc.nextLine();


        System.out.println("Input save path: ");
        String path = sc.nextLine();

        if(website.toLowerCase().contains("https://www.reddit.com/".toLowerCase()) || website.toLowerCase().contains("http://www.reddit.com".toLowerCase())){
                System.out.println("Because you chose reddit... How many pages would you like to download?");
                int pgs = sc.nextInt();

                Reddit_Reader rr = new Reddit_Reader(website,path,pgs);
        }else if(website.toLowerCase().contains("https://www.instagram.com/".toLowerCase()) || website.toLowerCase().contains("https://www.instagram.com".toLowerCase())){
            Instagram_Reader ir = new Instagram_Reader(website,path);
        }else{
            System.out.println("YOU DIDN'T INPUT A LINK TO EITHER A REDDIT PAGE OR AN INSTAGRAM PAGE");
        }


    }
}
