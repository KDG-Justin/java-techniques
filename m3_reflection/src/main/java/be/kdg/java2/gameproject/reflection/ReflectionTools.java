package be.kdg.java2.gameproject.reflection;

import java.lang.reflect.*;
import java.util.List;

public class ReflectionTools {

    @SafeVarargs
    public static void classAnalysis(Class<?>... aClass) {
        for (Class<?> current : aClass) {
            System.out.println("fully qualified name\t: " + current.getName());
            System.out.println("Naam van de Superklasse\t: " + current.getSimpleName());
            System.out.println("Naam van de package\t: " + current.getPackageName());
            System.out.println("Interfaces\t: " + current.getInterfaces().getClass().getSimpleName());

            StringBuilder constructorString = new StringBuilder();
            constructorString.append("Constructor\t: ");
            for (Constructor<?> constructors : current.getDeclaredConstructors()) {
                constructorString.append(constructors.toGenericString());
            }
            System.out.println(constructorString);

            StringBuilder attributeString = new StringBuilder();
            attributeString.append("Attributen\t: ");
            for (Field field : current.getDeclaredFields()) {
                String fieldInfo = field.getName() + "(" + field.getType().getSimpleName() + ") ";
                attributeString.append(fieldInfo);
            }
            System.out.println(attributeString);

            StringBuilder getters = new StringBuilder();
            getters.append("Getters: ");

            StringBuilder setters = new StringBuilder();
            setters.append("Setter: ");

            StringBuilder other = new StringBuilder();
            other.append("Andere methode: ");


            for (Method method : current.getDeclaredMethods()) {
                if (method.getName().startsWith("get")) {
                    String string = method.getName() + " ";
                    getters.append(string);

                } else if (method.getName().startsWith("set")) {
                    String string = method.getName() + " ";
                    setters.append(string);
                } else {
                    String string = method.getName() + " ";
                    other.append(string);

                }
            }
            System.out.println(getters);
            System.out.println(setters);
            System.out.println(other);
            System.out.println("\n");
        }
    }

    public static Object runAnnotated(Class<?> aClass) {
        try {
            System.out.println("Runannotated");
            Object object = aClass.getConstructor().newInstance();
            for (var method : aClass.getMethods()) {
                if (method.getName().startsWith("set") && method.getGenericParameterTypes()[0] == String.class){
                    var canRun = method.getAnnotation(CanRun.class);
                    method.invoke(object, canRun.value());
                }
            }
            return object;

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);

        }
    }
}
