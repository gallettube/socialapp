package com.raspberry.socialapp.ui

import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.raspberry.socialapp.R
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //super.onCreateDrawer()
        var user : FirebaseUser? = FirebaseAuth.getInstance().currentUser
        if(user != null) {
            var name: String? = user.displayName
            var email: String? = user.email
            var uid: String? = user.uid
            nameTextView.setText(name)
            emailTextView.setText(email)
            uidTextView.setText(uid)
        } else {
            super.goLoginScreen()
        }

        /*search.setOnClickListener { view ->
            Snackbar.make( view,
                           "Replace with your own action",
                           Snackbar.LENGTH_LONG)
                           .setAction("Action", null).show()
        }*/
//        if(AccessToken.getCurrentAccessToken() == null){
//            goLoginScreen()
//        }
    }



//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.main, menu)
//        return true
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        val id = item.itemId
//
//        if (id == R.id.action_settings) {
//            return true
//        }
//        return super.onOptionsItemSelected(item)
//    }


}
