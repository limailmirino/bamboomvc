package it.bamboolab.utils;

import java.util.Random;

public class PasswordGenerator {

	private static String[] cities = {"rome",
		"milan",
		"naples",
		"turin",
		"palermo",
		"genoa",
		"bologna",
		"florence",
		"bari",
		"catania",
		"venice",
		"verona",
		"messina",
		"padua",
		"trieste",
		"taranto",
		"brescia",
		"prato",
		"modena",
		"parma",
		"perugia",
		"livorno",
		"ravenna",
		"cagliari",
		"foggia",
		"rimini",
		"salerno",
		"ferrara",
		"sassari",
		"syracuse",
		"pescara",
		"monza",
		"latina",
		"bergamo",
		"forlì",
		"trento",
		"vicenza",
		"terni",
		"novara",
		"bolzano",
		"piacenza",
		"ancona",
		"arezzo",
		"andria",
		"udine",
		"cesena",
		"lecce",
		"pesaro",
		"alessandria",
		"barletta",
		"catanzaro",
		"pistoia",
		"brindisi",
		"pisa",
		"como",
		"lucca",
		"pozzuoli",
		"treviso",
		"marsala",
		"grosseto",
		"busto arsizio",
		"varese",
		"casoria",
		"caserta",
		"gela",
		"asti",
		"ragusa",
		"cremona",
		"pavia",
		"fiumicino",
		"massa",
		"trapani",
		"aprilia",
		"cosenza",
		"altamura",
		"imola",
		"carpi",
		"potenza",
		"carrara",
		"viareggio",
		"fano",
		"afragola",
		"vigevano",
		"viterbo",
		"vittoria",
		"savona",
		"benevento",
		"crotone",
		"pomezia",
		"matera",
		"molfetta",
		"agrigento",
		"legnano",
		"cerignola",
		"foligno",
		"faenza",
		"sanremo",
		"tivoli",
		"bitonto",
		"avellino",
		"bagheria",
		"acerra",
		"olbia",
		"cuneo",
		"anzio",
		"modica",
		"teramo",
		"bisceglie",
		"ercolano",
		"siena",
		"chieti",
		"portici",
		"trani",
		"velletri",
		"acireale",
		"rovigo",
		"gallarate",
		"pordenone",
		"aversa",
		"battipaglia",
		"scafati",
		"chioggia",
		"scandicci",
		"collegno"

						};
	
	public static String generatePassword() {

        // select a random city from the list
		Random r = new Random();
		int high = cities.length;
		
		int rand = r.nextInt(high);
		
		String city = cities[rand];
		
		// select 2 characters at random to make upper case
		int rchar1 = r.nextInt(city.length());
		int rchar2 = rchar1;
		
		while (rchar1 == rchar2) {
			rchar2 = r.nextInt(city.length());
		}
		
		char[] chars = city.toCharArray();
		chars[rchar1] = Character.toUpperCase(chars[rchar1]);
		chars[rchar2] = Character.toUpperCase(chars[rchar2]);
		
		city = new String(chars);
		
		// add four random digits
		String rnum = "";
		for(int x = 1 ; x <= 4; x++) {
			rnum += Integer.toString(r.nextInt(10));
		}
		
		city += rnum;

		// add exclamation mark to the end
		city += "!";
		
		return city;
	}
}
