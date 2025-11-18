/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
	String s1 = preProcess(str1);
    String s2 = preProcess(str2);

    String s1NoSpace = "";
    for (int i = 0; i < s1.length(); i++) {
        char c = s1.charAt(i);
        if (c != ' ') {
            s1NoSpace = s1NoSpace + c;
        }
    }

    String s2NoSpace = "";
    for (int i = 0; i < s2.length(); i++) {
        char c = s2.charAt(i);
        if (c != ' ') {
            s2NoSpace = s2NoSpace + c;
        }
    }

    if (s1NoSpace.length() != s2NoSpace.length()) {
        return false;
    }

    String temp = s2NoSpace;

    for (int i = 0; i < s1NoSpace.length(); i++) {
        char ch = s1NoSpace.charAt(i);
        boolean found = false;

        for (int j = 0; j < temp.length(); j++) {
            if (temp.charAt(j) == ch) {
                temp = temp.substring(0, j) + temp.substring(j + 1);
                found = true;
                break;
            }
        }

        if (!found) {
            return false;
        }
    }

    return true;
}

	
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
		 String result = "";

    for (int i = 0; i < str.length(); i++) {
        char ch = str.charAt(i);

        if (ch >= 'A' && ch <= 'Z') {
            ch = (char) (ch - 'A' + 'a');
        }

        if ((ch >= 'a' && ch <= 'z') || ch == ' ') {
            result = result + ch;
        }
    }
    return result;
}

	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
		 String remaining = str;
    String result = "";

    while (remaining.length() > 0) {
        int len = remaining.length();
        int index = (int) (Math.random() * len);

        char ch = remaining.charAt(index);
        result = result + ch;

        remaining = remaining.substring(0, index) + remaining.substring(index + 1);
    }

    return result;
}
}
