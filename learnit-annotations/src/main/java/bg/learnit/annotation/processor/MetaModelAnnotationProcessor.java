package bg.learnit.annotation.processor;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import bg.learnit.annotation.MetaModel;

@SupportedAnnotationTypes("bg.learnit.annotation.MetaModel")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class MetaModelAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> typeElements, RoundEnvironment roundEnvironment) {
        for (Element element : roundEnvironment.getElementsAnnotatedWith(MetaModel.class)) {
            new ElementProcessor(processingEnv).process(element);
        }
        return true;
    }
}
