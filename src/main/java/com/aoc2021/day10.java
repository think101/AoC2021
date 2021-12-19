package main.java.com.aoc2021;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class day10 {
    public static void main(String[] args) {
       List<String> lines = getLines();

       System.out.println("Syntax error score: " + calcSyntaxErrorScore(lines));
    }

    private static List<String> getLines() {
        List<String> lines = new ArrayList<>();

        try {
            //BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/day10_example.txt"));
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/day10_input.txt"));

            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return lines;
    }

    private static int calcSyntaxErrorScore(List<String> lines) {
        int result = 0;

        List<Character> wrongLetters = new ArrayList<>();

        for(String line : lines) {
            if(line == null || line.isEmpty()) {
                continue;
            }

            Stack<Character> stack = new Stack<>();
            for(int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if(c == '(' || c == '[' || c == '{' || c == '<') {
                    stack.push(c);
                }
                else if(c == ')' || c == ']' || c == '}' || c == '>') {
                    if( !stack.isEmpty() && checkSyntaxError(stack, c)) {
                        //System.out.println("Wrong letter: " + c + " at position: " + i + " in line: " + line);
                        wrongLetters.add(c);
                        break;
                    }
                    else {
                        if( !stack.isEmpty()) {
                            stack.pop();
                        }
                    }
                }
            }
        }

        if(!wrongLetters.isEmpty()) {
            for(char c : wrongLetters) {
                if( c == ')') {
                    result += 3;
                }
                else if(c == ']') {
                    result += 57;
                }
                else if(c == '}') {
                    result += 1197;
                }
                else if(c == '>') {
                    result += 25137;
                }
            }
        }

        return result;
    }

    // return true if there is a syntax error
    private static boolean checkSyntaxError(Stack<Character> stack, char c) {
        return (stack.peek() == '(' && c != ')')
                || (stack.peek() == '[' && c != ']')
                || (stack.peek() == '{' && c != '}')
                || (stack.peek() == '<' && c != '>');
    }

}
