package com.neo.http.client.executor.jodd;

import com.neo.http.client.executor.factory.*;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-25 10:43
 */
public class JoddFactory extends Factory<String, String> {

    @Override
    public Executor<String, String> createGet() {
        return new JoddGet();
    }

    @Override
    public Executor<String, String> createPost() {
        return new JoddPost();
    }

    @Override
    public Executor<String, String> createPut() {
        return new JoddPut();
    }

    @Override
    public Executor<String, String> createDelete() {
        return new JoddDelete();
    }
}
