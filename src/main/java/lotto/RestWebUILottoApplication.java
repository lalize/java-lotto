package lotto;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFiles;

public class RestWebUILottoApplication {
    public static void main(String[] args) {
        staticFiles.location("/static");

        get("/*", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "vue-index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}