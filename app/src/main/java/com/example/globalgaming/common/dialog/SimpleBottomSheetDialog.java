package com.example.globalgaming.common.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.globalgaming.databinding.BottomSheetSimpleBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class SimpleBottomSheetDialog extends BottomSheetDialogFragment {

    public static final String TAG = "ModalBottomSheet";

    private BottomSheetSimpleBinding bottomSheetSimpleBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bottomSheetSimpleBinding = BottomSheetSimpleBinding.inflate(inflater, container, false);
        return bottomSheetSimpleBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bottomSheetSimpleBinding = null;
    }
}
