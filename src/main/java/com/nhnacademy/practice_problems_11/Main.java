package com.nhnacademy.practice_problems_11;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
연습 11: TODO 앱에 파일 저장/로드 통합
목표: TODO 애플리케이션에 파일 저장/로드 기능을 통합하세요.

요구사항:

프로그램 시작 시 파일에서 자동 로드
프로그램 종료 시 파일에 자동 저장
저장/로드 메서드 분리
파일 없을 때 빈 리스트로 시작
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TodoApp {
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

    private static final String FILENAME = "todos.csv";
    private static List<Todo> todos = new ArrayList<>();
    private static int nextId = 1;

    public static void main(String[] args) {
        // TODO: 프로그램 시작 시 파일에서 로드

        // TODO: 간단한 메뉴 시뮬레이션
        // 샘플 TODO 추가
        todos.add(new Todo(nextId++, "새로운 할일", 2, false));

        // TODO: 프로그램 종료 시 파일에 저장
    }

    private static void loadFromFile() {
        // TODO: 파일에서 TODO 목록을 로드하는 코드 작성
        // 파일이 없으면 빈 리스트로 시작
        // nextId를 가장 큰 id + 1로 설정
    }

    private static void saveToFile() {
        // TODO: TODO 목록을 파일에 저장하는 코드 작성
    }
}
예상 출력 (첫 실행):

파일이 없습니다. 빈 리스트로 시작합니다.
TODO 추가됨: 새로운 할일
파일 저장 완료: todos.csv (1건)
예상 출력 (두 번째 실행):

파일 로드 완료: todos.csv (1건)
TODO 추가됨: 새로운 할일
파일 저장 완료: todos.csv (2건)
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
    }

    private static final String FILENAME = "todos.csv";
    private static List<Todo> todos = new ArrayList<>();
    private static int nextId = 1;

    public static void main(String[] args) {
        // 프로그램 시작 시 로드
        loadFromFile();

        // 샘플 TODO 추가
        todos.add(new Todo(nextId++, "새로운 할일", 2, false));
        System.out.println("TODO 추가됨: 새로운 할일");

        // 프로그램 종료 시 저장
        saveToFile();
    }

    private static void loadFromFile() {
        File file = new File(FILENAME);
        if (!file.exists()) {
            System.out.println("파일이 없습니다. 빈 리스트로 시작합니다.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            int maxId = 0;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String title = parts[1];
                int hours = Integer.parseInt(parts[2]);
                boolean done = Boolean.parseBoolean(parts[3]);

                todos.add(new Todo(id, title, hours, done));
                maxId = Math.max(maxId, id);
            }

            nextId = maxId + 1;
            System.out.println("파일 로드 완료: " + FILENAME + " (" + todos.size() + "건)");
        } catch (IOException e) {
            System.err.println("파일 로드 실패: " + e.getMessage());
        }
    }

    private static void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
            for (Todo todo : todos) {
                String line = todo.id + "," + todo.title + "," + todo.hours + "," + todo.done;
                writer.write(line);
                writer.newLine();
            }
            System.out.println("파일 저장 완료: " + FILENAME + " (" + todos.size() + "건)");
        } catch (IOException e) {
            System.err.println("파일 저장 실패: " + e.getMessage());
        }
    }
}

