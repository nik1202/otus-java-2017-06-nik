package ru.hw04;

import java.io.*;
import java.util.Map;
import java.util.Set;

/**
 * Created by nik on 6/27/2017.
 */
public class Counter implements Runnable {
    /**
     * Карта состояния объектов текущей минуты.
     */
    private Map<String, GCType> map;
    /**
     * SB для формирования отчетной строки.
     */
    private StringBuilder sb;
    /**
     * Конструктор.
     * @param map - карта.
     */
    public Counter(Map<String, GCType> map) {
        this.map = map;
    }
    /**
     * Ждем 60 секунд, формируем строку на основании данных в карте и обнуляем сотояние (в карте) обоих GC.
     */
    @Override
    public void run() {
        sb = new StringBuilder();
        for (Map.Entry<String, GCType> entry : map.entrySet()) {
            sb.append(entry.getValue().getName()).append(" & ");
        }
        sb.deleteCharAt(sb.length() - 1).deleteCharAt(sb.length() - 1).deleteCharAt(sb.length() - 1);
        writeLog(sb.toString());
        int minutes = 0;
        while (true) {
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sb = new StringBuilder();
            synchronized (map) {
                sb.append("\n").append(minutes + " minute: ");

                for (Map.Entry<String, GCType> entry : map.entrySet()) {
                    sb.append(entry.getValue().getName()).append(" count = ").append(entry.getValue().getCount()).append(" , time = ").append(entry.getValue().getTime()).append(" & ");
                }

                Set<String> set = map.keySet();
                for (String s : set) {
                    map.put(s, new GCType(s));
                }
            }
            minutes++;
            sb.deleteCharAt(sb.length() - 1).deleteCharAt(sb.length() - 1).deleteCharAt(sb.length() - 1);
            writeLog(sb.toString());
        }
    }
    /**
     * Пишем в лог файл отчет за прошедшую минуту.
     * @param text - строка отчета за минуту.
     */
    private void writeLog(String text) {
        String path = "gc.log";
        File file = new File(path);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(path, true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(text);
            bufferWriter.newLine();
            bufferWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
