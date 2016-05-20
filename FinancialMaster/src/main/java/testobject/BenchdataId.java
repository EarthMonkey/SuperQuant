package testobject;
// Generated 2016-5-20 23:45:10 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * BenchdataId generated by hbm2java
 */
@Embeddable
public class BenchdataId implements java.io.Serializable {

	private String benchId;
	private String date;

	public BenchdataId() {
	}

	public BenchdataId(String benchId, String date) {
		this.benchId = benchId;
		this.date = date;
	}

	@Column(name = "benchID", nullable = false, length = 20)
	public String getBenchId() {
		return this.benchId;
	}

	public void setBenchId(String benchId) {
		this.benchId = benchId;
	}

	@Column(name = "date", nullable = false, length = 20)
	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BenchdataId))
			return false;
		BenchdataId castOther = (BenchdataId) other;

		return ((this.getBenchId() == castOther.getBenchId()) || (this.getBenchId() != null
				&& castOther.getBenchId() != null && this.getBenchId().equals(castOther.getBenchId())))
				&& ((this.getDate() == castOther.getDate()) || (this.getDate() != null && castOther.getDate() != null
						&& this.getDate().equals(castOther.getDate())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getBenchId() == null ? 0 : this.getBenchId().hashCode());
		result = 37 * result + (getDate() == null ? 0 : this.getDate().hashCode());
		return result;
	}

}
