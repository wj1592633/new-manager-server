package com.wj.manager.test;

import com.wj.manager.business.entity.Employee;
import com.wj.manager.business.entity.User;
import com.wj.manager.business.service.EmployeeService;
import com.wj.manager.business.service.impl.EmployeeServiceImpl;
import net.sf.cglib.asm.$Type;
import org.springframework.asm.Type;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.cglib.core.*;
import org.springframework.cglib.proxy.*;
import org.springframework.cglib.transform.ClassEmitterTransformer;
import org.springframework.cglib.transform.TransformingClassGenerator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestCglib {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        tset3();
    }
    private static final Callback[] CALLBACKS = new Callback[] {
            new BeanMethodInterceptor(),
            new BeanFactoryAwareMethodInterceptor(),
            NoOp.INSTANCE
    };
    private static final ConditionalCallbackFilter CALLBACK_FILTER = new ConditionalCallbackFilter(CALLBACKS);

    /**
     * 会调用额外接口的方法从而进入到setCallback的intercept()里面，但是无法执行额外接口(即BeanFactoryAware接口的方法)
     * 在newEnhancer()中设置
     * enhancer.setInterfaces(new Class<?>[] {EnhancedConfiguration.class}); //BeanFactoryAware
     *
     * org.springframework.context.annotation.ConfigurationClassEnhancer#newEnhancer
     * org.springframework.context.annotation.ConfigurationClassEnhancer.BeanFactoryAwareGeneratorStrategy#transform
     */
    public static void tset3() throws IllegalAccessException, InstantiationException {
        Object object;
        //System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "target/cglib");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(EmployeeServiceImpl.class);
        enhancer.setInterfaces(new Class[]{EmployeeService.class, MyTestInterface.class});
        enhancer.setCallbackFilter(CALLBACK_FILTER);
        enhancer.setCallbackTypes(CALLBACK_FILTER.getCallbackTypes());
       /* enhancer.setCallbacks(new Callback[]{new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("-------------start----------------");
                System.out.println("o的类型: " + o.getClass());
                if (method.getName().equals("setUser")){
                    Field my1user = o.getClass().getDeclaredField("my1user");
                    my1user.setAccessible(true);
                    my1user.set(o,args[0]);
                    return null;
                }
                Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
                Signature signature = methodProxy.getSignature();
                System.out.println("*********************<<<====1111 ===>>>***********************");
                System.out.println(method.getName() + "----->>>");
                Object o1 = methodProxy.invokeSuper(o, args);
                //Object o1 =  method.invoke(o,args);
                System.out.println("*********************<<<====2222 ===>>>**************************");
                System.out.println("-------------end----------------");
                return o1;
            }
        }});*/
        //enhancer.setCallback();
       /* enhancer.setStrategy(new DefaultGeneratorStrategy(){
            @Override
            protected ClassGenerator transform(ClassGenerator cg) throws Exception {
                return new TransformingClassGenerator(cg, new AddPropertyTransformer(new String[]{"myUser1"}, new Type[]{Type.getType(User.class)}));
            };
        });*/
        enhancer.setStrategy(
                new DefaultGeneratorStrategy(){
                    @Override
                    protected ClassGenerator transform(ClassGenerator cg) throws Exception {
                        ClassEmitterTransformer transformer = new ClassEmitterTransformer() {
                            @Override
                            public void end_class() {
                                declare_field(Constants.ACC_PUBLIC, "my1user", Type.getType(User.class), null);
                                super.end_class();
                            }
                        };
                        return new TransformingClassGenerator(cg, transformer);
                    }
                }
        );

        Class<?> subclass = enhancer.createClass();
        Enhancer.registerStaticCallbacks(subclass, CALLBACKS);

        EmployeeService service = (EmployeeService)subclass.newInstance();
        service.test1();
        service.test2();
        EmployeeServiceImpl impl = (EmployeeServiceImpl)service;
        MyTestInterface testInterface = (MyTestInterface)service;
        User user1 = new User();
        user1.setAccount("kkkkk");
        User user2 = new User();
        user2.setAccount("ooooo");
        Employee employee = new Employee();
        employee.setAddress("nnnnnn");
        impl.test3("kkkbo",user1, employee, user2);
        impl.test3("kkkbo",user1, employee, user2);
        impl.test3("kkkbo",user2, employee, user1);
        impl.test3("kkkbo",user2, employee, user1);
        User user = new User();
        user.setPassword("11111");
        user.setAccount("22222");
        testInterface.setUser(user);
        testInterface.setBeanFactory(new DefaultListableBeanFactory());
        System.out.println("end------------------");
    }

    public static void tset1() throws IllegalAccessException, InstantiationException {
        Object object;
        //System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "target/cglib");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(EmployeeServiceImpl.class);
        enhancer.setInterfaces(new Class[]{EmployeeService.class, MyTestInterface.class});

        enhancer.setCallbacks(new Callback[]{new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("-------------start----------------");
                System.out.println("o的类型: " + o.getClass());
                if (method.getName().equals("setUser")){
                    Field my1user = o.getClass().getDeclaredField("my1user");
                    my1user.setAccessible(true);
                    my1user.set(o,args[0]);
                    return null;
                }
                Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
                Signature signature = methodProxy.getSignature();
                System.out.println("*********************<<<====1111 ===>>>***********************");
                System.out.println(method.getName() + "----->>>");
                Object o1 = methodProxy.invokeSuper(o, args);
                //Object o1 =  method.invoke(o,args);
                System.out.println("*********************<<<====2222 ===>>>**************************");
                System.out.println("-------------end----------------");
                return o1;
            }
        }});
        //enhancer.setCallback();
       /* enhancer.setStrategy(new DefaultGeneratorStrategy(){
            @Override
            protected ClassGenerator transform(ClassGenerator cg) throws Exception {
                return new TransformingClassGenerator(cg, new AddPropertyTransformer(new String[]{"myUser1"}, new Type[]{Type.getType(User.class)}));
            };
        });*/
        enhancer.setStrategy(
                new DefaultGeneratorStrategy(){
                    @Override
                    protected ClassGenerator transform(ClassGenerator cg) throws Exception {
                        ClassEmitterTransformer transformer = new ClassEmitterTransformer() {
                            @Override
                            public void end_class() {
                                declare_field(Constants.ACC_PUBLIC, "my1user", Type.getType(User.class), null);
                                super.end_class();
                            }
                        };
                        return new TransformingClassGenerator(cg, transformer);
                    }
                }
        );

        EmployeeService service = (EmployeeService)enhancer.create();
        service.test1();
        service.test2();
        EmployeeServiceImpl impl = (EmployeeServiceImpl)service;
        MyTestInterface testInterface = (MyTestInterface)service;
        User user1 = new User();
        user1.setAccount("kkkkk");
        User user2 = new User();
        user2.setAccount("ooooo");
        Employee employee = new Employee();
        employee.setAddress("nnnnnn");
        impl.test3("kkkbo",user1, employee, user2);
        impl.test3("kkkbo",user1, employee, user2);
        impl.test3("kkkbo",user2, employee, user1);
        impl.test3("kkkbo",user2, employee, user1);
        User user = new User();
        user.setPassword("11111");
        user.setAccount("22222");
        testInterface.setUser(user);
        testInterface.setBeanFactory(new DefaultListableBeanFactory());
        System.out.println("end------------------");
    }
    public static void tset2(){
        net.sf.cglib.proxy.Enhancer enhancer = new net.sf.cglib.proxy.Enhancer();
        enhancer.setSuperclass(EmployeeServiceImpl.class);
        enhancer.setInterfaces(new Class[]{EmployeeService.class, MyTestInterface.class});
        enhancer.setCallbacks(new net.sf.cglib.proxy.MethodInterceptor[]{
                new net.sf.cglib.proxy.MethodInterceptor(){
                    @Override
                    public Object intercept(Object o, Method method, Object[] args, net.sf.cglib.proxy.MethodProxy methodProxy) throws Throwable {
                        System.out.println("-------------start----------------");
                        System.out.println("o的类型: " + o.getClass());
                        if (method.getName().equals("setUser")){
                            Field my1user = o.getClass().getDeclaredField("$cglib_prop_myUserx");
                            my1user.setAccessible(true);
                            my1user.set(o,args[0]);

                            return null;
                        }
                        Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
                        net.sf.cglib.core.Signature signature1 = methodProxy.getSignature();
                        System.out.println("*********************<<<====1111 ===>>>***********************");
                        System.out.println(method.getName() + "----->>>");
                        Object o1 = methodProxy.invokeSuper(o, args);
                        //Object o1 =  method.invoke(o,args);
                        System.out.println("*********************<<<====2222 ===>>>**************************");
                        System.out.println("-------------end----------------");
                        return o1;
                    }
                }
        });
        enhancer.setNamingPolicy(new net.sf.cglib.core.DefaultNamingPolicy(){
            @Override
            public String getClassName(String prefix, String source, Object key, net.sf.cglib.core.Predicate names) {
                String className = super.getClassName(prefix, source, key, names);
                System.out.println(className);
                return className;
            }
        });
        enhancer.setStrategy(new net.sf.cglib.core.DefaultGeneratorStrategy(){
            @Override
            protected net.sf.cglib.core.ClassGenerator transform(net.sf.cglib.core.ClassGenerator cg) throws Exception {
               return new net.sf.cglib.transform.TransformingClassGenerator (cg,
                        new net.sf.cglib.transform.impl.AddPropertyTransformer(new String[]{ "myUserx" },
                                new $Type []{ $Type.getType(User.class )}));
            }
        });

        EmployeeService service = (EmployeeService)enhancer.create();
        service.test1();
        service.test2();
        EmployeeServiceImpl impl = (EmployeeServiceImpl)service;
        MyTestInterface testInterface = (MyTestInterface)service;
        User user1 = new User();
        user1.setAccount("kkkkk");
        User user2 = new User();
        user2.setAccount("ooooo");
        Employee employee = new Employee();
        employee.setAddress("nnnnnn");
        impl.test3("kkkbo",user1, employee, user2);
        impl.test3("kkkbo",user1, employee, user2);
        impl.test3("kkkbo",user2, employee, user1);
        impl.test3("kkkbo",user2, employee, user1);
        User user = new User();
        user.setPassword("11111");
        user.setAccount("22222");
        testInterface.setUser(user);
        testInterface.setBeanFactory(new DefaultListableBeanFactory());
        System.out.println("end------------------");
    }
}
