package phong.nt.qltv;

public class Helper {
	public static final String[] COLLECTION = new String[] { "S\u00E1ch Ti\u1EBFng Anh",
			"S\u00E1ch V\u0103n H\u1ECDc - Ti\u1EC3u Thuy\u1EBFt", "S\u00E1ch Kinh T\u1EBF",
			"S\u00E1ch Chuy\u00EAn Ng\u00E0nh",
			"S\u00E1ch K\u1EF9 N\u0103ng S\u1ED1ng - Ngh\u1EC7 Thu\u1EADt S\u1ED1ng",
			"S\u00E1ch Gi\u00E1o Khoa - Tham Kh\u1EA3o",
			"S\u00E1ch H\u1ECDc Ngo\u1EA1i Ng\u1EEF - T\u1EEB \u0110i\u1EC3n",
			"S\u00E1ch Cho Tu\u1ED5i M\u1EDBi L\u1EDBn", "S\u00E1ch Truy\u1EC7n Thi\u1EBFu Nhi",
			"S\u00E1ch Th\u01B0\u1EDDng Th\u1EE9c - \u0110\u1EDDi S\u1ED1ng", "Truy\u1EC7n Tranh - Manga - Comic",
			"S\u00E1ch V\u0103n Ho\u00E1 - Ngh\u1EC7 Thu\u1EADt - Du L\u1ECBch", "T\u1EA1p Ch\u00ED",
			"S\u00E1ch Nu\u00F4i D\u1EA1y Con", "S\u00E1ch C\u0169 Gi\u00E1 T\u1ED1t",
			"Combo - Series S\u00E1ch \u0110\u1EB7c S\u1EAFc" };

	public static final String[] CATEGORY_CODE = new String[] { "c320", "c839", "c846", "c873", "c870", "c1012", "c887",
			"c714", "c393", "c862", "c1084", "c857", "c1468", "c2527", "c3447", "c3550" };

	public static int indexOfCategoryCode(String code) {
		for (int i = 0; i < CATEGORY_CODE.length; i++) {
			if (CATEGORY_CODE[i].equals(code)) {
				return i;
			}
		}
		return -1;
	}

	public static int indexOfCollection(String collection) {
		for (int i = 0; i < COLLECTION.length; i++) {
			if (COLLECTION[i].equals(collection)) {
				return i;
			}
		}
		return -1;
	}
	
	public static String collectionOfCode(String code){
		int index = indexOfCategoryCode(code);
		return COLLECTION[index];
	}

}
