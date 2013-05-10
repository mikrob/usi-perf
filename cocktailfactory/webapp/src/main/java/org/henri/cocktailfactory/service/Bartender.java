package org.henri.cocktailfactory.service;

import org.henri.cocktailfactory.mbean.PerformanceBean;
import org.henri.cocktailfactory.ws.Cocktail;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
