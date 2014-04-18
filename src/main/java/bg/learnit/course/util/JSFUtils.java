package bg.learnit.course.util;

import javax.faces.context.FacesContext;

public final class JSFUtils {

	public static <T> T getBean(String name, Class<T> clazz) {
		FacesContext context = FacesContext.getCurrentInstance();
	    T result = context.getApplication().evaluateExpressionGet(context, "#{" + name + "}", clazz);
	    return result;
	}
}
