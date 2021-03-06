package DAO.pojo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TradeRecordId generated by hbm2java
 */
@Embeddable
public class TradeRecordId implements java.io.Serializable {

	private String stockId;
	private String date;

	public TradeRecordId() {
	}

	public TradeRecordId(String stockId, String date) {
		this.stockId = stockId;
		this.date = date;
	}

	@Column(name = "stockID", nullable = false, length = 20)
	public String getStockId() {
		return this.stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
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
		if (!(other instanceof TradeRecordId))
			return false;
		TradeRecordId castOther = (TradeRecordId) other;

		return ((this.getStockId() == castOther.getStockId()) || (this.getStockId() != null
				&& castOther.getStockId() != null && this.getStockId().equals(castOther.getStockId())))
				&& ((this.getDate() == castOther.getDate()) || (this.getDate() != null && castOther.getDate() != null
						&& this.getDate().equals(castOther.getDate())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getStockId() == null ? 0 : this.getStockId().hashCode());
		result = 37 * result + (getDate() == null ? 0 : this.getDate().hashCode());
		return result;
	}

}
