package drivezero;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import drivezero.models.User;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/handlebars", ".html");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("index");

        User person = new User();
        person.setName("Neil");
        person.setId(196);
        String templateString = template.apply(person);

        System.out.println(templateString);
    }
}
