package com.tryout;

/**
 * Created by patrickackerman on 1/6/17.
 */
    abstract class Writer {
        public static void write() {
            System.out.println("Writing...");
        }
    }
    class Author extends Writer {
        public static void write() {
            System.out.println("Writing book");
        } }


