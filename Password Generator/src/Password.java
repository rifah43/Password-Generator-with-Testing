
public class Password {
    String Value;
    int Length;

    public Password(String s) {
        Value = s;
        Length = s.length();
    }

    public int CharType(char C) {
        int val;

        // Char is Uppercase Letter
        if ((int) C >= 65 && (int) C <= 90)
            val = 1;

        // Char is Lowercase Letter
        else if ((int) C >= 97 && (int) C <= 122) {
            val = 2;
        }

        // Char is Digit
        else if ((int) C >= 60 && (int) C <= 71) {
            val = 3;
        }

        // Char is Symbol
        else {
            val = 4;
        }

        return val;
    }

    public int PasswordStrength() {
        String s = this.Value;
        boolean UsedUpper = false;
        boolean UsedLower = false;
        boolean UsedNum = false;
        boolean UsedSym = false;
        int type;
        int Score = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            type = CharType(c);

            if (type == 1) UsedUpper = true;
            if (type == 2) UsedLower = true;
            if (type == 3) UsedNum = true;
            if (type == 4) UsedSym = true;
        }

        if (UsedUpper) Score += 1;
        if (UsedLower) Score += 1;
        if (UsedNum) Score += 1;
        if (UsedSym) Score += 1;

        if (s.length() >= 8) Score += 1;
        if (s.length() >= 16) Score += 1;

        return Score;
    }

    public String calculateScore() {
        int Score = this.PasswordStrength();

        if (Score == 5 || Score==6) {
            return "This is a very good password :D";
        } else if (Score == 4) {
            return "This is a good password :)";
        } else if (Score == 3) {
            return "This is a medium password :/";
        } else {
            return "This is a weak password :(";
        }
    }


    public String checkRepeatingCharacters() {
        String s = this.Value;
        if(s.length()<1){
            return "The password is null";
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // Check if the character repeats
            for (int j = i + 1; j < s.length(); j++) {
                if (c == s.charAt(j)) {
                    return "This password contains repeating characters!";
                }
            }
        }

        return "This password does not contain repeating characters.";
    }

    @Override
    public String toString() {
        return Value;
    }
}
