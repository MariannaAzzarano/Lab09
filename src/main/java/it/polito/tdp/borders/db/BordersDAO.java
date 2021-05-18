package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public List<Country> loadAllCountries() {

		String sql = "SELECT ccode, StateAbb, StateNme FROM country ORDER BY StateAbb";
		List<Country> result = new ArrayList<Country>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				System.out.format("%d %s %s\n", rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme"));
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Border> getCountryPairs(int anno) {
		String sql = "SELECT cc1.StateAbb, cc1.CCode, cc1.StateNme, cc2.StateAbb, cc2.CCode, cc2.StateNme "
				+ "FROM contiguity c, country cc1, country cc2 "
				+ "WHERE YEAR <= ? AND conttype = 1 AND "
				+ "(c.state1no = cc1.CCode AND c.state2no = cc2.CCode)";
		List<Border> result = new ArrayList<Border>();
		
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Country c1 = new Country(rs.getString(1), rs.getInt(2), rs.getString(3));
				Country c2 = new Country(rs.getString(4), rs.getInt(5), rs.getString(6));
				Border b = new Border(c1, c2);
				if(b.getC2().getCCode() < b.getC1().getCCode()) {
					result.add(b);
				}
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore getCountryPairs");
			throw new RuntimeException("Error Connection Database");
		}
	}
	
	
	public Country getCountry(String name) {
		String sql = "SELECT StateAbb, CCode, StateNme "
				+ "FROM country "
				+ "WHERE StateNme = ? ";
		Country c = null;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, name);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				c = new Country(rs.getString(1), rs.getInt(2), rs.getString(3));
			}
			conn.close();
			return c;
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore getCountryPairs");
			throw new RuntimeException("Error Connection Database");
		}
		
	}
}
