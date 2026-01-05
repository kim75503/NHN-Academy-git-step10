package com.nhnacademy.pratice_problems_9;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
Part 2: 파일 저장/로드 연습
연습 9: TODO 목록을 CSV 파일로 저장
목표: BufferedWriter를 사용하여 TODO 목록을 CSV 파일로 저장하는 프로그램을 작성하세요.

요구사항:

try-with-resources 사용
모든 TODO를 CSV 형식으로 변환
파일 저장 성공 메시지 출력
IOException 예외 처리
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TodoFileSaver {
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
    }

    public static void main(String[] args) {
        List<Todo> todos = new ArrayList<>();
        todos.add(new Todo(1, "Java 공부", 3, false));
        todos.add(new Todo(2, "운동하기", 1, true));
        todos.add(new Todo(3, "독서", 2, false));

        String filename = "todos.csv";

        // TODO: try-with-resources를 사용하여 BufferedWriter 생성

        // TODO: 각 TODO를 CSV 형식으로 변환하여 파일에 쓰기
        // 형식: id,title,hours,done

        // TODO: IOException 예외 처리

        // TODO: 저장 성공 메시지 출력
    }
}
예상 출력:

파일 저장 완료: todos.csv (3건)
파일 내용 (todos.csv):

1,Java 공부,3,false
2,운동하기,1,true
3,독서,2,false

 */
public class Main {
    public static void main(String[] args) {
        List<Todo> todos = new ArrayList<>();
        todos.add(new Todo(1, "Java 공부", 3, false));
        todos.add(new Todo(2, "운동하기", 1, true));
        todos.add(new Todo(3, "독서", 2, false));

        String filename = "todos.csv";

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
            for(Todo todo : todos){
                String line = todo.id + "," + todo.title + "," + todo.hours +"," + todo.done;
                writer.write(line);
                writer.newLine();
            }
             System.out.println("파일 저장 완료: " + filename + " (" + todos.size() + "건)");
        } catch (IOException e) {
            System.err.println("파일 저장 실패: " + e.getMessage());
        }
    }
}



class Todo {
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
}