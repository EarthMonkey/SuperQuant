package VO;

public class BenchVO {
	    private String[][] data;//��ʷ����
	     //��ʷ����˳�������ǣ����ڣ����̼ۣ���߼ۣ���ͼۣ����̼ۣ��ɽ���������ɣ����ɽ����Ԫ��
		private String status;//�Ƿ�����
		private String time;//��ǰ���ݵ�ʱ��
		private double now;//���ڵļ۸�
		private double rise_fall_price;//�ǵ���
		private double rise_fall_percent;//�ǵ���
		private double high;//��߼�
		private double low;//��ͼ�
		private double open;//���̼�
		private double yesterday_close;//�������̼�
		private String price;//�ɽ���
		private String volume;//�ɽ���
		private String rise_company;//�۸������Ĺ�˾
		private String fall_company;//�۸񽵵͵Ĺ�˾
		private String company;//���������Ĺ�˾
		public BenchVO() {
			super();
		}
		public String[][] getData() {
			return data;
		}
		public void setData(String[][] data) {
			this.data = data;
		}
		public double getNow() {
			return now;
		}
		public void setNow(double now) {
			this.now = now;
		}
		public double getRise_fall_price() {
			return rise_fall_price;
		}
		public void setRise_fall_price(double rise_fall_price) {
			this.rise_fall_price = rise_fall_price;
		}
		public double getRise_fall_percent() {
			return rise_fall_percent;
		}
		public void setRise_fall_percent(double rise_fall_percent) {
			this.rise_fall_percent = rise_fall_percent;
		}
		public double getHigh() {
			return high;
		}
		public void setHigh(double high) {
			this.high = high;
		}
		public double getLow() {
			return low;
		}
		public void setLow(double low) {
			this.low = low;
		}
		public double getOpen() {
			return open;
		}
		public void setOpen(double open) {
			this.open = open;
		}
		public double getYesterday_close() {
			return yesterday_close;
		}
		public void setYesterday_close(double yesterday_close) {
			this.yesterday_close = yesterday_close;
		}
		public String getPrice() {
			return price;
		}
		public void setPrice(String price) {
			this.price = price;
		}
		public String getVolume() {
			return volume;
		}
		public void setVolume(String volume) {
			this.volume = volume;
		}
		public String getRise_company() {
			return rise_company;
		}
		public void setRise_company(String rise_company) {
			this.rise_company = rise_company;
		}
		public String getFall_company() {
			return fall_company;
		}
		public void setFall_company(String fall_company) {
			this.fall_company = fall_company;
		}
		public String getCompany() {
			return company;
		}
		public void setCompany(String company) {
			this.company = company;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		
}
