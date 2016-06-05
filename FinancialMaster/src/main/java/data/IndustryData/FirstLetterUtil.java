package data.IndustryData;

public class FirstLetterUtil {
	private static int BEGIN = 45217;
	private static int END = 63486;
	// ������ĸ��ʾ�����������GB2312�еĳ��ֵĵ�һ�����֣�Ҳ����˵�������Ǵ�������ĸa�ĵ�һ�����֡�
	// i, u, v��������ĸ, �Զ��������ǰ�����ĸ
	private static char[] chartable = { '��', '��', '��', '��', '��', '��', '��', '��', '��', '��', '��', '��', '��', '��', 'Ŷ', 'ž',
			'��', 'Ȼ', '��', '��', '��', '��', '��', '��', 'ѹ', '��', };
	// ��ʮ������ĸ�����Ӧ��ʮ�߸��˵�
	// GB2312�뺺������ʮ���Ʊ�ʾ
	private static int[] table = new int[27];
	// ��Ӧ����ĸ�����
	private static char[] initialtable = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'h', 'j', 'k', 'l', 'm', 'n', 'o',
			'p', 'q', 'r', 's', 't', 't', 't', 'w', 'x', 'y', 'z', };

	// ��ʼ��
	static {
		for (int i = 0; i < 26; i++) {
			table[i] = gbValue(chartable[i]);// �õ�GB2312�������ĸ����˵��ʮ���ơ�
		}
		table[26] = END;// ������β
	}

	/**
	 * ����һ���������ֵ��ַ�������һ������ƴ������ĸ���ַ��� ����Ҫ��һ��������˼·���£�һ�����ַ����롢�жϡ����
	 */
	public static String getFirstLetter(String sourceStr) {
		String result = "";
		String str = sourceStr.toLowerCase();
		int StrLength = str.length();
		int i;
		try {
			for (i = 0; i < StrLength; i++) {
				result += Char2Initial(str.charAt(i));
			}
		} catch (Exception e) {
			result = "";
		}
		return result;
	}

	/**
	 * �����ַ�,�õ�������ĸ,Ӣ����ĸ���ض�Ӧ�Ĵ�д��ĸ,�����Ǽ��庺�ַ��� '0'
	 */
	private static char Char2Initial(char ch) {
		// ��Ӣ����ĸ�Ĵ���Сд��ĸת��Ϊ��д����д��ֱ�ӷ���
		if (ch >= 'a' && ch <= 'z') {
			return ch;
		}
		if (ch >= 'A' && ch <= 'Z') {

			return ch;
		}
		// �Է�Ӣ����ĸ�Ĵ���ת��Ϊ����ĸ��Ȼ���ж��Ƿ������Χ�ڣ�
		// �����ǣ���ֱ�ӷ��ء�
		// ���ǣ���������ڵĽ����жϡ�
		int gb = gbValue(ch);// ����ת������ĸ

		if ((gb < BEGIN) || (gb > END))// ���������֮ǰ��ֱ�ӷ���
		{
			return ch;
		}

		int i;
		for (i = 0; i < 26; i++) {// �ж�ƥ��������䣬ƥ�䵽��break,�ж��������硰[,)��
			if ((gb >= table[i]) && (gb < table[i + 1])) {
				break;
			}
		}

		if (gb == END) {// ����GB2312�������Ҷ�
			i = 25;
		}
		return initialtable[i]; // ����������У���������ĸ
	}

	/**
	 * ȡ�����ֵı��� cn ����
	 */
	private static int gbValue(char ch) {// ��һ�����֣�GB2312��ת��Ϊʮ���Ʊ�ʾ��
		String str = new String();
		str += ch;
		try {
			byte[] bytes = str.getBytes("GB2312");
			if (bytes.length < 2) {
				return 0;
			}
			return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);
		} catch (Exception e) {
			return 0;
		}
	}
}
