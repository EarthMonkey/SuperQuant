package PO;

import java.util.ArrayList;

public class benchmarkPO {
	//���д��̵Ĵ�̖
	private ArrayList<String> benchmark;

	public benchmarkPO(ArrayList<String> benchmark) {
		super();
		setBenchmark(benchmark);
	}

	public ArrayList<String> getBenchmark() {
		return benchmark;
	}

	private void setBenchmark(ArrayList<String> newbenchmark) {
		benchmark=new ArrayList<String>();
		for(int i=0;i<newbenchmark.size();i++){
			benchmark.add(newbenchmark.get(i));
		}
	}
	
}
