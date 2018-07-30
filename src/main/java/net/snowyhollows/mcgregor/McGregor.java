package net.snowyhollows.mcgregor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

import com.squareup.javapoet.*;
import net.snowyhollows.mcgregor.api.ComponentBuilder;
import net.snowyhollows.mcgregor.tag.Component;

public class McGregor extends AbstractProcessor {

    private Filer filer;
    private Messager messager;
    private static final ClassName listName = ClassName.get(List.class);
	private static final ClassName componentName = ClassName.get(Component.class);
	private static final ParameterizedTypeName listOfComponents = ParameterizedTypeName.get(listName, componentName);

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
        messager = processingEnvironment.getMessager();
    }

    private ClassName nameFor(ClassName type, String suffix) {
        return ClassName.get(type.packageName(), type.simpleName() + suffix);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> beans = roundEnv.getElementsAnnotatedWith(ComponentBuilder.class);

        for (Element bean : beans) {
	        messager.printMessage(Diagnostic.Kind.NOTE, "generating " + bean);
	        TypeElement beanClass = (TypeElement) bean;
	        ClassName beanClassName = ClassName.get(beanClass);
	        ComponentBuilder componentBuilder = beanClass.getAnnotation(ComponentBuilder.class);

	        if (!beanClass.getModifiers().contains(Modifier.ABSTRACT)) {
		        messager.printMessage(Diagnostic.Kind.ERROR, "type: " + beanClass + " is not abstract.");
		        throw new RuntimeException("type: " + beanClass + " is not abstract.");
	        }

	        ClassName builderName = nameFor(beanClassName, "Templated");
	        String packageName = beanClassName.packageName();

	        MethodSpec.Builder build = MethodSpec.methodBuilder("getChildren")
			        .addAnnotation(Override.class)
			        .addModifiers(Modifier.PUBLIC)
			        .returns(listOfComponents);

	        FileInputStream fileReader = null;

	        try {
	        	String templateName = componentBuilder.value();
	        	if (templateName.equals("##same_as_simple_name")) {
	        		templateName = uncapitalize(beanClass.getSimpleName().toString()) + ".html";
		        }
		        FileObject res = filer.getResource(StandardLocation.SOURCE_OUTPUT, "a.b.c", "kapusta.html");
		        File baseSourcesDir = new File(res.getName());
		        while (!baseSourcesDir.getName().equalsIgnoreCase("build")) {
		        	baseSourcesDir = baseSourcesDir.getParentFile();
		        }
		        baseSourcesDir = baseSourcesDir.getParentFile();

		        String relativeName = "src/main/java/" + (templateName.startsWith("/") ? templateName : packageName.replace('.','/') + "/" + templateName);
		        File resourceFile = new File(baseSourcesDir, relativeName);
				messager.printMessage(Diagnostic.Kind.NOTE, "found file: " + resourceFile);

		        fileReader = new FileInputStream(resourceFile);

				messager.printMessage(Diagnostic.Kind.NOTE, "start processing");
		        ComponentDescription a = AstBuilder.process(fileReader);
				messager.printMessage(Diagnostic.Kind.NOTE, "end processing");
		        BasicAstRenderer basicAstRenderer = new BasicAstRenderer();
		        build.addStatement("$T keys = new $T(getKey());", KeyCreator.class, KeyCreator.class);
				messager.printMessage(Diagnostic.Kind.NOTE, "start rendering");
		        build.addStatement("return java.util.Collections.singletonList(" + basicAstRenderer.render(a) + ")");
				messager.printMessage(Diagnostic.Kind.NOTE, "end rendering");

	        } catch (IOException e) {
		        throw new RuntimeException(e);
	        } finally {
	        	if (fileReader != null) {
			        try {
				        fileReader.close();
			        } catch (IOException e) {
				        throw new RuntimeException(e);
			        }
		        }
	        }

			messager.printMessage(Diagnostic.Kind.NOTE, "start writing");
	        TypeSpec.Builder factory = TypeSpec.classBuilder(builderName)
	                .addModifiers(Modifier.PUBLIC)
	                .superclass(beanClassName)
	                .addMethod(build.build());

	        try {
		        JavaFile.builder(packageName, factory.build()).build().writeTo(filer);
				messager.printMessage(Diagnostic.Kind.NOTE, "end writing");
	        } catch (IOException e) {
		        throw new RuntimeException(e);
	        }
        }

        return true;

    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(ComponentBuilder.class.getName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

	private String uncapitalize(String t) {
		return t.substring(0, 1).toLowerCase() + t.substring(1);
	}

}
