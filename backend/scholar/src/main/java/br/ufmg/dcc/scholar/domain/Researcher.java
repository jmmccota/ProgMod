package br.ufmg.dcc.scholar.domain;

public class Researcher implements IResearcher {

	private String name;
	
	private String expertiseArea;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setExpertiseArea(String expertiseArea) {
		this.expertiseArea = expertiseArea;
	}

	@Override
	public String retrieveExpertiseArea() {
		return this.expertiseArea;
	}
}
