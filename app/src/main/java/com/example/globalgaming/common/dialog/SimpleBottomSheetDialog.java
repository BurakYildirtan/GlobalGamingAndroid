package com.example.globalgaming.common.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.globalgaming.common.adapter.OnProductClickListener;
import com.example.globalgaming.databinding.BottomSheetSimpleBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class SimpleBottomSheetDialog extends BottomSheetDialogFragment {

    public static final String TAG = "ModalBottomSheet";

    private BottomSheetSimpleBinding bottomSheetSimpleBinding;

    private OnButtonClickListener listener;


    public SimpleBottomSheetDialog(OnButtonClickListener onButtonClickListener) {
        this.listener = onButtonClickListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bottomSheetSimpleBinding = BottomSheetSimpleBinding.inflate(inflater, container, false);
        return bottomSheetSimpleBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setBtnListener();
    }

    private void setBtnListener() {
        bottomSheetSimpleBinding.btnConfirm.setOnClickListener(view ->  {
            listener.onButtonClick(true);
            this.dismiss();
        });

        bottomSheetSimpleBinding.btnCancel.setOnClickListener(view -> {
            listener.onButtonClick(false);
            this.dismiss();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bottomSheetSimpleBinding = null;
    }
}
