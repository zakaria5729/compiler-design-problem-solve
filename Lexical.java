package Compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Lexical {

    public static void main(String[] args) {

        ArrayList<String> strList = new ArrayList<>();
        ArrayList<String> keyList = new ArrayList<>();
        ArrayList<String> idenList = new ArrayList<>();
        ArrayList<String> mathOpList = new ArrayList<>();
        ArrayList<String> logicOpList = new ArrayList<>();
        ArrayList<String> numericList = new ArrayList<>();
        ArrayList<String> othersList = new ArrayList<>();
        
        for (char ch='a'; ch<='z'; ch++) {
            String str = String.valueOf(ch);
            idenList.add(str);
        } 
        
         for (int i = 0; i < 10; i++) {
            double db = (double) i;        
            String strInt = String.valueOf(i);
            String strDouble = String.valueOf(db);
            
            numericList.add(strInt);
            numericList.add(strDouble);
        }

        keyList.add("int");
        keyList.add("float");
        keyList.add("double");
        keyList.add("String");
        keyList.add("char");
        keyList.add("do");
        keyList.add("while");
        keyList.add("for");
        keyList.add("if");
        keyList.add("else");
        keyList.add("break");
        keyList.add("continue");
        keyList.add("return");

        mathOpList.add("+");
        mathOpList.add("-");
        mathOpList.add("*");
        mathOpList.add("/");
        mathOpList.add("=");

        logicOpList.add("<");
        logicOpList.add(">");
        logicOpList.add("<=");
        logicOpList.add(">=");
        logicOpList.add("<=");
        logicOpList.add("==");
        logicOpList.add("!=");

        othersList.add("(");
        othersList.add(")");
        othersList.add("{");
        othersList.add("}");
        othersList.add("[");
        othersList.add("]");
        othersList.add(",");
        othersList.add(";");

        HashSet<String> keySet = new HashSet<>();
        HashSet<String> idenSet = new HashSet<>();
        HashSet<String> mathSet = new HashSet<>();
        HashSet<String> logicSet = new HashSet<>();
        HashSet<String> mumericSet = new HashSet<>();
        HashSet<String> othersSet = new HashSet<>();

        try {
            Scanner input = new Scanner(new File("C:\\Users\\Zakaria\\Desktop\\lexical.txt"));
            while (input.hasNext()) {
                String s = input.nextLine();
                String strArr[] = s.split(" ");
                strList.addAll(Arrays.asList(strArr));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        int j = 0;
        while (strList.size() > j) {
            if (keyList.contains(strList.get(j))) {
                keySet.add(strList.get(j));
            } else if (idenList.contains(strList.get(j))) {
                idenSet.add(strList.get(j));
            } else if (mathOpList.contains(strList.get(j))) {
                mathSet.add(strList.get(j));
            } else if (logicOpList.contains(strList.get(j))) {
                logicSet.add(strList.get(j));
            } else if (numericList.contains(strList.get(j))) {
                mumericSet.add(strList.get(j));
            } else if (othersList.contains(strList.get(j))) {
                othersSet.add(strList.get(j));
            }
            j++;
        }
        
        System.out.print("Keywords:");
        for (String s : keySet) {
            System.out.print(" " + s);
        }
        System.out.println();
        
        System.out.print("Identifers:");
        for (String s : idenSet) {
            System.out.print(" " + s);
        }
        System.out.println();
        
        System.out.print("Math Operators:");
        for (String s : mathSet) {
            System.out.print(" " + s);
        }
        System.out.println();
        
        System.out.print("Logical Operator:");
        for (String s : logicSet) {
            System.out.print(" " + s);
        }
        System.out.println();
        
        System.out.print("Numerical Values:");
        for (String s : mumericSet) {
            System.out.print(" " + s);
        }
        System.out.println();
        
        System.out.print("Others:");
        for (String s : othersSet) {
            System.out.print(" " + s);
        }
        System.out.println();
    }
}
