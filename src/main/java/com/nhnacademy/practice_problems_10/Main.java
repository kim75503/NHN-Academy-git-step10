package com.nhnacademy.practice_problems_10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
연습 10: CSV 파일에서 TODO 목록 로드
목표: BufferedReader를 사용하여 CSV 파일에서 TODO 목록을 읽어오는 프로그램을 작성하세요.

요구사항:

파일 존재 여부 확인
try-with-resources 사용
CSV 파싱하여 Todo 객체 생성
로드된 목록 출력
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TodoFileLoader {
    static class Todo {
        int id;
        String title;
        int hours;
        boolean done;

        Todo(int id, String title, int hours, boolean done) {
            this.id = id;
            this.title = title;
            this.hours = hours;
            this.done = done;
        }

        @Override
        public String toString() {
            return String.format("[%d] %s (%dh) - %s",
                id, title, hours, done ? "완료" : "미완료");
        }
    }

    public static void main(String[] args) {
        String filename = "todos.csv";
        List<Todo> todos = new ArrayList<>();

        // TODO: File 객체를 생성하고 파일 존재 여부 확인

        // TODO: try-with-resources로 BufferedReader 생성

        // TODO: 파일을 한 줄씩 읽으면서 CSV 파싱
        // split()으로 분리 후 Todo 객체 생성하여 리스트에 추가

        // TODO: IOException 예외 처리

        // TODO: 로드된 TODO 목록 출력
    }
}
예상 출력:

파일 로드 완료: todos.csv (3건)
=== TODO 목록 ===
[1] Java 공부 (3h) - 미완료
[2] 운동하기 (1h) - 완료
[3] 독서 (2h) - 미완료
 */
public class Main {
    static class Todo {
        int id;
        String title;
        int hours;
        boolean done;

        Todo(int id, String title, int hours, boolean done) {
            this.id = id;
            this.title = title;
            this.hours = hours;
            this.done = done;
        }

        @Override
        public String toString() {
            return String.format("[%d] %s (%dh) - %s",
                id, title, hours, done ? "완료" : "미완료");
        }
    }

    public static void main(String[] args) throws IOException{
        String filename = "todos.csv";
        List<Todo> todos = new ArrayList<>();


        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("파일이 존재하지 않습니다: " + filename);
            return;
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            while ((line = reader.readLine()) != null) { 
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String title = parts[1];
                int hours = Integer.parseInt(parts[2]);
                boolean done = Boolean.parseBoolean(parts[3]);

                todos.add(new Todo(id, title, hours,  done));
                
            }
            System.out.println("파일 로드 완료: " + filename + " (" + todos.size() + "건)");
            System.out.println("=== TODO 목록 ===");
            for(Todo todo : todos){
                System.out.println(todo);
            }
        }

        // TODO: File 객체를 생성하고 파일 존재 여부 확인

        // TODO: try-with-resources로 BufferedReader 생성

        // TODO: 파일을 한 줄씩 읽으면서 CSV 파싱
        // split()으로 분리 후 Todo 객체 생성하여 리스트에 추가

        // TODO: IOException 예외 처리

        // TODO: 로드된 TODO 목록 출력
    }
}
