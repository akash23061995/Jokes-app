package jokes.com.multibhashi_assignment_jokes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import org.jetbrains.anko.toast
class SecondFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.second, container, false)


        val imageView = view.findViewById<ImageView>(R.id.img_nerd)
        imageView.setImageResource(R.drawable.nerdimg)
        val url = "http://api.icndb.com/jokes/random/10?limitTo=[nerdy]"


         view.setOnClickListener(){
             val intent = Intent(this@SecondFragment.activity,RecyclerClass::class.java)
             intent.putExtra("url", url)
             startActivity(intent)

         }

        return view
    }

}

