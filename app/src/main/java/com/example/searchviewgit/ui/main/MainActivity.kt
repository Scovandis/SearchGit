package com.example.searchviewgit.ui.main

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchviewgit.R
import com.example.searchviewgit.model.User
import com.example.searchviewgit.model.UserResponse
import com.example.searchviewgit.network.ApiService
import com.example.searchviewgit.network.ApiUser
import com.example.searchviewgit.ui.main.adaper.MainAdapter
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var jsonApi: ApiUser
    lateinit var compositeDisposable: CompositeDisposable

    lateinit var list = ArrayList<User>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = ApiService.instance
        jsonApi = retrofit.create(ApiUser::class.java)

        recyclerView.layoutManager = LinearLayoutManager(this)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu!!.findItem(R.id.search_view).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.searchview)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            //gunakan methode ini setelah search selelsai
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
                setUser(query)
                return true
            }

            //gunakan methode ini untuk merespon tiap perubahan huruf pada searchView
            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }

        })




        return true
    }

    private fun setUser(query: String?) {

        query?.let {
            jsonApi.getSearchUser(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{user -> display(user)}
        }?.let {
            compositeDisposable.add(
                it
            )
        }


    }

    private fun display(user: List<User>?) {
        val adapter = MainAdapter(user, list)
        recyclerView.adapter = adapter
    }


}