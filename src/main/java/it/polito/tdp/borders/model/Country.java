package it.polito.tdp.borders.model;

public class Country {
	
	private String StateAbbr; 
	private Integer CCode;
	private String StateName;
	private Integer nStatiConfinanti;    //NON SO SE SERVE ----> CONTROLLA DOPO
	
	
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CCode == null) ? 0 : CCode.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (CCode == null) {
			if (other.CCode != null)
				return false;
		} else if (!CCode.equals(other.CCode))
			return false;
		return true;
	}


	public Country(String stateAbbr, Integer cCode, String stateName) {
		super();
		StateAbbr = stateAbbr;
		CCode = cCode;
		StateName = stateName;
	}


	public String getStateAbbr() {
		return StateAbbr;
	}


	public void setStateAbbr(String stateAbbr) {
		StateAbbr = stateAbbr;
	}


	public Integer getCCode() {
		return CCode;
	}


	public void setCCode(Integer cCode) {
		CCode = cCode;
	}


	public String getStateName() {
		return StateName;
	}


	public void setStateName(String stateName) {
		StateName = stateName;
	}
	
	public Integer getnStatiConfinanti() {
		return nStatiConfinanti;
	}


	public void setnStatiConfinanti(Integer nStatiConfinanti) {
		this.nStatiConfinanti = nStatiConfinanti;
	}


	@Override
	public String toString() {
		return "Country [StateAbbr=" + StateAbbr + ", CCode=" + CCode + ", StateName=" + StateName + "]";
	}
	
	
	
	

}
