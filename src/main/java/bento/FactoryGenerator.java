package bento;

import com.squareup.javapoet.*;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;
import net.snowyhollows.bento2.annotation.ByFactory;
import net.snowyhollows.bento2.annotation.ByName;
import net.snowyhollows.bento2.annotation.DefaultFactory;
import net.snowyhollows.bento2.annotation.WithFactory;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

public class FactoryGenerator extends AbstractProcessor {

//    private final static ClassName BENTO_FACTORY = ClassName.get(BentoFactory.class.getPackage().getName(), BentoFactory.class.getSimpleName());
//    private final static ClassName STRING = ClassName.get("java.lang", "String");
//
    private Filer filer;
    private Messager messager;
    private Types types;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
        messager = processingEnvironment.getMessager();
        types = processingEnvironment.getTypeUtils();
    }

//    private ClassName factoryNameFor(ClassName type, String suffix) {
//        return ClassName.get(type.packageName(), type.simpleName() + suffix);
//    }
//
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> beans = roundEnv.getElementsAnnotatedWith(WithFactory.class);

        for (Element bean : beans) {
            messager.printMessage(Diagnostic.Kind.NOTE, "generating " + bean);
            ExecutableElement constructor = (ExecutableElement) bean;
            TypeElement beanClass = (TypeElement) constructor.getEnclosingElement();
            ClassName beanClassName = ClassName.get(beanClass);

            String suffix = constructor.getAnnotation(WithFactory.class).value();

            ClassName factoryName = factoryNameFor(beanClassName, suffix);
            String packageName = beanClassName.packageName();

            ParameterizedTypeName bentoFactoryParametrized = ParameterizedTypeName.get(BENTO_FACTORY, beanClassName);

            MethodSpec.Builder createInContext = MethodSpec.methodBuilder("createInContext")
                    .addParameter(ParameterSpec.builder(Bento.class, "bento").build())
                    .addModifiers(Modifier.PUBLIC)
                    .returns(beanClassName)
                    .addCode("return new $T(", beanClassName);

            boolean first = true;
            for (VariableElement param : constructor.getParameters()) {
                TypeMirror tm = param.asType();

                if (!first) {
                    createInContext.addCode(", ");
                }

                ByName byName = param.getAnnotation(ByName.class);
                ByFactory byFactory = param.getAnnotation(ByFactory.class);

                TypeName typeName = TypeName.get(tm);
                boolean isEnum = !typeName.isPrimitive() && types.asElement(tm).getKind() == ElementKind.ENUM;
                boolean isByName = byName != null || typeName.isPrimitive() || typeName.equals(STRING) || isEnum;

                if (isByName) {
                    String nameToGet = (byName == null || byName.value().equals("##")) ?
                            param.getSimpleName().toString()
                            : byName.value();

                    String call = null;
                    if (typeName.equals(TypeName.FLOAT)) {
                        call = "bento.getFloat($S)";
                    } else if (typeName.equals(TypeName.INT)) {
                        call = "bento.getInt($S)";
                    } else if (typeName.equals(TypeName.BOOLEAN)) {
                        call = "bento.getBoolean($S)";
                    } else if (typeName.equals(STRING)) {
                        call = "bento.getString($S)";
                    } else if (isEnum) {
                        call = "bento.getEnum($T.class, $S)";
                    } else {
                        call = "bento.get($S)";
                    }
                    if (!isEnum) {
                        createInContext.addCode(call, nameToGet);
                    } else {
                        createInContext.addCode(call, ClassName.get(tm), nameToGet);
                    }

                } else {
                    if (byFactory == null) {
                        createInContext.addCode("bento.get($T.IT)", factoryNameFor((ClassName) ClassName.get(tm), "Factory"));
                    } else {
                        TypeMirror typeMirror = getT(byFactory);
                        TypeElement element = (TypeElement)types.asElement(typeMirror);
                        if (!element.getQualifiedName().toString().equals(DefaultFactory.class.getCanonicalName())) {
                            createInContext.addCode("bento.get($T.IT)", ClassName.get(element));
                        } else {
                            createInContext.addCode("bento.get($T.IT)", factoryNameFor((ClassName) ClassName.get(tm), "Factory"));
                        }
                    }
                }

                first = false;
            }

            createInContext.addCode(");\n");

            TypeSpec.Builder factory = TypeSpec.enumBuilder(factoryName)
                    .addModifiers(Modifier.PUBLIC)
                    .addEnumConstant("IT")
                    .addSuperinterface(bentoFactoryParametrized)
                    .addMethod(createInContext.build());

            try {
                JavaFile.builder(packageName, factory.build()).build().writeTo(filer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }

    private static TypeMirror getT(ByFactory byFactory) {
        try
        {
            byFactory.value();
        }
        catch( MirroredTypeException mte )
        {
            return mte.getTypeMirror();
        }
        return null;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(WithFactory.class.getName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

}
