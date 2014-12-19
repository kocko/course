package bg.learnit.course.util;

import javax.faces.context.FacesContext;

public final class JSFUtils {

    /**
     * A private constructor, so that the class can not be initialized.
     */
    private JSFUtils() {
        // empty constructor to avoid instantiation of this class
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().evaluateExpressionGet(context, "#{" + name + "}", clazz);
    }
}
