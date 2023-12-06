package com.example.globalgaming.common.callbacks;

import com.example.globalgaming.common.mapper.Result;

public interface ResultCallback<T> {
    void onSuccess(Result<T> response);

    void onError(Result<T> error);
}