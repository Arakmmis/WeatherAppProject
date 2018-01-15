package me.arakmmis.weatherproject.ui.contactus

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.contact_us_activity.*
import me.arakmmis.weatherproject.R

class ContactUsActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ContactUsActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contact_us_activity)

        tv_phone.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + getString(R.string.phone_number))
            ContextCompat.startActivity(this@ContactUsActivity, intent, null)
        }

        tv_email.setOnClickListener { _ ->
            // Copy To Clipboard
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Address", getString(R.string.email_address))
            clipboard.primaryClip = clip
            Toast.makeText(this@ContactUsActivity, "Email Address Copied to Clipboard", Toast.LENGTH_SHORT).show()
        }
    }
}