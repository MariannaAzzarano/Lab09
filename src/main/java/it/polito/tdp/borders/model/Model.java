package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.event.ConnectedComponentTraversalEvent;
import org.jgrapht.event.EdgeTraversalEvent;
import org.jgrapht.event.TraversalListener;
import org.jgrapht.event.VertexTraversalEvent;
import org.jgrapht.graph.AsUnweightedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private BordersDAO dao;
	private Graph< Country, DefaultEdge> grafo;
	 

	public Model() {
		dao = new BordersDAO();
		
	}
	
	public void creaGrafo(Integer anno) {
		grafo = new SimpleGraph<>(DefaultEdge.class);
		
		Graphs.addAllVertices(grafo, this.getVertici(anno));
		List<Border> borders = dao.getCountryPairs(anno);
		for(Border b : borders) {
			this.grafo.addEdge(b.getC1(), b.getC2());
		}

//		System.out.println("GRAFO CREATO!");
//		System.out.println("# VERTICI: " + grafo.vertexSet().size());
//		System.out.println("# ARCHI: " + grafo.edgeSet().size());
	}
	
	public List<Country> getStatiConfinanti(Country c, Integer anno) {   //potrebbe servirmi per metodo della ricorsione
		List<Border> borders = dao.getCountryPairs(anno);
		List<Country> confiniStato = new ArrayList<Country>();
		for(Border b : borders) {
			if(b.getC1().equals(c)) {
				confiniStato.add(b.getC2());
			}
			else if(b.getC2().equals(c)) {
				confiniStato.add(b.getC1());
			}
		}
		return confiniStato;
	}
	


	
	public List<Country> getVertici(Integer anno){
		List<Border> borders = dao.getCountryPairs(anno);
		List<Country> countries = new ArrayList<Country>();
		for(Border b : borders) {
			if(!countries.contains(b.getC1())) {
				countries.add(b.getC1());
			}
			if(!countries.contains(b.getC2())) {
				countries.add(b.getC2());
			}
		}
		for(Country c : countries) {
			int numeroStatiConfinanti = 0;
			for(Border b : borders) {
				if(b.getC1().equals(c) || b.getC2().equals(c)) {
					numeroStatiConfinanti++ ;
				}
			}
			c.setnStatiConfinanti(numeroStatiConfinanti);
		}
		return countries;
	}
	
	public Integer getComponentiConnesse() {
		ConnectivityInspector<Country, DefaultEdge> conn = new ConnectivityInspector<>(grafo);
		List<Set<Country>> cc = conn.connectedSets();
		return cc.size();
	}
	
	public Integer getNumeroArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public Integer getNUmeroVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public Set<Country> getCountries(){
		return this.grafo.vertexSet();
	}
	
	
	
	//ESERCIZIO 2:
	
	//con BreadthFirstIterator
	public List<Country> getCountryRaggiungibili(Country partenza){
		BreadthFirstIterator<Country, DefaultEdge> bfv = new BreadthFirstIterator<Country, DefaultEdge>(grafo, partenza);
		List<Country> countries = new ArrayList<Country>();
		while(bfv.next() != null) {
			countries.add(bfv.next());
		}
		return countries;
	}
	

}
