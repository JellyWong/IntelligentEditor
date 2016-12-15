package wanggd;

public final class ErrorCorrection {
	
	private final static int MAX_HINTS = 8;
	
	private static String[][] dictionary = null;
	
	public static void init(String[][] _dictionary){
		dictionary = _dictionary;
	}
	
	public static String[] hint(String tar){
		if(tar==null) return null;
		int initial_index = tar.charAt(0)-'a';
 		int length = dictionary[initial_index].length;

		double similarity;
		double[] candidate_scores = new double[MAX_HINTS];
		for(int i = 0;i < MAX_HINTS;i++)
			candidate_scores[i] = 0;
		String[] candidates = new String[MAX_HINTS];
		for(int i = 0;i < length;i++){
			if(dictionary[initial_index][i]==null)
				break;
			if(dictionary[initial_index][i].equals(tar))
				return null;
			similarity = getSimilarity(dictionary[initial_index][i], tar);
			if(similarity > candidate_scores[MAX_HINTS-1]){
				int j = MAX_HINTS-1;
				while(j>0&&similarity>=candidate_scores[j]){
					candidate_scores[j] = candidate_scores[j-1];
					candidates[j] = candidates[j-1];
					j--;
				}
				candidate_scores[j] = similarity;
				candidates[j] = dictionary[initial_index][i];
			}
		}
		return candidates;
	}
	
	public static double getSimilarity(String src, String tar) {  
	        int ld = EditDistance(src, tar);  
	        return 1 - (double) ld / Math.max(src.length(), tar.length());   
	}  
	
	public static int EditDistance(String s, String t){
		int d[][];  
        int sLen = s.length();  
        int tLen = t.length();  
        int si;   
        int ti;   
        char ch1;  
        char ch2;  
        int cost;  
        if(sLen == 0) {  
            return tLen;  
        }  
        if(tLen == 0) {  
            return sLen;  
        }  
        d = new int[sLen+1][tLen+1];  
        for(si=0; si<=sLen; si++) {  
            d[si][0] = si;  
        }  
        for(ti=0; ti<=tLen; ti++) {  
            d[0][ti] = ti;  
        }  
        for(si=1; si<=sLen; si++) {  
            ch1 = s.charAt(si-1);  
            for(ti=1; ti<=tLen; ti++) {  
                ch2 = t.charAt(ti-1);  
                if(ch1 == ch2) {  
                    cost = 0;  
                } else {  
                    cost = 1;  
                }  
                d[si][ti] = Math.min(Math.min(d[si-1][ti]+1, d[si][ti-1]+1),d[si-1][ti-1]+cost);  
            }  
        }  
        return d[sLen][tLen];  
    }  
}
