package com.neo.http.client.executor.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-25 10:32
 */
public abstract class Factory<T, E> {
    private static final Logger logger = LoggerFactory.getLogger(Factory.class);

    public static Factory getFactory(String classname) {
        Factory factory = null;
        try {
            factory = (Factory) Class.forName(classname).newInstance();
        } catch (InstantiationException e) {
            logger.error(" Can not find the class: [" + classname + "]", e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return factory;
    }

    public abstract Executor<T, E> createGet();

    public abstract Executor<T, E> createPost();

    public abstract Executor<T, E> createPut();

    public abstract Executor<T, E> createDelete();

}
