package it.polito.tdp.borders.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import it.polito.tdp.borders.db.BordersDAO;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();
		BordersDAO dao = new BordersDAO();
		System.out.println("TestModel -- TODO");
		
//		System.out.println("Creo il grafo relativo al 2000");
//		model.createGraph(2000);
		
//		List<Country> countries = model.getCountries();
//		System.out.format("Trovate %d nazioni\n", countries.size());

//		System.out.format("Numero componenti connesse: %d\n", model.getNumberOfConnectedComponents());
		
//		Map<Country, Integer> stats = model.getCountryCounts();
//		for (Country country : stats.keySet())
//			System.out.format("%s %d\n", country, stats.get(country));		
		
//		List<Country> countries = model.getVertici(2000);
//		for(Country c : countries) {
//			System.out.println(c.toString() + " " + c.getnStatiConfinanti());
//		}
//		System.out.println(""+countries.size());
//		
		model.creaGrafo(2000);
//		System.out.println("" + model.getComponentiConnesse());
		
		
		System.out.println("Gli stati raggiungibili da Austria"); 
		Country cc = dao.getCountry("Austria");
		if(model.getCountryRaggiungibili(cc).size() != 0) {
			List<Country> countries = model.getCountryRaggiungibili(dao.getCountry("Austria"));
			System.out.println("NON VUOTO");
			for(Country c : countries) {
				System.out.println(c.toString());
			}
		}
		else {
			System.out.println("VUOTO");
		}
		
		
	}

}
