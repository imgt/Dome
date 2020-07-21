package com.lg.dome.mvvms.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lg.baselib.base.BaseFragment
import com.lg.dome.R
import com.lg.dome.databinding.FragmentPageBinding
import com.lg.dome.mvvms.adapter.NewAdapter
import com.lg.dome.mvvms.model.OnItemListener
import com.lg.dome.mvvms.viewmodel.NewsViewModel

/**
 * Created by lqj on 2020/7/21.
 */
class PageFragment : BaseFragment<FragmentPageBinding>(){
    private var mNewsViewModel: NewsViewModel? = null
    private var adapter: NewAdapter? = null
    override fun initViewModel() {
        mNewsViewModel = getFragmentViewModel(NewsViewModel::class.java)


    }

    override fun layoutId()= R.layout.fragment_page

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mNewsViewModel!!.requestData()
        val rvNews: RecyclerView = mBinding.rvNews
        val manager = LinearLayoutManager(context)
        adapter = NewAdapter(context)
        rvNews.setLayoutManager(manager)
        rvNews.setAdapter(adapter)
        mNewsViewModel!!.news.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                adapter!!.setList(it.getStories())
                adapter!!.notifyDataSetChanged()
            }
        })
        adapter!!.setItemListener(object :OnItemListener{
            override fun onItemClick(view: View, position: Int) {

            }

        })

    }
}