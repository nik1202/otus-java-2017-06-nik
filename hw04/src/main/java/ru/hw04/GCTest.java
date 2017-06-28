package ru.hw04;

import com.sun.management.GarbageCollectionNotificationInfo;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.util.*;

/**
 * Created by nik on 6/27/2017.
 */
public class GCTest {
    /**
     * "Размер" объекта для добавления в лист.
     */
    private static final int SIZE = 3000;
    /**
     * Тестовый лист.
     */
    private List<String> list = new ArrayList<>();
    /**
     * Объект рандом для случайного удаления элементов из листа.
     */
    private Random r = new Random();
    /**
     * Карта статистики по GC.
     */
    private Map<String, GCType> map = new HashMap<>();
    /**
     * Метод запуста теста.
     * @param args - args.
     */
    public static void main(String[] args) {
        GCTest test = new GCTest();
        test.installGCMonitoring();

        new Thread(new Counter(test.map)).start();

        try {
            test.fillArray();
        } catch (OutOfMemoryError e) {
            System.exit(0);
        }
    }
    /**
     * Метод заполняет массив бесконечно, удаляя в случайном порядке случайные элементы,
     * но это не спасет его от OutOfMemory. :)
     */
    private void fillArray() {
        int isDelete;
        int index;

        while (true) {
            list.add(new String(new char[SIZE]));
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            isDelete = r.nextInt(2);
            if (isDelete == 1) {
                index = r.nextInt(list.size());
                list.remove(index);
            }
        }
    }
    /**
     * Мониториг CG.
     * При срабатывании GC в карту добавляется информация о том что сборка произошла и затраченное на нее время.
     */
    private void installGCMonitoring() {
        List<GarbageCollectorMXBean> gcbeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcbean : gcbeans) {
            NotificationEmitter emitter = (NotificationEmitter) gcbean;

            map.put(gcbean.getName(), new GCType(gcbean.getName()));

            NotificationListener listener = (notification, handback) -> {
                if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                    GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());

                    GCType t = map.get(info.getGcName());
                    t.countInc();
                    t.timeAdd(info.getGcInfo().getDuration());
                    map.put(info.getGcName(), t);
                }
            };

            emitter.addNotificationListener(listener, null, null);
        }
    }
}
