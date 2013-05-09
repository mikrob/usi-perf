package org.henri.cocktailfactory.service;

import org.henri.cocktailfactory.ws.Cocktail;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class Bartender {

    private List<Cocktail> cocktails;

    public List<Cocktail> getAllCocktails() {
        if (cocktails == null) {
            cocktails = readList();
        }
        return cocktails;
    }

    private List<Cocktail> readList() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/cocktails.txt"), "UTF-8"))) {
            List<Cocktail> list = new ArrayList<>(400);
            String line;
            while ((line = in.readLine()) != null) {
                String name = line;
                StringBuilder description = new StringBuilder();
                while ((line = in.readLine()) != null && !line.equals("-------------------------------------------------")) {
                    description.append(line);
                    description.append("\n");
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
