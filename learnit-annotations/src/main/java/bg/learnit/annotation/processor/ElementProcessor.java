package bg.learnit.annotation.processor;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

class ElementProcessor {

	private VelocityContext velocityContext;
	
	private ProcessingEnvironment processingEnvironment;
	
	public ElementProcessor(ProcessingEnvironment processingEnvironment) {
		this.velocityContext = new VelocityContext();
		this.processingEnvironment = processingEnvironment;
	}
	
	public void process(Element elementForProcessing) {
        if (elementForProcessing.getKind() == ElementKind.CLASS) {
            TypeElement classType = (TypeElement) elementForProcessing;
            PackageElement packageElement = (PackageElement) classType.getEnclosingElement();
            List<String> names = extractClassMembersNames(classType);
            
            fillVelocityContext(classType.getSimpleName(), packageElement.getQualifiedName(), names);
            fillTemplateWithActualData(classType.getQualifiedName().toString(), packageElement);
        }
	}

	private List<String> extractClassMembersNames(TypeElement classType) {
		List<? extends Element> classMembersElements = processingEnvironment.getElementUtils().getAllMembers(classType);
		List<String> classMemberNames = new ArrayList<>();
		for (Element member : classMembersElements) {
		    if (member.getKind() == ElementKind.FIELD) {
		        classMemberNames.add(member.getSimpleName().toString());
		    }
		}
		return classMemberNames;
	}

	private void fillVelocityContext(Name simpleClassName, Name fullPackage, List<String> classMemberNames) {
		velocityContext.put("className", simpleClassName.toString());
		velocityContext.put("packageName", fullPackage.toString());
		velocityContext.put("fields", classMemberNames);
	}
	
	private void fillTemplateWithActualData(String fullyQualifiedClassName, PackageElement packageElement) {
		Template template = new VelocityUtils().getVelocityTemplate();
		try {
		    JavaFileObject javaFileObject = processingEnvironment.getFiler().createSourceFile(fullyQualifiedClassName + "_", packageElement);
		    writeJavaFileObjectToSourceFileUsingTemplate(template, javaFileObject);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}

	private void writeJavaFileObjectToSourceFileUsingTemplate(Template template, JavaFileObject jfo) throws IOException {
		Writer writer = jfo.openWriter();
		template.merge(velocityContext, writer);
		writer.close();
	}
	
}

