package com.rbb.report.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.univocity.parsers.annotations.Parsed;

@XmlRootElement(name = "record")
@XmlAccessorType(XmlAccessType.FIELD)
public class Record {

	@Parsed(field = "Reference")
	@XmlAttribute
	private long reference;
	@Parsed(field = "AccountNumber")
	@XmlElement
	private String accountNumber;
	@Parsed(field = "Start Balance")
	@XmlElement
	private BigDecimal startBalance;
	@Parsed(field = "Mutation")
	@XmlElement
	private BigDecimal mutation;
	@Parsed(field = "End Balance")
	@XmlElement
	private BigDecimal endBalance;
	@Parsed(field = "Description")
	@XmlElement
	private String description;

	public long getReference() {
		return reference;
	}

	public void setReference(long reference) {
		this.reference = reference;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getStartBalance() {
		return startBalance;
	}

	public void setStartBalance(BigDecimal startBalance) {
		this.startBalance = startBalance;
	}

	public BigDecimal getMutation() {
		return mutation;
	}

	public void setMutation(BigDecimal mutation) {
		this.mutation = mutation;
	}

	public BigDecimal getEndBalance() {
		return endBalance;
	}

	public void setEndBalance(BigDecimal endBalance) {
		this.endBalance = endBalance;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Long.valueOf(reference).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (!(obj instanceof Record)) {
			return false;
		}
		Record model = (Record) obj;
		if (reference != model.getReference()) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return "Record [reference=" + reference + ", accountNumber=" + accountNumber + ", startBalance=" + startBalance
				+ ", mutation=" + mutation + ", endBalance=" + endBalance + ", description=" + description + "]";
	}

}
