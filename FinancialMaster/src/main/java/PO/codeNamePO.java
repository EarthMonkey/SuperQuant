package PO;
import java.util.ArrayList;

public class codeNamePO {
	//���й�Ʊ����
	private ArrayList<String> result;
	public codeNamePO(ArrayList<String> list){
		result=new ArrayList<String>();
		setResult(list);
	}
	public ArrayList<String> getResult() {
		return result;
	}
	private void setResult(ArrayList<String> list) {
		for (String string : list) {
			result.add(string);
		}
	}
	
	
}
