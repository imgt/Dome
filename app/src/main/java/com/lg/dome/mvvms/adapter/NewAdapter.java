/*
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

package com.lg.dome.mvvms.adapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lg.baselib.base.adapter.SimpleBaseBindingAdapter;
import com.lg.dome.R;
import com.lg.dome.databinding.ItemNewBinding;
import com.lg.dome.mvvms.model.OnItemListener;
import com.lg.dome.mvvms.viewmodel.NewsBean;


/**
 */
public class NewAdapter extends SimpleBaseBindingAdapter<NewsBean.StoriesBean, ItemNewBinding> {
    private OnItemListener itemListener;
    public NewAdapter(Context context) {
        super(context, R.layout.item_new);
    }

    public void setItemListener(OnItemListener itemListener) {
        this.itemListener = itemListener;
    }

    @Override
    protected void onSimpleBindItem(ItemNewBinding binding, NewsBean.StoriesBean item, RecyclerView.ViewHolder holder) {
        binding.setBean(item);
        binding.getRoot().setOnClickListener(v -> {
         if (itemListener!=null){
             itemListener.onItemClick(v,holder.getLayoutPosition());
         }

        });

    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
}
