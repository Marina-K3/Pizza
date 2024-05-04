package pizza.config;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class Init extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() { return new Class<?>[]{Web_MVC.class};
    }

    @Override
    protected String[] getServletMappings() { return new String[]{"/"};
    }

}