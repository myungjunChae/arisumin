package arisumin.com.arisumin.view.myinfo

import android.content.Context
import android.os.Bundle
import arisumin.com.arisumin.R
import arisumin.com.arisumin.databinding.ActivityMyInfoBinding
import arisumin.com.arisumin.datasource.PreferenceModel
import arisumin.com.arisumin.startActivity
import arisumin.com.arisumin.toast
import arisumin.com.arisumin.view.MainActivity
import arisumin.com.arisumin.view.base.BaseActivity

class MyInfoActivity : BaseActivity<ActivityMyInfoBinding>() {

    override val resourceId: Int = R.layout.activity_my_info

    private val pref by lazy { MyInfoPref(this, "my_info") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.confirm.setOnClickListener {
            if (validateInput()) {
                pref.name = binding.inputName.text.toString()
                pref.weight = binding.inputWeight.text.toString().toInt()
                startActivity<MainActivity>()
            }
        }
    }

    private fun validateInput(): Boolean {
        if (binding.inputName.text.isEmpty()) {
            this toast "name is empty"
            return false
        }
        if (binding.inputWeight.text.isEmpty()) {
            this toast "weight is empty"
            return false
        }
        return true
    }
}

class MyInfoPref(context: Context, name: String) : PreferenceModel(context, name) {
    var name by stringPreference("name", null)
    var weight by intPreference("weight")
}