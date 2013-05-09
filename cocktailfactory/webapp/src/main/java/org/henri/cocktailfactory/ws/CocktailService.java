package org.henri.cocktailfactory.ws;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("cocktail/*")
public class CocktailService {
    @RequestMapping(value="/list", method= RequestMethod.GET, produces="application/json")
    public @ResponseBody
    java.util.List<Cocktail> byProduces() {
        return new ArrayList<>();
    }
}