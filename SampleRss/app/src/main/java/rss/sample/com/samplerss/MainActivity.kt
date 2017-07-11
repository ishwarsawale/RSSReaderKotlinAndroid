package rss.sample.com.samplerss


import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import rss.sample.com.samplerss.Adapter.FeedAdapter
import rss.sample.com.samplerss.Common.HTTPDataHandler
import rss.sample.com.samplerss.Model.RootObject

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private val RSS_link = "http://www.datatau.com/rss"
    private val RSS_to_JSON_API = "https://api.rss2json.com/v1/api.json?rss_url="
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title="DATATAU"
        setSupportActionBar(toolbar)
        var linearLayoutManager = LinearLayoutManager(baseContext,LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager = linearLayoutManager

        loadRSS()
    }

    private fun loadRSS() {

        val loadRSSAsync = @SuppressLint("StaticFieldLeak")
        object:AsyncTask<String,String,String>(){
            internal var  mDialog = ProgressDialog(this@MainActivity)

            override fun doInBackground(vararg p0: String?): String {
                val result:String
                val http  = HTTPDataHandler()
                result = http.GetHTTPDataHandler(p0[0])
                return result

            }

            override fun onPostExecute(result: String?) {
                mDialog.dismiss()
                var rootObject:RootObject
                rootObject = Gson().fromJson<RootObject>(result,RootObject::class.java!!)
                val adapter = FeedAdapter(rootObject,baseContext)
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }

            override fun onPreExecute() {
                mDialog.setMessage("Please wait..")
                mDialog.show()
            }


        }
        val url_get_data =  StringBuilder(RSS_to_JSON_API)
        url_get_data.append(RSS_link)
        loadRSSAsync.execute(url_get_data.toString())


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_refresh)
            loadRSS()
        return true

    }
}
