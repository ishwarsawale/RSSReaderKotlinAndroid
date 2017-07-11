package rss.sample.com.samplerss.Model

/**
 * Created by Admin on 7/7/2017.
 */

data class Item(val title:String, val pubDate:String, val link:String, val guid:String, val author:String, val thumbnail:String, val description:String, val content:String, val enclosure:List<String>,val categories:List<String>){

}

/*
* data class Item(val title:String, val pubDate:String, val link:String, val guid:String, val author:String, val thumbnail:String, val description:String, val content:String, val enclosure:List<String>,val categories:List<String>){

}
*/