package jokes.com.multibhashi_assignment_jokes

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast


class FirstFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.first, container, false)
//        val url = "http://api.icndb.com/jokes/?limitTo=[explicit]"

        val url = "http://api.icndb.com/jokes/random/10?limitTo=[explicit]"

        val imageView = view.findViewById<ImageView>(R.id.img_explicit)
        imageView.setImageResource(R.drawable.explicitimg)
        view.setOnClickListener(){


            val intent = Intent(this@FirstFragment.activity,RecyclerClass::class.java)
            intent.putExtra("url", url)
            startActivity(intent)
        }

        return view
    }
}