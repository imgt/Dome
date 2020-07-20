/*
 * Copyright 2018-2020 KunMinX
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lg.baselib.base;

import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.util.SparseArray;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.lg.baselib.BR;
import com.lg.baselib.base.model.SharedViewModel;


/**
 *
 */
public abstract class BaseActivity extends AppCompatActivity {

    private SharedViewModel mSharedViewModel;
    private ViewModelProvider mActivityProvider;
    private ViewDataBinding mBinding;


    protected abstract void initViewModel();

    protected abstract DataBindingConfig getDataBindingConfig();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSharedViewModel = ((BaseApp) getApplicationContext()).getAppViewModelProvider(this).get(SharedViewModel.class);

        initViewModel();
        DataBindingConfig dataBindingConfig = getDataBindingConfig();
        ViewDataBinding binding = DataBindingUtil.setContentView(this, dataBindingConfig.getLayout());
        binding.setLifecycleOwner(this);
       // binding.setVariable(BR.vm, dataBindingConfig.getStateViewModel());
        SparseArray bindingParams = dataBindingConfig.getBindingParams();
        for (int i = 0, length = bindingParams.size(); i < length; i++) {
            binding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i));
        }
        mBinding = binding;
    }

    public boolean isDebug() {
        return getApplicationContext().getApplicationInfo() != null &&
                (getApplicationContext().getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }


    protected void showLongToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    protected void showShortToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    protected void showLongToast(int stringRes) {
        showLongToast(getApplicationContext().getString(stringRes));
    }

    protected void showShortToast(int stringRes) {
        showShortToast(getApplicationContext().getString(stringRes));
    }

    protected <T extends ViewModel> T getActivityViewModel(@NonNull Class<T> modelClass) {
        if (mActivityProvider == null) {
            mActivityProvider = new ViewModelProvider(this);
        }
        return mActivityProvider.get(modelClass);
    }

    public SharedViewModel getSharedViewModel() {
        return mSharedViewModel;
    }
}
