package org.example;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

public class Main {
    final static String URL = "https://api.publicapis.org/";

    public static void main(String[] args) {
        String categories = "categories";
        String entries = "entries";

        // Get String
        RestAssured.baseURI = URL + categories;
        System.out.println("Get String");
        System.out.println(given()
                .get()
                .jsonPath()
                .getString("categories") + "\n");
        // Get integer
        System.out.println("Get Integer");
        System.out.println(given()
                .get()
                .jsonPath()
                .getInt("count") + "\n");

        //Get List
        System.out.println("Get List");
        given()
                .get()
                .jsonPath()
                .getList("categories")
                .forEach(System.out::println);

        //Get Object
        System.out.println("\nGet Object");
        RestAssured.baseURI = URL + entries;
        System.out.println(given()
                .get()
                .jsonPath()
                .getJsonObject("entries[11]")
                .toString() + "\n");

        //Get Map
        System.out.println("Get Map");
        given()
                .get()
                .jsonPath()
                .getMap("entries[50]").forEach((k, v) -> System.out.println(k + " =-> " + v));
    }
}
