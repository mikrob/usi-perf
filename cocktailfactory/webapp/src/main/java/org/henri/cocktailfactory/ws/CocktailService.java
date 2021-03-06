package org.henri.cocktailfactory.ws;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.henri.cocktailfactory.service.Bartender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("cocktail/*")
public class CocktailService {

    @Inject
    private Bartender bartender;

    @RequestMapping(value="/list", method= RequestMethod.GET, produces="application/json")
    public @ResponseBody List<Cocktail> list() {
        return bartender.getAllCocktails();
    }

    @RequestMapping(value="/filter/{filter}", method= RequestMethod.GET, produces="application/json")
    public @ResponseBody List<Cocktail> filter(@PathVariable String filter) {
        List<Cocktail> all = list();
        List<Cocktail> result = new ArrayList<>(all.size());
        for(Cocktail c : all) {
            if(c.getName().contains(filter) || c.getDescription().contains(filter)) {
                result.add(c);
            }
        }
        return result;
    }

}