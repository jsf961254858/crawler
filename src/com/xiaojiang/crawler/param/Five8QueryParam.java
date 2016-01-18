package com.xiaojiang.crawler.param;

public class Five8QueryParam {

	private String zoneName;
	
	private String zoneUrl;
	
	private String districtName;
	
	private String districtUrl;
	
	private String price;
	
	private String area;
	
	private String bedroom;

	
	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public String getZoneUrl() {
		return zoneUrl;
	}

	public void setZoneUrl(String zoneUrl) {
		this.zoneUrl = zoneUrl;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getDistrictUrl() {
		return districtUrl;
	}

	public void setDistrictUrl(String districtUrl) {
		this.districtUrl = districtUrl;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getBedroom() {
		return bedroom;
	}

	public void setBedroom(String bedroom) {
		this.bedroom = bedroom;
	}
	
	public String toString(){
		return "{zoneName="+this.getZoneName()+", zoneUrl="+this.getZoneUrl()+","
				+ "districtName="+this.getDistrictName()+",districtUrl="+this.getDistrictUrl()+","
				+ "price="+this.getPrice()+",area="+this.getArea()+",bedroom="+this.getBedroom()+",}";
	}
	
	public String toUrl(){
		
		String url = null;
		
		if(this.getDistrictUrl() != null && !"".equals(this.getDistrictUrl()))
			url = this.getDistrictUrl();
		else
			url = this.getZoneUrl();
	
		if(this.getPrice() != null && !"".equals(this.getPrice()))
			url += this.getPrice();
		
		if(this.getArea() != null && !"".equals(this.getArea()))
			url += this.getArea();
		
		if(this.getBedroom() != null && !"".equals(this.getBedroom()))
			url += this.getBedroom();
		
		if(!url.endsWith("/"))
			url += "/";
			
		return url;
		
	}
	
}
