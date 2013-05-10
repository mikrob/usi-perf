package org.henri.cocktailfactory.ws;

import org.henri.cocktailfactory.mbean.PerformanceBean;
import org.henri.cocktailfactory.service.Bartender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("cocktail/*")
public class CocktailService {

    @Inject
    private Bartender bartender;

    @RequestMapping(value="/list", method= RequestMethod.GET, produces="application/json")
    public @ResponseBody List<Cocktail> list() {
        List<Cocktail> result = bartender.getAllCocktails();
        Collections.sort(result);
        return result;
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