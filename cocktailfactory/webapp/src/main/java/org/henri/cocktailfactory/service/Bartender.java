package org.henri.cocktailfactory.service;

import org.henri.cocktailfactory.mbean.PerformanceBean;
import org.henri.cocktailfactory.ws.Cocktail;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class Bartender {

    private static final Lock LOCK = new ReentrantLock();

    private List<Cocktail> cocktails;

    @Inject
    private PerformanceBean performanceBean;

    public List<Cocktail> getAllCocktails() {
        try {
            if (performanceBean.isBottleneck()) {
                LOCK.lock();
                cocktails = null;
            }

            if (cocktails == null) {
                cocktails = readList();

                // Oh dear, maybe there is a duplicated in the list
                List<Cocktail> result = new ArrayList<>(cocktails.size());
                for (int i = 0; i < cocktails.size(); i++) {
                    boolean duplicate = false;
                    Cocktail c = cocktails.get(i);
                    for (int j = i + 1; j < cocktails.size(); j++) {
                        Cocktail d = cocktails.get(j);
                        if (c.equals(d)) {
                            duplicate = true;
                        }
                    }
                    if (!duplicate) {
                        result.add(c);
                    }
                }
                cocktails = result;

                // Sleep to help debugging (TO REMOVE LATER)
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            return cocktails;
        } finally {
            if (performanceBean.isBottleneck()) {
                LOCK.unlock();
            }
        }
    }

    private List<Cocktail> readList() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/cocktails.txt"), "UTF-8"))) {
            List<Cocktail> list = new ArrayList<>(400);
            String line;
            while ((line = in.readLine()) != null) {
                String name = line;
                // Skip the next empty line
                line = in.readLine();
                // Start reading the description
                StringBuilder description = new StringBuilder();
                while ((line = in.readLine()) != null && !line.equals("-------------------------------------------------")) {
                    description.append(line);
                    description.append("<br/>");
                }
                Cocktail c = new Cocktail(name, description.toString());
                list.add(c);
            }
            Collections.sort(list);
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
