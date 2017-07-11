package rss.sample.com.samplerss.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import rss.sample.com.samplerss.Interface.ItemClickListener
import rss.sample.com.samplerss.Model.RootObject
import rss.sample.com.samplerss.R





@Suppress("DEPRECATION")
class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener,View.OnLongClickListener{

    var txtTitle: TextView
    var txtPubdate:TextView
    var txtContent:TextView

    private var itemClickListener : ItemClickListener?=null

    init {

        txtTitle =   itemView.findViewById<TextView>(R.id.txtTitle)
        txtPubdate =   itemView.findViewById<TextView>(R.id.txtPubdate)
        txtContent =   itemView.findViewById<TextView>(R.id.txtContent)

        itemView.setOnClickListener(this)
        itemView.setOnLongClickListener(this)
    }


    fun setItemClickListener(itemClickListener: ItemClickListener){

        this.itemClickListener = itemClickListener

    }

    override fun onClick(p0: View?) {
        itemClickListener!!.onClick(p0,position,false)
    }

    override fun onLongClick(p0: View?): Boolean {
        itemClickListener!!.onClick(p0,position,true)
        return  true
    }

}


class  FeedAdapter(private val rootObject: RootObject, private val mContext: Context) :RecyclerView.Adapter<FeedViewHolder>(){

    private val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(mContext)
    }

    override fun getItemCount(): Int {

        return rootObject.items.size
    }

    override fun onCreateViewHolder(p0: ViewGroup?, p1: Int): FeedViewHolder {

        val itemView = inflater.inflate(R.layout.row,p0,false)
        return FeedViewHolder(itemView)
    }

    override fun onBindViewHolder(p0: FeedViewHolder?, p1: Int) {
        p0?.txtTitle?.text = rootObject.items[p1].title
        p0?.txtContent?.text = rootObject.items[p1].link
        p0?.txtPubdate?.text = rootObject.items[p1].pubDate

        p0?.setItemClickListener(ItemClickListener { view, position, isLongClick ->

            if (!isLongClick){
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(rootObject.items[position].link))
                browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                mContext.startActivity(browserIntent)
            }
        })
    }

}
