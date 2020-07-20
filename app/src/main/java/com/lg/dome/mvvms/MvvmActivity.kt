package com.lg.dome.mvvms

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lg.baselib.base.BaseActivity

import com.lg.dome.R
import com.lg.dome.databinding.ActivityNewBinding
import com.lg.dome.mvvms.adapter.NewAdapter
import com.lg.dome.mvvms.viewmodel.NewsViewModel

/**
 */
class MvvmActivity : BaseActivity<ActivityNewBinding>(){
    private var mNewsViewModel: NewsViewModel? = null
    private var adapter: NewAdapter? = null
    override fun layoutId(): Int {
        return  R.layout.activity_new
    }
    override fun initViewModel() {
        mNewsViewModel = getActivityViewModel(NewsViewModel::class.java)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.click=ClickProxy()
        val rvNews: RecyclerView = mBinding.rvNews
        val manager = LinearLayoutManager(this)
        adapter = NewAdapter(this)
       rvNews.setLayoutManager(manager)
        rvNews.setAdapter(adapter)
        mNewsViewModel!!.news.observe(this, Observer {
            if (it!=null){
              //  adapter!!.setList(it.getStories())
                adapter!!.list.addAll(it.getStories())
                adapter!!.notifyDataSetChanged()
            }
        })

    }
    inner class ClickProxy {

        fun back() {
            mNewsViewModel!!.requestData()

        }
    }


}