package reflection.uml;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import reflection.uml.ReflectionData.*;

public class ProcessClasses {

    List<Link> getSuperclasses(Class<?> c, List<Class<?>> javaClasses) {
        // todo: implement this method

        List<Link> links = new ArrayList<>();
        Class<?>[] interfaces = c.getInterfaces();

        for(Class<?> javaClass : javaClasses) {
            if(c.getSuperclass().getSimpleName().equals(javaClass.getSimpleName())){
                links.add(new Link(c.getSimpleName(), javaClass.getSimpleName(), LinkType.SUPERCLASS));
            }

            for(Class<?> anInterface : interfaces){
                if(anInterface.getSimpleName().equals(javaClass.getSimpleName())){
                    links.add(new Link(c.getSimpleName(), anInterface.getSimpleName(), LinkType.SUPERCLASS));
                }
            }

        }

        return links;

    }


    ClassType getClassType(Class<?> c) {
        // todo: fix this broken implementation
        if(c.isInterface()){
            return ClassType.INTERFACE;
        }
        else if(Modifier.isAbstract(c.getModifiers())){
            return ClassType.ABSTRACT;
        }
        else{
            return ClassType.CLASS;
        }
    }

    List<FieldData> getFields(Class<?> c) {
        List<FieldData> fields = new ArrayList<>();
        for (java.lang.reflect.Field f : c.getDeclaredFields()) {
            fields.add(new FieldData(f.getName(), f.getType().getSimpleName()));
        }
        return fields;
    }

    List<MethodData> getMethods(Class<?> c) {
        // todo: implement this method
        List<MethodData> methodData = new ArrayList<>();

        Method[] methods = c.getDeclaredMethods();
        for(Method method : methods){
            if(!method.isSynthetic()){
                String returnType = method.getReturnType().getSimpleName();
                String name = method.getName();
                methodData.add(new MethodData(name, returnType));
            }

        }
        return methodData;
    }

    List<Link> getFieldDependencies(Class<?> c, List<Class<?>> javaClasses) {
        // todo: implement this method - return dependent classes that are in javaClasses

        List<Link> dependencies = new ArrayList<>();

        for(Class<?> javaClass : javaClasses){

            for (Field declaredField : c.getDeclaredFields()) {
                if(declaredField.getType().equals(javaClass)){
                    dependencies.add(new Link(c.getSimpleName(), javaClass.getSimpleName(), LinkType.DEPENDENCY));
                }
            }


        }

        return dependencies;

    }

    List<Link> getMethodDependencies(Class<?> c, List<Class<?>> javaClasses) {
        // todo: implement this method - return dependent classes that are in javaClasses

        List<Link> methodDependencies = new ArrayList<>();

        for(Class<?> javaClass : javaClasses){

            for (Method method : c.getDeclaredMethods()) {
                if(method.getReturnType().equals(javaClass)){
                    methodDependencies.add(new Link(c.getSimpleName(), javaClass.getSimpleName(), LinkType.DEPENDENCY));
                }
            }


        }

        return methodDependencies;

    }

    DiagramData process(List<Class<?>> javaClasses) {
        // we're going to process the classes here to build up the class data
        List<ClassData> classData = new ArrayList<>();
        Set<Link> links = new HashSet<>();

        for (Class<?> c : javaClasses) {
            String className = c.getSimpleName();
            ClassType classType = getClassType(c);
            List<FieldData> fields = getFields(c);
            List<MethodData> methods = getMethods(c);
            classData.add(new ClassData(className, classType, fields, methods));
            links.addAll(getSuperclasses(c, javaClasses));
            links.addAll(getFieldDependencies(c, javaClasses));
            links.addAll(getMethodDependencies(c, javaClasses));
        }
        return new DiagramData(classData, links);
    }


    public static void main(String[] args) {
        List<Class<?>> classes = new ArrayList<>();
        // add in all the classes we wish to generate UML for
        classes.add(MyShape.class);
        classes.add(MyCircle.class);
        classes.add(MyCircle.InnerStatic.class);
        classes.add(MyEllipse.class);
        classes.add(Connector.class);
        System.out.println(classes);
        System.out.println();
        DiagramData dd = new ProcessClasses().process(classes);
        System.out.println(dd);
        System.out.println();
        for (ClassData cd : dd.classes()) {
            System.out.println(cd);
        }
        System.out.println();
        for (Link l : dd.links()) {
            System.out.println(l);
        }
    }
}
