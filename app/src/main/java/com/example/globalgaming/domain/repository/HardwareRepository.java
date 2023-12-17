package com.example.globalgaming.domain.repository;

import com.example.globalgaming.common.callbacks.ResultCallback;
import com.example.globalgaming.domain.model.SoftwareModel;

import org.json.JSONObject;

public interface HardwareRepository {

    void getHardware(JSONObject typedUserData, ResultCallback<SoftwareModel> resultCallback);
}
