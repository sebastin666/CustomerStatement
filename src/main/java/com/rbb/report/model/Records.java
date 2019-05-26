package com.rbb.report.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "records")
@XmlAccessorType(XmlAccessType.FIELD)
public class Records {
	
	public Records() {}
		
	public Records(List<Record> records) {
		this.records = records;
		System.out.println("rec:::::"+this.records);
	}

	@XmlElement(name = "record")
	private List<Record> records;

	public List<Record> getRecords() {
		return records;
	}

	public void setRecords(List<Record> records) {
		this.records = records;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getRecords().toString();
	}
}
