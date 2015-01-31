package bg.learnit.annotation.processor;

import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.JavaFileObject;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import bg.learnit.annotation.MetaModel;

@SupportedAnnotationTypes("bg.learnit.annotation.MetaModel")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class MetaModelAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> typeElements, RoundEnvironment roundEnvironment) {
        for (Element element : roundEnvironment.getElementsAnnotatedWith(MetaModel.class)) {
            VelocityContext context = new VelocityContext();
            if (element.getKind() == ElementKind.CLASS) {
                TypeElement typeElement = (TypeElement) element;
                PackageElement packageElement = (PackageElement) typeElement.getEnclosingElement();
                Elements elementUtils = processingEnv.getElementUtils();
                List<? extends Element> members = elementUtils.getAllMembers(typeElement);
                List<String> names = new ArrayList<>();
                for (Element member : members) {
                    if (member.getKind() == ElementKind.FIELD) {
                        names.add(member.getSimpleName().toString());
                    }
                }
                context.put("className", typeElement.getSimpleName().toString());
                context.put("packageName", packageElement.getQualifiedName().toString());
                context.put("fields", names);
                Template template = getVelocityTemplate();
                try {
                    JavaFileObject jfo = processingEnv.getFiler().createSourceFile(typeElement.getQualifiedName() + "_", packageElement);
                    Writer writer = jfo.openWriter();
                    template.merge(context, writer);
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
    
    private Template getVelocityTemplate() {
        Properties properties = new Properties();
        URL url = this.getClass().getResource("/velocity.properties");
        try {
            properties.load(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        VelocityEngine engine = new VelocityEngine(properties);
        engine.init();
        return engine.getTemplate("metaInfo.vm");
    }

}
