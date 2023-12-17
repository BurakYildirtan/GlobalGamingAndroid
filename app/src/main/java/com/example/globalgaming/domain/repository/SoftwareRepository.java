package com.example.globalgaming.domain.repository;

import com.example.globalgaming.common.callbacks.ResultCallback;
import com.example.globalgaming.domain.model.HardwareModel;
import com.example.globalgaming.domain.model.SoftwareModel;

import org.json.JSONObject;

public interface SoftwareRepository {

    void getSoftware(JSONObject typedUserData, ResultCallback<HardwareModel> resultCallback);
}
