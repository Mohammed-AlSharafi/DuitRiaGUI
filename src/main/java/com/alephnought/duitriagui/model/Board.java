/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alephnought.duitriagui.model;

public class Board {

    public static Cell[] cells = {
            new Cell("Go", 0, 732, 659),
            new Cell("Petaling Street", 600000, 625, 659),
            new Cell("Fate", 0, 569, 659),
            new Cell("Jonker Street", 600000, 513, 659),
            new Cell("Tax", 0, 458, 659),
            new Cell("KLIA", 2000000, 403, 659),
            new Cell("Masjid Jamek", 1000000, 348, 659),
            new Cell("Fate", 0, 293, 659),
            new Cell("Batu Caves", 1000000, 238, 659),
            new Cell("Sri Mahamariamman Temple", 1200000, 183, 659),
            new Cell("Jail", 0, 76, 659),
            new Cell("National Museum", 1400000, 76, 577),
            new Cell("Tenaga National Berhad", 1500000, 76, 522),
            new Cell("Royal Palace", 1400000, 76, 471),
            new Cell("Merdeka Square", 1400000, 76, 419),
            new Cell("KLIA2", 2000000, 76, 363),
            new Cell("A Famosa Fort", 1700000, 76, 312),
            new Cell("Fate", 0, 76, 258),
            new Cell("Kellie Castle", 1800000, 76, 205),
            new Cell("Stadthuys", 2000000, 76, 151),
            new Cell("Parking", 0, 76, 60),
            new Cell("Fraser's Hill", 2200000, 183, 60),
            new Cell("Fate", 0, 238, 60),
            new Cell("Cameron Highlands", 2200000, 293, 60),
            new Cell("Genting Highlands", 2400000, 348, 60),
            new Cell("KL Sentral Station", 2000000, 403, 60),
            new Cell("Pahang National Park", 2600000, 458, 60),
            new Cell("Jabatan Bekalan Air", 1500000, 513, 60),
            new Cell("Gunung Mulu National Park", 2600000, 569, 60),
            new Cell("Kinabalu National Park", 2700000, 625, 60),
            new Cell("Go To Jail", 0, 732, 60),
            new Cell("Tioman Islands", 3000000, 732, 151),
            new Cell("Perhentian Islands", 3000000, 732, 205),
            new Cell("Fate", 0, 732, 258),
            new Cell("Sepadan Islands", 3200000, 732, 312),
            new Cell("Pudu Sentral Station", 2000000, 732, 363),
            new Cell("Fate", 0, 732, 419),
            new Cell("KLCC", 3500000, 732, 471),
            new Cell("Tax", 0, 732, 522),
            new Cell("Sepang II Circuit", 4000000, 732, 577)
    };
    static Set[] sets = {
        new Set("Green", new Cell[]{cells[1], cells[3]}),
        new Set("Blue", new Cell[]{cells[6], cells[8], cells[9]}),
        new Set("Red", new Cell[]{cells[11], cells[13], cells[14]}),
        new Set("Baby Blue", new Cell[]{cells[16], cells[18], cells[19]}),
        new Set("Purple", new Cell[]{cells[21], cells[23], cells[24]}),
        new Set("Orange", new Cell[]{cells[26], cells[28], cells[29]}),
        new Set("Red", new Cell[]{cells[31], cells[32], cells[34]}),
        new Set("Yellow", new Cell[]{cells[37], cells[39]})
    };

}
